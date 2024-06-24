package com.riwi.librosya.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.riwi.librosya.api.dto.request.BookRequest;
import com.riwi.librosya.api.dto.response.BookBasicResponse;
import com.riwi.librosya.domain.entity.Book;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {

    BookBasicResponse entityToGetResp(Book book);

    BookRequest entityToGetReq(Book book);

    @Mapping(target = "loans", ignore = true)
    @Mapping(target = "reservations", ignore = true)
    Book requestToGetEntity(BookRequest request);
}