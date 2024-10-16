package com.theaaronrussell.studentsync.service;

import com.theaaronrussell.studentsync.entity.Teacher;
import com.theaaronrussell.studentsync.exception.TeacherNotFoundException;
import com.theaaronrussell.studentsync.mapper.TeacherMapper;
import com.theaaronrussell.studentsync.model.AddTeacherRequestDTO;
import com.theaaronrussell.studentsync.model.TeacherDTO;
import com.theaaronrussell.studentsync.repository.TeacherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class TeacherService {

    private static final Logger log = LoggerFactory.getLogger(TeacherService.class);

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, TeacherMapper teacherMapper) {
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
    }

    public TeacherDTO getTeacher(UUID id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> {
            log.error("Teacher with ID {} not found", id);
            return new TeacherNotFoundException(id);
        });
        log.info("Retrieved teacher with ID of {}", id);
        return teacherMapper.teacherToTeacherDto(teacher);
    }

    @Transactional
    public TeacherDTO addTeacher(AddTeacherRequestDTO request) {
        Teacher teacher = teacherMapper.addTeacherRequestDtoToTeacher(request);
        Teacher savedTeacher = teacherRepository.save(teacher);
        log.info("Teacher with ID {} added", savedTeacher.getId());
        return teacherMapper.teacherToTeacherDto(savedTeacher);
    }

    @Transactional
    public TeacherDTO updateTeacher(UUID id, AddTeacherRequestDTO request) {
        if (!teacherRepository.existsById(id)) {
            log.error("Teacher with ID {} not found", id);
            throw new TeacherNotFoundException(id);
        }
        Teacher newTeacher = teacherMapper.addTeacherRequestDtoToTeacher(request);
        newTeacher.setId(id);
        Teacher updatedTeacher = teacherRepository.save(newTeacher);
        log.info("Teacher with ID {} updated", updatedTeacher.getId());
        return teacherMapper.teacherToTeacherDto(updatedTeacher);
    }

    @Transactional
    public void deleteTeacher(UUID id) {
        if (teacherRepository.deleteTeacherById(id) == 0) {
            log.error("Teacher with ID {} not found", id);
            throw new TeacherNotFoundException(id);
        }
        log.info("Teacher with ID {} deleted", id);
    }

}
