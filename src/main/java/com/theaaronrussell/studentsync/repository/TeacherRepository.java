package com.theaaronrussell.studentsync.repository;

import com.theaaronrussell.studentsync.entity.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TeacherRepository extends CrudRepository<Teacher, UUID> {

    /**
     * Deletes a teacher by their ID.
     *
     * @param id the UUID of the teacher to delete
     * @return the number of teachers deleted
     */
    long deleteTeacherById(UUID id);

}
