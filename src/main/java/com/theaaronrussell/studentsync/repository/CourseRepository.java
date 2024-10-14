package com.theaaronrussell.studentsync.repository;

import com.theaaronrussell.studentsync.entity.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CourseRepository extends CrudRepository<Course, UUID> {
}
