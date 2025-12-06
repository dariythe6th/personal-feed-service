package com.example.personal_feed_service.infrastructure.persistence.jpa;

import com.example.personal_feed_service.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
}