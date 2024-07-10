package com.riwi.librosya.api.dto.response;

import java.sql.Date;

import com.riwi.librosya.util.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanResponse {
    private Long id;

    private Date loanDate;
    
    private Date returnDate;

    private Status status;

    private UserBasicResponse user;

    private BookBasicResponse book;
}
