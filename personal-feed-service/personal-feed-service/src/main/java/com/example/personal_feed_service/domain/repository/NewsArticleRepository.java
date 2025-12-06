package com.example.personal_feed_service.domain.repository;

import com.example.personal_feed_service.domain.model.NewsArticle;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface NewsArticleRepository {
    List<NewsArticle> findTop100ByTags(Set<String> tags);
    Optional<NewsArticle> findById(Long id);
    void deleteById(Long id);
    NewsArticle save(NewsArticle article);
    List<NewsArticle> findAll();
}