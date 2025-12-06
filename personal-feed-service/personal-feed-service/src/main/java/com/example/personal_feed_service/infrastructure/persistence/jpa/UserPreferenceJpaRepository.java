package com.example.personal_feed_service.infrastructure.persistence.jpa;

import com.example.personal_feed_service.domain.model.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPreferenceJpaRepository extends JpaRepository<UserPreference, Long> {
}