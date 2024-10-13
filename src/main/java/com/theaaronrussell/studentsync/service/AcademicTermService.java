package com.theaaronrussell.studentsync.service;

import com.theaaronrussell.studentsync.entity.AcademicTerm;
import com.theaaronrussell.studentsync.exception.AcademicTermNotFoundException;
import com.theaaronrussell.studentsync.mapper.AcademicTermMapper;
import com.theaaronrussell.studentsync.model.AcademicTermDTO;
import com.theaaronrussell.studentsync.model.AddAcademicTermRequestDTO;
import com.theaaronrussell.studentsync.repository.AcademicTermRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

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

    public AcademicTermDTO getAcademicTerm(UUID id) {
        AcademicTerm academicTerm = academicTermRepository.findById(id).orElseThrow(() -> {
            log.error("Academic term with ID of {} not found", id);
            return new AcademicTermNotFoundException(id);
        });
        return academicTermMapper.academicTermToAcademicTermDto(academicTerm);
    }

    @Transactional
    public AcademicTermDTO addAcademicTerm(AddAcademicTermRequestDTO request) {
        AcademicTerm academicTerm = academicTermMapper.addAcademicTermRequestDtoToAcademicTerm(request);
        AcademicTerm savedAcademicTerm = academicTermRepository.save(academicTerm);
        log.info("Academic term with ID of {} added", savedAcademicTerm.getId());
        return academicTermMapper.academicTermToAcademicTermDto(savedAcademicTerm);
    }

    @Transactional
    public AcademicTermDTO updateAcademicTerm(UUID id, AddAcademicTermRequestDTO request) {
        if (!academicTermRepository.existsById(id)) {
            log.error("Academic term with ID of {} not found", id);
            throw new AcademicTermNotFoundException(id);
        }
        AcademicTerm newAcademicTerm = academicTermMapper.addAcademicTermRequestDtoToAcademicTerm(request);
        newAcademicTerm.setId(id);
        AcademicTerm updatedAcademicTerm = academicTermRepository.save(newAcademicTerm);
        log.info("Academic term with ID of {} updated", updatedAcademicTerm.getId());
        return academicTermMapper.academicTermToAcademicTermDto(updatedAcademicTerm);
    }

    @Transactional
    public void deleteAcademicTerm(UUID id) {
        if (academicTermRepository.deleteAcademicTermById(id) == 0) {
            log.error("Academic term with ID of {} not found", id);
            throw new AcademicTermNotFoundException(id);
        }
        log.info("Academic term with ID of {} deleted", id);
    }

}
