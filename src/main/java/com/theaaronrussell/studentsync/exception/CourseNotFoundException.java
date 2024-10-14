package com.theaaronrussell.studentsync.exception;

import java.util.UUID;

public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException(UUID id) {
        super("Course with ID of " + id + " not found");
    }

}
