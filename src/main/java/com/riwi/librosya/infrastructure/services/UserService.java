package com.riwi.librosya.infrastructure.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.riwi.librosya.api.dto.request.UserRequest;
import com.riwi.librosya.api.dto.response.UserResponse;
import com.riwi.librosya.domain.entity.User;
import com.riwi.librosya.domain.repositories.UserRepository;
import com.riwi.librosya.infrastructure.abstract_service.IUserService;
import com.riwi.librosya.util.enums.SortType;
import com.riwi.librosya.util.exceptions.BadRequestException;
import com.riwi.librosya.util.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class UserService implements IUserService{

    /* Inyeccion dependencias */
        @Autowired
        private final UserRepository userRepository;

        @Autowired
        private final UserMapper userMapper;

        @Override
        public UserResponse create(UserRequest request) {
            
        User user = this.userMapper.requestToGetEntity(request);

        user.setLoans(new ArrayList<>());
        user.setReservations(new ArrayList<>());

        return this.userMapper.entityToGetResp(userRepository.save(user));
        }

        @SuppressWarnings("null")
        @Override
        public Page<UserResponse> getAll(int page, int size, SortType sortType) {
            if (page<0) page =0;

            PageRequest pagination = null;
                switch (sortType) {
                    case NONE -> pagination = PageRequest.of(page, size); 
                    case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending()); //organizar de forma ascendente por full name de user
                    case DESC -> pagination  = PageRequest.of(page, size,Sort.by(FIELD_BY_SORT).descending()); //organizar de forma descendente por nombre de user
                }
            return this.userRepository.findAll(pagination).map(user->userMapper.entityToGetResp(user));
        }

        @Override
        public UserResponse getById(Long id) {
            return this.userMapper.entityToGetResp(this.findUser(id));
        }

        @Override
        public UserResponse update(UserRequest request, Long id) {
            User user = this.findUser(id);

            User userUpdate = this.userMapper.requestToGetEntity(request);

            userUpdate.setLoans(user.getLoans());
            userUpdate.setReservations(user.getReservations());
            
            return this.userMapper.entityToGetResp(this.userRepository.save(userUpdate));
        }

        @Override
        public void delete(Long id) {
            this.userRepository.delete(this.findUser(id));
        }

        /*Funcion busqueda de usuario */
        private User findUser(Long id){
            return this.userRepository.findById(id).orElseThrow(()-> new BadRequestException(com.riwi.librosya.util.messages.ErrorMessage.idNotFound("User")));
        }
}
