package com.riwi.librosya.util.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.mapstruct.MappingConstants;

import com.riwi.librosya.api.dto.request.UserRequest;
import com.riwi.librosya.api.dto.response.UserBasicResponse;
import com.riwi.librosya.domain.entity.User;



@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserBasicResponse entityToGetResp(User user);

    UserRequest entityToGetReq(User user);

    @Mapping(target = "loans", ignore = true)
    @Mapping(target = "reservations", ignore = true)
    User requestToGetEntity(UserRequest request);
}