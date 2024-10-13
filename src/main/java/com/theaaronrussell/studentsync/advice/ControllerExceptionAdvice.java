package com.theaaronrussell.studentsync.advice;

import com.theaaronrussell.studentsync.exception.AcademicTermNotFoundException;
import com.theaaronrussell.studentsync.exception.StudentNotFoundException;
import com.theaaronrussell.studentsync.exception.TeacherNotFoundException;
import com.theaaronrussell.studentsync.model.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler({
            StudentNotFoundException.class,
            TeacherNotFoundException.class,
            AcademicTermNotFoundException.class
    })
    public ResponseEntity<ErrorDTO> handleNotFoundExceptions(RuntimeException e) {
        ErrorDTO response = new ErrorDTO();
        response.setMessage(e.getMessage());
        response.setTimestamp(OffsetDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
