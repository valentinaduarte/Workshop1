package com.riwi.librosya.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {
    @NotBlank(message = "Book`s title is required.")
    @Size(max = 100, message = "Book's title max length is 100")
    private String title;

    @NotBlank(message = "Book`s author is required.")
    private String author;

    @NotNull(message = "Publication year is required.")
    private int publicationYear;

    @NotBlank(message = "Book`s genre is required.")
    private String genre;

    @NotNull(message = "Book`s isbn is required.")
    private String isbn;
}
