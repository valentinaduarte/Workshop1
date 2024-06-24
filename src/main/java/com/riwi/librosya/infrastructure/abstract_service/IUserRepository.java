package com.riwi.librosya.infrastructure.abstract_service;


import com.riwi.librosya.api.dto.request.UserRequest;
import com.riwi.librosya.api.dto.response.UserResponse;



public interface IUserRepository extends CRUDService<UserRequest,UserResponse, Long> {
    
}