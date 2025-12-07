package com.example.personal_feed_service.application.usecase;

import com.example.personal_feed_service.domain.model.NewsArticle;
import com.example.personal_feed_service.domain.repository.NewsArticleRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AdminNewsManagementService implements AdminNewsManagementUseCase {

    private final NewsArticleRepository newsRepository;

    public AdminNewsManagementService(NewsArticleRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    @Transactional
    @CacheEvict(value = "personalized_feed", allEntries = true)
    public NewsArticle createArticle(NewsArticle article) {
        article.setTimestamp(System.currentTimeMillis());
        return newsRepository.save(article);
    }

    @Override
    public Optional<NewsArticle> getArticle(Long id) {
        return newsRepository.findById(id);
    }

    @Override
    @Transactional
    @CacheEvict(value = "personalized_feed", allEntries = true)
    public NewsArticle updateArticle(NewsArticle article) {
        if (newsRepository.findById(article.getId()).isEmpty()) {
            throw new RuntimeException("Article not found");
        }
        return newsRepository.save(article);
    }

    @Override
    @Transactional
    @CacheEvict(value = "personalized_feed", allEntries = true)
    public void deleteArticle(Long id) {
        newsRepository.deleteById(id);
    }

    @Override
    public List<NewsArticle> getAllArticles() {
        return newsRepository.findAll();
    }
}