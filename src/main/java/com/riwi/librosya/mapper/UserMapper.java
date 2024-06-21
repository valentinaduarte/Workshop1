package com.riwi.librosya.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.riwi.librosya.api.dto.request.UserRequest;
import com.riwi.librosya.domain.entity.User;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING )
public interface UserMapper {

    UserRequest entityToRequest(User user);

    
}
