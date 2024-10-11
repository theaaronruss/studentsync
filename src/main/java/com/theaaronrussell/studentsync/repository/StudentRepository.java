package com.theaaronrussell.studentsync.repository;

import com.theaaronrussell.studentsync.entity.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface StudentRepository extends CrudRepository<Student, UUID> {
}
