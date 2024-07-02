package com.riwi.librosya.infrastructure.abstract_service;

import com.riwi.librosya.api.dto.request.ReservationRequest;
import com.riwi.librosya.api.dto.response.ReservationResponse;

public interface IReservationService extends CRUDService<ReservationRequest, ReservationResponse, Long>{
    public final String FIELD_BY_SORT = "reservationDate";
}
