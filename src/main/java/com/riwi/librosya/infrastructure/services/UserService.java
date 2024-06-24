package com.riwi.librosya.infrastructure.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.riwi.librosya.api.dto.request.UserRequest;
import com.riwi.librosya.api.dto.response.UserBasicResponse;
import com.riwi.librosya.api.dto.response.UserResponse;
import com.riwi.librosya.domain.entity.User;
import com.riwi.librosya.domain.repositories.UserRepository;
import com.riwi.librosya.mapper.UserMapper;

public class UserService {
     @Autowired
    private UserRepository userRepository;

    public UserResponse createUser(UserRequest userRequest) {
        User user = UserMapper.INSTANCE.toEntity(userRequest);
        user = userRepository.save(user);
        return UserMapper.INSTANCE.toResponse(user);
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.INSTANCE.toResponse(user);
    }

    public List<UserBasicResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper.INSTANCE::toBasicResponse)
                .collect(Collectors.toList());
    }

    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user = userRepository.save(user);
        return UserMapper.INSTANCE.toResponse(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
