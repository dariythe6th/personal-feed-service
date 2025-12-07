package com.example.personal_feed_service.application.usecase;

import com.example.personal_feed_service.domain.model.NewsArticle;

import java.util.List;
import java.util.Optional;

public interface AdminNewsManagementUseCase {
    NewsArticle createArticle(NewsArticle article);
    Optional<NewsArticle> getArticle(Long id);
    NewsArticle updateArticle(NewsArticle article);
    void deleteArticle(Long id);
    List<NewsArticle> getAllArticles(); // ← новый метод
}