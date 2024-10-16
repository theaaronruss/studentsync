package com.theaaronrussell.studentsync.service;

import com.theaaronrussell.studentsync.entity.Student;
import com.theaaronrussell.studentsync.exception.StudentNotFoundException;
import com.theaaronrussell.studentsync.mapper.StudentMapper;
import com.theaaronrussell.studentsync.model.AddStudentRequestDTO;
import com.theaaronrussell.studentsync.model.StudentDTO;
import com.theaaronrussell.studentsync.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class StudentService {

    private static final Logger log = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Autowired
    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public StudentDTO getStudent(UUID id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Student with ID {} not found", id);
                    return new StudentNotFoundException(id);
                });
        log.info("Retrieved student with ID of {}", id);
        return studentMapper.studentToStudentDto(student);
    }

    @Transactional
    public StudentDTO addStudent(AddStudentRequestDTO request) {
        Student student = studentMapper.addStudentRequestDtoToStudent(request);
        Student savedStudent = studentRepository.save(student);
        log.info("Student with ID {} added", savedStudent.getId());
        return studentMapper.studentToStudentDto(savedStudent);
    }

    @Transactional
    public StudentDTO updateStudent(UUID id, AddStudentRequestDTO request) {
        if (!studentRepository.existsById(id)) {
            log.error("Student with ID {} not found", id);
            throw new StudentNotFoundException(id);
        }
        Student newStudent = studentMapper.addStudentRequestDtoToStudent(request);
        newStudent.setId(id);
        Student updatedStudent = studentRepository.save(newStudent);
        log.info("Student with ID {} updated", updatedStudent.getId());
        return studentMapper.studentToStudentDto(updatedStudent);
    }

    @Transactional
    public void deleteStudent(UUID id) {
        if (studentRepository.deleteStudentById(id) == 0) {
            log.error("Student with ID {} not found", id);
            throw new StudentNotFoundException(id);
        }
        log.info("Student with ID {} deleted", id);
    }

}
