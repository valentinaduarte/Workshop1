package com.riwi.librosya.api.dto.response;

import java.sql.Date;

import com.riwi.librosya.util.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponse {

    private Long id;

    private Date reservationDate;

    private Status status;

    private UserBasicResponse user;

    private BookBasicResponse book;
}