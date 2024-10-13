package com.theaaronrussell.studentsync.service;

import com.theaaronrussell.studentsync.entity.AcademicTerm;
import com.theaaronrussell.studentsync.mapper.AcademicTermMapper;
import com.theaaronrussell.studentsync.model.AcademicTermDTO;
import com.theaaronrussell.studentsync.model.AddAcademicTermRequestDTO;
import com.theaaronrussell.studentsync.repository.AcademicTermRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcademicTermService {

    private static final Logger log = LoggerFactory.getLogger(AcademicTermService.class);

    private final AcademicTermRepository academicTermRepository;
    private final AcademicTermMapper academicTermMapper;

    @Autowired
    public AcademicTermService(AcademicTermRepository academicTermRepository, AcademicTermMapper academicTermMapper) {
        this.academicTermRepository = academicTermRepository;
        this.academicTermMapper = academicTermMapper;
    }

    public AcademicTermDTO addAcademicTerm(AddAcademicTermRequestDTO request) {
        AcademicTerm academicTerm = academicTermMapper.addAcademicTermRequestDtoToAcademicTerm(request);
        AcademicTerm savedAcademicTerm = academicTermRepository.save(academicTerm);
        log.info("Academic term with ID of {} added", savedAcademicTerm.getId());
        return academicTermMapper.academicTermToAcademicTermDto(savedAcademicTerm);
    }

}
