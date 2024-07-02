package com.riwi.librosya.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.librosya.domain.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
}

