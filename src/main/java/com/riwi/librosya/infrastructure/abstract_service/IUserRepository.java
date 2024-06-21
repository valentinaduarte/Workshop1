package com.riwi.librosya.infrastructure.abstract_service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.riwi.librosya.domain.entity.User;


public interface IUserRepository extends JpaRepository<User, Long> {
    
}