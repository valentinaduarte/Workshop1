package com.riwi.librosya.api.dto.request;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class LoanRequest{
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Loan date can't be in the past")
    private Date loanDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Return date can't be in the past")
    private Date returnDate;

    @NotBlank(message = "User's status is required")
    @Size(max = 20, message = "Loan's status max length is 20")
    private String status;

    @NotNull(message = "User's id is required")
    private int userId;

    @NotNull(message = "Book's id is required")
    private int bookId;

}