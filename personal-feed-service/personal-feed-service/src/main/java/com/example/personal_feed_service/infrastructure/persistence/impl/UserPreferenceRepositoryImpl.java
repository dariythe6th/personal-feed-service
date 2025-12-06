package com.example.personal_feed_service.infrastructure.persistence.impl;

import com.example.personal_feed_service.domain.model.UserPreference;
import com.example.personal_feed_service.domain.repository.UserPreferenceRepository;
import com.example.personal_feed_service.infrastructure.persistence.jpa.UserPreferenceJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserPreferenceRepositoryImpl implements UserPreferenceRepository {

    private final UserPreferenceJpaRepository jpaRepository;

    public UserPreferenceRepositoryImpl(UserPreferenceJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<UserPreference> findByUserId(Long userId) {
        return jpaRepository.findById(userId);
    }

    @Override
    public UserPreference save(UserPreference preference) {
        return jpaRepository.save(preference);
    }
}