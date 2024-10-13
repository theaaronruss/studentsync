package com.theaaronrussell.studentsync.mapper;

import com.theaaronrussell.studentsync.entity.AcademicTerm;
import com.theaaronrussell.studentsync.model.AcademicTermDTO;
import com.theaaronrussell.studentsync.model.AddAcademicTermRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AcademicTermMapper {

    @Mapping(target = "id", ignore = true)
    AcademicTerm addAcademicTermRequestDtoToAcademicTerm(AddAcademicTermRequestDTO addAcademicTermRequestDTO);

    AcademicTermDTO academicTermToAcademicTermDto(AcademicTerm academicTerm);

}
