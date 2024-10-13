package com.theaaronrussell.studentsync.controller;

import com.theaaronrussell.studentsync.api.AcademicTermsApi;
import com.theaaronrussell.studentsync.model.AcademicTermDTO;
import com.theaaronrussell.studentsync.model.AddAcademicTermRequestDTO;
import com.theaaronrussell.studentsync.service.AcademicTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class AcademicTermController implements AcademicTermsApi {

    private final AcademicTermService academicTermService;

    @Autowired
    public AcademicTermController(AcademicTermService academicTermService) {
        this.academicTermService = academicTermService;
    }

    @Override
    public ResponseEntity<AcademicTermDTO> addAcademicTerm(AddAcademicTermRequestDTO request) {
        AcademicTermDTO response = academicTermService.addAcademicTerm(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AcademicTermDTO> getAcademicTerm(UUID id) {
        AcademicTermDTO response = academicTermService.getAcademicTerm(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AcademicTermDTO> updateAcademicTerm(UUID id, AddAcademicTermRequestDTO request) {
        AcademicTermDTO response = academicTermService.updateAcademicTerm(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteAcademicTerm(UUID id) {
        academicTermService.deleteAcademicTerm(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
