package com.example.personal_feed_service.infrastructure.persistence.jpa;

import com.example.personal_feed_service.domain.model.UserActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserActivityJpaRepository extends JpaRepository<UserActivity, Long> {
    List<UserActivity> findByArticleId(Long articleId);
}