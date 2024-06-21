package com.riwi.librosya.domain.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;

@Entity(name = "loan")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Loan{
    @Id
    @Column(length = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="loan_date",nullable = false)
    private Date loanDate;

    @Column(name="return_date",nullable = false)
    private Date returnDate;

    @Column(length = 20, nullable = false)
    private String status;

    @Column(name = "user_id",length = 20, nullable = false)
    private Long userId;

    @Column(name = "book_id",length = 20, nullable = false)
    private Long bookId;

    /*Relationship with User */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    /*Relationship with Book */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<Book> books;

}

