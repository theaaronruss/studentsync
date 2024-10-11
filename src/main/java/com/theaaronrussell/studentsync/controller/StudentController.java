package com.theaaronrussell.studentsync.controller;

import com.theaaronrussell.studentsync.api.StudentsApi;
import com.theaaronrussell.studentsync.model.AddStudentRequestDTO;
import com.theaaronrussell.studentsync.model.StudentDTO;
import com.theaaronrussell.studentsync.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController implements StudentsApi {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public ResponseEntity<StudentDTO> studentsPost(AddStudentRequestDTO request) {
        StudentDTO response = studentService.addStudent(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
