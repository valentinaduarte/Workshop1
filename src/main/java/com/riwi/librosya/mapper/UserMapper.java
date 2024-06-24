package com.riwi.librosya.mapper;


import org.mapstruct.Mapper;

import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import com.riwi.librosya.api.dto.request.UserRequest;
import com.riwi.librosya.api.dto.response.UserBasicResponse;
import com.riwi.librosya.domain.entity.User;



@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserRequest userRequest);

    //UserResponse toResponse(User user);

    UserBasicResponse toBasicResponse(User user);
}