package com.example.personal_feed_service.domain.repository;

import com.example.personal_feed_service.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long id);
}