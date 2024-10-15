package com.theaaronrussell.studentsync.mapper;

import com.theaaronrussell.studentsync.entity.Course;
import com.theaaronrussell.studentsync.model.AddCourseRequestDTO;
import com.theaaronrussell.studentsync.model.CourseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "academicTerm.id", source = "academicTermId")
    @Mapping(target = "teacher.id", source = "teacherId")
    Course addCourseRequestDtoToCourse(AddCourseRequestDTO request);

    CourseDTO courseToCourseDto(Course course);

}
