package com.theaaronrussell.studentsync.service;

import com.theaaronrussell.studentsync.entity.AcademicTerm;
import com.theaaronrussell.studentsync.entity.Course;
import com.theaaronrussell.studentsync.entity.Teacher;
import com.theaaronrussell.studentsync.exception.AcademicTermNotFoundException;
import com.theaaronrussell.studentsync.exception.CourseNotFoundException;
import com.theaaronrussell.studentsync.exception.TeacherNotFoundException;
import com.theaaronrussell.studentsync.mapper.CourseMapper;
import com.theaaronrussell.studentsync.model.AddCourseRequestDTO;
import com.theaaronrussell.studentsync.model.CourseDTO;
import com.theaaronrussell.studentsync.repository.AcademicTermRepository;
import com.theaaronrussell.studentsync.repository.CourseRepository;
import com.theaaronrussell.studentsync.repository.TeacherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CourseService {

    private static final Logger log = LoggerFactory.getLogger(CourseService.class);

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final AcademicTermRepository academicTermRepository;
    private final CourseMapper courseMapper;

    @Autowired
    public CourseService(CourseRepository courseRepository, TeacherRepository teacherRepository, AcademicTermRepository academicTermRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.academicTermRepository = academicTermRepository;
        this.courseMapper = courseMapper;
    }

    public CourseDTO getCourse(UUID id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> {
            log.error("Course with ID of {} not found", id);
            return new CourseNotFoundException(id);
        });
        log.info("Retrieved course with ID of {}", id);
        return courseMapper.courseToCourseDto(course);
    }

    @Transactional
    public CourseDTO addCourse(AddCourseRequestDTO request) {
        UUID teacherId = request.getTeacherId();
        UUID academicTermId = request.getAcademicTermId();
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> {
            log.error("Teacher with ID of {} not found", request.getTeacherId());
            return new TeacherNotFoundException(teacherId);
        });
        AcademicTerm academicTerm = academicTermRepository.findById(academicTermId).orElseThrow(() -> {
            log.error("Academic term with ID of {} not found", academicTermId);
            return new AcademicTermNotFoundException(academicTermId);
        });
        Course course = courseMapper.addCourseRequestDtoToCourse(request);
        Course savedCourse = courseRepository.save(course);
        savedCourse.setTeacher(teacher);
        savedCourse.setAcademicTerm(academicTerm);
        log.info("Course with ID of {} added", savedCourse.getId());
        return courseMapper.courseToCourseDto(savedCourse);
    }

}
