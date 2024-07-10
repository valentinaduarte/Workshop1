package com.riwi.librosya.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.riwi.librosya.api.dto.request.ReservationRequest;
import com.riwi.librosya.api.dto.response.ReservationResponse;
import com.riwi.librosya.domain.entity.Reservation;
import com.riwi.librosya.domain.repositories.ReservationRepository;
import com.riwi.librosya.infrastructure.abstract_service.IReservationService;
import com.riwi.librosya.util.enums.SortType;
import com.riwi.librosya.util.exceptions.BadRequestException;
import com.riwi.librosya.util.mapper.ReservationMapper;
import com.riwi.librosya.util.messages.ErrorMessage;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class ReservationService implements IReservationService {
    
    /* inyeccion de dependencias */
        @Autowired
        private final ReservationRepository reservationRepository;

        @Autowired 
        private final ReservationMapper reservationMapper;

    /* CRUD */
        @Override
        public ReservationResponse create(ReservationRequest request) {

            Reservation reservation = this.reservationMapper.requestToGetEntity(request);

            return this.reservationMapper.entityToGetResp(reservationRepository.save(reservation));
        }

        @SuppressWarnings("null")
        @Override
        public Page<ReservationResponse> getAll(int page, int size, SortType sortType) {
            if (page<0) page =0;

            PageRequest pagination = null;
                switch (sortType) {
                    case NONE -> pagination = PageRequest.of(page, size); 
                    case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
                    case DESC -> pagination  = PageRequest.of(page, size,Sort.by(FIELD_BY_SORT).descending()); 
                }
            return this.reservationRepository.findAll(pagination).map(reservation -> reservationMapper.entityToGetResp(reservation));
        }
        
        @Override
        public ReservationResponse getById(Long id) {
            return this.reservationMapper.entityToGetResp(this.findReservation(id));
        }

        @Override
        public ReservationResponse update(ReservationRequest request, Long id) {
  
            Reservation reservationUpdate = this.reservationMapper.requestToGetEntity(request);

            return this.reservationMapper.entityToGetResp(this.reservationRepository.save(reservationUpdate));
        }

        @Override
        public void delete(Long id) {
           this.reservationRepository.delete(this.findReservation(id));
        }
    /*funciones propias */
        private Reservation findReservation(Long id){
            return this.reservationRepository.findById(id).orElseThrow(()-> new BadRequestException(ErrorMessage.idNotFound("Reservation")));
        }

}
