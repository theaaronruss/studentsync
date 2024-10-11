package com.theaaronrussell.studentsync.exception;

import java.util.UUID;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(UUID id) {
        super("Student with ID of " + id + " not found");
    }

}
