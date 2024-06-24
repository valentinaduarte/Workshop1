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
public class LoanResponse {
    private Long id;

    private Date loan_date;

    private Date return_date;

    private Status status;

    private UserBasicResponse user;

    private BookBasicResponse book;

}
