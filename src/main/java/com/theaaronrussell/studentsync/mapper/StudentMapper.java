package com.theaaronrussell.studentsync.mapper;

import com.theaaronrussell.studentsync.entity.Student;
import com.theaaronrussell.studentsync.model.AddStudentRequestDTO;
import com.theaaronrussell.studentsync.model.StudentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "id", ignore = true)
    Student addStudentRequestDtoToStudent(AddStudentRequestDTO request);

    StudentDTO studentToStudentDto(Student student);

}
