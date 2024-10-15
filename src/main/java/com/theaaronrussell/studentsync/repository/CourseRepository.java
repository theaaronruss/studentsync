package com.theaaronrussell.studentsync.repository;

import com.theaaronrussell.studentsync.entity.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CourseRepository extends CrudRepository<Course, UUID> {

    /**
     * Deletes a course by its ID.
     *
     * @param id the UUID of the course to delete
     * @return the number of courses deleted
     */
    long deleteCourseById(UUID id);

}
