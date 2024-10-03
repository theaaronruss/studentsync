package com.theaaronrussell.studentsync.repository;

import com.theaaronrussell.studentsync.entity.DatabaseUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<DatabaseUser, String> {

    DatabaseUser findByUsername(String username);

}
