package com.theaaronrussell.studentsync.exception;

import java.util.UUID;

public class AcademicTermNotFoundException extends RuntimeException {

    public AcademicTermNotFoundException(UUID id) {
        super("Academic term with ID of " + id + " not found");
    }

}
