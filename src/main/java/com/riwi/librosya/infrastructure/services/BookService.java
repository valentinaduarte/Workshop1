package com.riwi.librosya.infrastructure.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.riwi.librosya.api.dto.request.BookRequest;
import com.riwi.librosya.api.dto.response.BookResponse;
import com.riwi.librosya.domain.entity.Book;
import com.riwi.librosya.domain.repositories.BookRepository;
import com.riwi.librosya.infrastructure.abstract_service.IBookService;
import com.riwi.librosya.util.enums.SortType;
import com.riwi.librosya.util.exceptions.BadRequestException;
import com.riwi.librosya.util.mapper.BookMapper;
import com.riwi.librosya.util.messages.ErrorMessage;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class BookService implements IBookService{

    /* Inyeccion dependencias */
        @Autowired
        private final BookRepository bookRepository;

        @Autowired
        private final BookMapper bookMapper;
    


        @Override
        public BookResponse create(BookRequest request) {
            
            Book book = this.bookMapper.requestToGetEntity(request);

            book.setLoans(new ArrayList<>());
            book.setReservations(new ArrayList<>());

            return this.bookMapper.entityToGetResp(bookRepository.save(book));
        }

        @SuppressWarnings("null")
        @Override
        public Page<BookResponse> getAll(int page, int size, SortType sortType) {
            if (page<0) page =0;

            PageRequest pagination = null;
                switch (sortType) {
                    case NONE -> pagination = PageRequest.of(page, size); 
                    case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending()); //organizar de forma ascendente por titulo de lesson
                    case DESC -> pagination  = PageRequest.of(page, size,Sort.by(FIELD_BY_SORT).descending()); //organizar de forma descendente por titulo de lesson
                }

            return this.bookRepository.findAll(pagination).map(book->bookMapper.entityToGetResp(book));
        }

        @Override
        public BookResponse getById(Long id) {
            return this.bookMapper.entityToGetResp(this.findBook(id));
        }

        @Override
        public BookResponse update(BookRequest request, Long id) {
            Book book = this.findBook(id);

            Book bookUpdate = this.bookMapper.requestToGetEntity(request);

            bookUpdate.setLoans(book.getLoans());
            bookUpdate.setReservations(book.getReservations());

            return this.bookMapper.entityToGetResp(this.bookRepository.save(bookUpdate));
        }

        @Override
        public void delete(Long id) {
            this.bookRepository.delete(this.findBook(id));
        }

    private Book findBook(Long id){ 
        return this.bookRepository.findById(id).orElseThrow(()->new BadRequestException(ErrorMessage.idNotFound("Book")));
    }
    
}
