package com.theaaronrussell.studentsync.controller;

import com.theaaronrussell.studentsync.api.TeachersApi;
import com.theaaronrussell.studentsync.model.AddTeacherRequestDTO;
import com.theaaronrussell.studentsync.model.TeacherDTO;
import com.theaaronrussell.studentsync.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeacherController implements TeachersApi {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Override
    public ResponseEntity<TeacherDTO> addTeacher(AddTeacherRequestDTO request) {
        TeacherDTO response = teacherService.addTeacher(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
