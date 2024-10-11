package com.theaaronrussell.studentsync.mapper;

import com.theaaronrussell.studentsync.entity.Teacher;
import com.theaaronrussell.studentsync.model.AddTeacherRequestDTO;
import com.theaaronrussell.studentsync.model.TeacherDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    @Mapping(target = "id", ignore = true)
    Teacher addTeacherRequestDtoToTeacher(AddTeacherRequestDTO request);

    TeacherDTO teacherToTeacherDto(Teacher teacher);

}
