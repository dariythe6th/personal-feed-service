package com.example.personal_feed_service.infrastructure.persistence.impl;

import com.example.personal_feed_service.domain.model.User;
import com.example.personal_feed_service.domain.repository.UserRepository;
import com.example.personal_feed_service.infrastructure.persistence.jpa.UserJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository jpaRepository;

    public UserRepositoryImpl(UserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public User save(User user) {
        return jpaRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaRepository.findById(id);
    }
}