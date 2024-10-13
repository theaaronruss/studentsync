package com.theaaronrussell.studentsync.repository;

import com.theaaronrussell.studentsync.entity.AcademicTerm;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AcademicTermRepository extends CrudRepository<AcademicTerm, UUID> {

    /**
     * Deletes an academic term by its ID.
     *
     * @param id the UUID of the academic term to delete
     * @return the number of academic terms deleted
     */
    long deleteAcademicTermById(UUID id);

}
