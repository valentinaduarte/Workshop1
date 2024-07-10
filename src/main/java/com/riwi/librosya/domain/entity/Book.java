package com.riwi.librosya.domain.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

@Entity(name = "book")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 100, nullable = false)
    private String author;

    @Column(length = 50, nullable = false)
    private String genre;

    @Column(name = "publication_year", nullable = false)
    private int publicationYear;

    @Column(length = 20, nullable = false)
    private String isbn;

    /*Relationship with reservation */
    @OneToMany(mappedBy = "book",fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Reservation> reservations;

    /*Relationship with loan */
    @OneToMany(mappedBy = "book",fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Loan> loans;
}



