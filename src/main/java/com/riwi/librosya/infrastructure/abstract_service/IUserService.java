package com.riwi.librosya.infrastructure.abstract_service;

import com.riwi.librosya.api.dto.request.UserRequest;
import com.riwi.librosya.api.dto.response.UserResponse;

public interface IUserService extends CRUDService<UserRequest, UserResponse, Long> {
     public final String FIELD_BY_SORT = "loan_date";
}