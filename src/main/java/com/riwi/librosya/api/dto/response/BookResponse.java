package com.riwi.librosya.api.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private Long id;

    private String title;

    private String author;

    private int publicationYear;

    private String genre;

    private String isbn;

    private List<LoanBasicResponse> loans;

    private List<ReservationBasicResponse> reservations;
}
