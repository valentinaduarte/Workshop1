package com.riwi.librosya.infrastructure.abstract_service;

import com.riwi.librosya.api.dto.request.LoanRequest;
import com.riwi.librosya.api.dto.response.LoanResponse;

public interface ILoanService extends CRUDService<LoanRequest, LoanResponse, Long> {
    public final String FIELD_BY_SORT = "full_name"; /*Variable a utilizar para la paginacion */

}
