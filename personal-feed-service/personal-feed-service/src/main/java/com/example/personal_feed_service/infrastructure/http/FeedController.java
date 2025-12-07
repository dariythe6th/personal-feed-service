// FeedController.java
package com.example.personal_feed_service.infrastructure.http;

import com.example.personal_feed_service.application.usecase.GetPersonalizedFeedUseCase;
import com.example.personal_feed_service.application.usecase.RecordInteractionUseCase;
import com.example.personal_feed_service.domain.model.NewsArticle;
import com.example.personal_feed_service.domain.model.UserActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feed")
public class FeedController {

    @Autowired private GetPersonalizedFeedUseCase feedUseCase;
    @Autowired private RecordInteractionUseCase interactionUseCase;

    @GetMapping("/{userId}")
    public List<NewsArticle> getFeed(@PathVariable Long userId) {
        return feedUseCase.getFeed(userId, 20);
    }

    @PostMapping("/{userId}/view/{articleId}")
    public void recordView(@PathVariable Long userId, @PathVariable Long articleId) {
        interactionUseCase.record(userId, articleId, UserActivity.ActivityType.VIEW);
    }

    @PostMapping("/{userId}/like/{articleId}")
    public void recordLike(@PathVariable Long userId, @PathVariable Long articleId) {
        interactionUseCase.record(userId, articleId, UserActivity.ActivityType.LIKE);
    }
}