package com.theaaronrussell.studentsync.advice;

import com.theaaronrussell.studentsync.exception.StudentNotFoundException;
import com.theaaronrussell.studentsync.model.ErrorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class ControllerExceptionAdvice {

    private static final Logger log = LoggerFactory.getLogger(ControllerExceptionAdvice.class);

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleStudentNotFoundException(StudentNotFoundException e) {
        ErrorDTO response = new ErrorDTO();
        response.setMessage(e.getMessage());
        response.setTimestamp(OffsetDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
