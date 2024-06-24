package com.riwi.librosya.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class BookBasicResponse {
    
    private Long id;

    private String title;

    private String author;

    private String genre;

    private int publicationYear;

    private String isbn;

}
