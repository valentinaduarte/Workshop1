package com.riwi.librosya.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.riwi.librosya.api.dto.request.ReservationRequest;
import com.riwi.librosya.api.dto.response.ReservationBasicResponse;
import com.riwi.librosya.domain.entity.Reservation;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReservationMapper {

    ReservationBasicResponse entityToGetResp(Reservation reservation);

    @Mapping(target = "book", ignore = true)
    @Mapping(target = "user", ignore = true)
    Reservation requestToGetEntity(ReservationRequest request);
}