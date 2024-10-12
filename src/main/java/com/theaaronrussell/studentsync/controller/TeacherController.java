package com.theaaronrussell.studentsync.controller;

import com.theaaronrussell.studentsync.api.TeachersApi;
import com.theaaronrussell.studentsync.model.AddTeacherRequestDTO;
import com.theaaronrussell.studentsync.model.TeacherDTO;
import com.theaaronrussell.studentsync.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

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

    @Override
    public ResponseEntity<TeacherDTO> getTeacher(UUID id) {
        TeacherDTO response = teacherService.getTeacher(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TeacherDTO> updateTeacher(UUID id, AddTeacherRequestDTO request) {
        TeacherDTO response = teacherService.updateTeacher(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteTeacher(UUID id) {
        teacherService.deleteTeacher(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
