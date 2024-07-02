package com.riwi.librosya.api.dto.response;

import java.util.List;

public class BookResponse {
    private Long id;

    private String title;

    private String author;

    private int publicationYear;

    private String genre;

    private String isbn;

    //private UserBasicResp user;

    private List<LoanBasicResponse> loans;

    private List<ReservationBasicResponse> reservations;
}
