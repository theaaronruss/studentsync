package com.theaaronrussell.studentsync.service;

import com.theaaronrussell.studentsync.entity.AcademicTerm;
import com.theaaronrussell.studentsync.entity.Course;
import com.theaaronrussell.studentsync.entity.Teacher;
import com.theaaronrussell.studentsync.exception.AcademicTermNotFoundException;
import com.theaaronrussell.studentsync.exception.CourseNotFoundException;
import com.theaaronrussell.studentsync.exception.TeacherNotFoundException;
import com.theaaronrussell.studentsync.mapper.CourseMapper;
import com.theaaronrussell.studentsync.model.AcademicTermDTO;
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

import java.time.LocalDate;
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
        CourseDTO result = courseMapper.courseToCourseDto(course);
        setCourseStatus(result);
        return result;
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
        CourseDTO result = courseMapper.courseToCourseDto(savedCourse);
        setCourseStatus(result);
        return result;
    }

    @Transactional
    public CourseDTO updateCourse(UUID id, AddCourseRequestDTO request) {
        if (!courseRepository.existsById(id)) {
            log.error("Course with ID of {} not found", id);
            throw new CourseNotFoundException(id);
        }
        checkIfTeacherExists(request.getTeacherId());
        checkIfAcademicTermExists(request.getAcademicTermId());
        Course newCourse = courseMapper.addCourseRequestDtoToCourse(request);
        newCourse.setId(id);
        Course updatedCourse = courseRepository.save(newCourse);
        log.info("Course with ID of {} updated", id);
        CourseDTO result = courseMapper.courseToCourseDto(updatedCourse);
        setCourseStatus(result);
        return result;
    }

    private void checkIfTeacherExists(UUID teacherId) {
        if (!teacherRepository.existsById(teacherId)) {
            log.error("Teacher with ID of {} not found", teacherId);
            throw new TeacherNotFoundException(teacherId);
        }
    }

    private void checkIfAcademicTermExists(UUID academicTermId) {
        if (!academicTermRepository.existsById(academicTermId)) {
            log.error("Academic term with ID of {} not found", academicTermId);
            throw new AcademicTermNotFoundException(academicTermId);
        }
    }

    @Transactional
    public void deleteCourse(UUID id) {
        if (courseRepository.deleteCourseById(id) == 0) {
            log.error("Course with ID of {} not found", id);
            throw new CourseNotFoundException(id);
        }
        log.info("Course with ID of {} deleted", id);
    }

    private void setCourseStatus(CourseDTO course) {
        AcademicTermDTO courseTerm = course.getAcademicTerm();
        if (courseTerm == null) return;
        LocalDate now = LocalDate.now();
        if (courseTerm.getStartDate().isAfter(now)) {
            course.setStatus(CourseDTO.StatusEnum.UPCOMING);
        } else if (courseTerm.getEndDate().isBefore(now)) {
            course.setStatus(CourseDTO.StatusEnum.ARCHIVE);
        } else {
            course.setStatus(CourseDTO.StatusEnum.CURRENT);
        }
    }

}
