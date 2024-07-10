package com.riwi.librosya.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.riwi.librosya.api.dto.request.ReservationRequest;
import com.riwi.librosya.api.dto.response.ReservationResponse;
import com.riwi.librosya.domain.entity.Reservation;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReservationMapper {
    
    ReservationResponse entityToGetResp(Reservation reservation);

    @Mapping(target = "book", ignore = true)
    @Mapping(target = "user", ignore = true)
    Reservation requestToGetEntity(ReservationRequest request);
}