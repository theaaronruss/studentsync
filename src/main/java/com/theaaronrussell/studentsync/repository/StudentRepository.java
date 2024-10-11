package com.theaaronrussell.studentsync.repository;

import com.theaaronrussell.studentsync.entity.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface StudentRepository extends CrudRepository<Student, UUID> {

    /**
     * Deletes a student by their ID.
     *
     * @param id the UUID of the student to delete
     * @return the number of students deleted
     */
    long deleteStudentById(UUID id);

}
