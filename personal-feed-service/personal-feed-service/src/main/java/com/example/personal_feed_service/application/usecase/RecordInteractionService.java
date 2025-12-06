package com.example.personal_feed_service.application.usecase;

import com.example.personal_feed_service.domain.model.UserActivity;
import com.example.personal_feed_service.domain.repository.UserActivityRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class RecordInteractionService implements RecordInteractionUseCase {

    private final UserActivityRepository activityRepository;

    public RecordInteractionService(UserActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    @CacheEvict(value = "personalized_feed", allEntries = true)
    public UserActivity record(Long userId, Long articleId, UserActivity.ActivityType type) {
        UserActivity activity = new UserActivity();
        activity.setUserId(userId);
        activity.setArticleId(articleId);
        activity.setType(type);
        activity.setTimestamp(System.currentTimeMillis());
        return activityRepository.save(activity);
    }
}