package com.riwi.librosya.api.dto.response;

import java.util.Date;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationBasicResponse {

    private Long id;

    private Date reservationDate;

    private String status;

    private Long userId;

    private Long bookId;
}
