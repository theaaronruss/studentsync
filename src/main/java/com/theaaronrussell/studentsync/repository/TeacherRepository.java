package com.theaaronrussell.studentsync.repository;

import com.theaaronrussell.studentsync.entity.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TeacherRepository extends CrudRepository<Teacher, UUID> {
}
