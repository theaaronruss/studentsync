package com.theaaronrussell.studentsync.service;

import com.theaaronrussell.studentsync.entity.Student;
import com.theaaronrussell.studentsync.mapper.StudentMapper;
import com.theaaronrussell.studentsync.model.AddStudentRequestDTO;
import com.theaaronrussell.studentsync.model.StudentDTO;
import com.theaaronrussell.studentsync.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Autowired
    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public StudentDTO addStudent(AddStudentRequestDTO request) {
        Student studentEntity = studentMapper.addStudentRequestDtoToStudent(request);
        Student savedStudent = studentRepository.save(studentEntity);
        return studentMapper.studentToStudentDto(savedStudent);
    }

}
