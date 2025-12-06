package com.example.personal_feed_service.domain.repository;

import com.example.personal_feed_service.domain.model.UserPreference;

import java.util.Optional;

public interface UserPreferenceRepository {
    Optional<UserPreference> findByUserId(Long userId);
    UserPreference save(UserPreference preference);
}