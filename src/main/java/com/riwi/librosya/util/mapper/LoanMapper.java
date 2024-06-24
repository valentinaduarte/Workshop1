package com.riwi.librosya.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.riwi.librosya.api.dto.request.LoanRequest;
import com.riwi.librosya.api.dto.response.LoanBasicResponse;
import com.riwi.librosya.domain.entity.Loan;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LoanMapper {

    LoanBasicResponse entityToGetResp(Loan loan);

    @Mapping(target = "book", ignore = true)
    @Mapping(target = "user", ignore = true)
    Loan requestToGetEntity(LoanRequest request);
}