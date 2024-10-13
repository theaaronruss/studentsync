package com.theaaronrussell.studentsync.repository;

import com.theaaronrussell.studentsync.entity.AcademicTerm;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AcademicTermRepository extends CrudRepository<AcademicTerm, UUID> {
}
