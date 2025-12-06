package com.example.personal_feed_service.infrastructure.persistence.impl;

import com.example.personal_feed_service.domain.model.NewsArticle;
import com.example.personal_feed_service.domain.repository.NewsArticleRepository;
import com.example.personal_feed_service.infrastructure.persistence.jpa.NewsArticleJpaRepository;
import com.example.personal_feed_service.infrastructure.persistence.jpa.mapper.NewsArticleMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class NewsArticleRepositoryImpl implements NewsArticleRepository {

    private final NewsArticleJpaRepository jpaRepository;

    public NewsArticleRepositoryImpl(NewsArticleJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<NewsArticle> findTop100ByTags(Set<String> tags) {
        return jpaRepository.findByTagsIn(tags, PageRequest.of(0, 100)).stream()
                .map(NewsArticleMapper::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<NewsArticle> findById(Long id) {
        return jpaRepository.findById(id).map(NewsArticleMapper::toDomainModel);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public NewsArticle save(NewsArticle article) {
        return NewsArticleMapper.toDomainModel(jpaRepository.save(NewsArticleMapper.toEntity(article)));
    }

    @Override
    public List<NewsArticle> findAll() {
        return jpaRepository.findAll().stream()
                .map(NewsArticleMapper::toDomainModel)
                .collect(Collectors.toList());
    }
}