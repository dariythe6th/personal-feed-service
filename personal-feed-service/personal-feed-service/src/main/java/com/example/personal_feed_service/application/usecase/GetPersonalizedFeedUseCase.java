package com.example.personal_feed_service.application.usecase;

import com.example.personal_feed_service.domain.model.NewsArticle;

import java.util.List;

public interface GetPersonalizedFeedUseCase {
    List<NewsArticle> getFeed(Long userId, int topN);
}