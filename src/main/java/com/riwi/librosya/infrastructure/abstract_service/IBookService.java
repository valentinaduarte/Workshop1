package com.riwi.librosya.infrastructure.abstract_service;

import com.riwi.librosya.api.dto.request.BookRequest;
import com.riwi.librosya.api.dto.response.BookResponse;

public interface IBookService extends CRUDService<BookRequest, BookResponse, Long>{
    public final String FIELD_BY_SORT = "title";
}
