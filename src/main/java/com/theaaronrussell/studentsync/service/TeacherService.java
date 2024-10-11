package com.theaaronrussell.studentsync.service;

import com.theaaronrussell.studentsync.entity.Teacher;
import com.theaaronrussell.studentsync.mapper.TeacherMapper;
import com.theaaronrussell.studentsync.model.AddTeacherRequestDTO;
import com.theaaronrussell.studentsync.model.TeacherDTO;
import com.theaaronrussell.studentsync.repository.TeacherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public TeacherDTO addTeacher(AddTeacherRequestDTO request) {
        Teacher teacher = teacherMapper.addTeacherRequestDtoToTeacher(request);
        Teacher savedTeacher = teacherRepository.save(teacher);
        log.info("Teacher with ID {} added", savedTeacher.getId());
        return teacherMapper.teacherToTeacherDto(savedTeacher);
    }

}
