package com.example.personal_feed_service.infrastructure.persistence.impl;

import com.example.personal_feed_service.domain.model.UserActivity;
import com.example.personal_feed_service.domain.repository.UserActivityRepository;
import com.example.personal_feed_service.infrastructure.persistence.jpa.UserActivityJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserActivityRepositoryImpl implements UserActivityRepository {

    private final UserActivityJpaRepository jpaRepository;

    public UserActivityRepositoryImpl(UserActivityJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public UserActivity save(UserActivity activity) {
        return jpaRepository.save(activity);
    }

    @Override
    public List<UserActivity> findByArticleId(Long articleId) {
        return jpaRepository.findByArticleId(articleId);
    }
}