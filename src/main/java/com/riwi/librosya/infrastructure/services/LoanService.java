package com.riwi.librosya.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.riwi.librosya.api.dto.request.LoanRequest;
import com.riwi.librosya.api.dto.response.LoanResponse;
import com.riwi.librosya.domain.entity.Loan;
import com.riwi.librosya.domain.repositories.LoanRepository;
import com.riwi.librosya.infrastructure.abstract_service.ILoanService;
import com.riwi.librosya.util.enums.SortType;
import com.riwi.librosya.util.mapper.LoanMapper;
import com.riwi.librosya.util.messages.ErrorMessage;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class LoanService implements ILoanService{
    
    /*inyecciones */
    @Autowired
        private final LoanRepository loanRepository;

        @Autowired
        private final LoanMapper loanMapper;



        @Override
        public LoanResponse create(LoanRequest request) {
            Loan loan = this.loanMapper.requestToGetEntity(request);

            return this.loanMapper.entityToGetResp(loanRepository.save(loan));
        }
        
        @SuppressWarnings("null")
        @Override
        public Page<LoanResponse> getAll(int page, int size, SortType sortType) {
            if (page<0) page =0;

                PageRequest pagination = null;
                    switch (sortType) {
                        case NONE -> pagination = PageRequest.of(page, size); 
                        case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
                        case DESC -> pagination  = PageRequest.of(page, size,Sort.by(FIELD_BY_SORT).descending());
                    }
            
            return this.loanRepository.findAll(pagination).map(loan -> loanMapper.entityToGetResp(loan));
        }
        

        @Override
        public LoanResponse getById(Long id) {
            return this.loanMapper.entityToGetResp(this.findLoan(id));
        }

        @Override
        public LoanResponse update(LoanRequest request, Long id) {

            Loan loanUpdate = this.loanMapper.requestToGetEntity(request);

            return this.loanMapper.entityToGetResp(this.loanRepository.save(loanUpdate));
        }

        @Override
        public void delete(Long id) {
            this.loanRepository.delete(this.findLoan(id));
        }


        private Loan findLoan(Long id){
            return this.loanRepository.findById(id).orElseThrow(()-> new com.riwi.librosya.util.exceptions.BadRequestException(ErrorMessage.idNotFound("loan")));
        }
}
