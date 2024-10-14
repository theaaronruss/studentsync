package com.theaaronrussell.studentsync.controller;

import com.theaaronrussell.studentsync.api.CoursesApi;
import com.theaaronrussell.studentsync.model.AddCourseRequestDTO;
import com.theaaronrussell.studentsync.model.CourseDTO;
import com.theaaronrussell.studentsync.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class CourseController implements CoursesApi {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public ResponseEntity<CourseDTO> addCourse(AddCourseRequestDTO request) {
        CourseDTO course = courseService.addCourse(request);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CourseDTO> getCourse(UUID id) {
        CourseDTO course = courseService.getCourse(id);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

}
