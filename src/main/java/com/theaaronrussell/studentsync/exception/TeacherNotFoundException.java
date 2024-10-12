package com.theaaronrussell.studentsync.exception;

import java.util.UUID;

public class TeacherNotFoundException extends RuntimeException {

    public TeacherNotFoundException(UUID id) {
        super("Teacher with ID of " + id + " not found");
    }

}
