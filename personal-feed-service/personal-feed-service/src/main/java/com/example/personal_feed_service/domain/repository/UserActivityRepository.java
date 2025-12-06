package com.example.personal_feed_service.domain.repository;

import com.example.personal_feed_service.domain.model.UserActivity;

import java.util.List;

public interface UserActivityRepository {
    UserActivity save(UserActivity activity);
    List<UserActivity> findByArticleId(Long articleId);
}