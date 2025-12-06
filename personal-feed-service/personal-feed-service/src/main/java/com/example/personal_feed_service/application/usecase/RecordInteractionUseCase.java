package com.example.personal_feed_service.application.usecase;

import com.example.personal_feed_service.domain.model.UserActivity;

public interface RecordInteractionUseCase {
    UserActivity record(Long userId, Long articleId, UserActivity.ActivityType type);
}