package com.riwi.librosya.api.dto.response;

import java.sql.Date;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanBasicResponse {
    private Long id;

    private Date loanDate;

    private Date returnDate;

    private String status;

}
