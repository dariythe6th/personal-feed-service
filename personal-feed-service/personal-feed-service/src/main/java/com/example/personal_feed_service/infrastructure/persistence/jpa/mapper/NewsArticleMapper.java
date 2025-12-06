package com.example.personal_feed_service.infrastructure.persistence.jpa.mapper;

import com.example.personal_feed_service.domain.model.NewsArticle;
import com.example.personal_feed_service.infrastructure.persistence.jpa.NewsArticleEntity;

import java.time.Instant;
import java.util.HashSet;

public class NewsArticleMapper {

    public static NewsArticle toDomainModel(NewsArticleEntity entity) {
        if (entity == null) return null;
        return NewsArticle.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .tags(new HashSet<>(entity.getTags()))
                .timestamp(entity.getPublishedAt().toEpochMilli())
                .build();
    }

    public static NewsArticleEntity toEntity(NewsArticle model) {
        if (model == null) return null;
        NewsArticleEntity entity = new NewsArticleEntity();
        entity.setId(model.getId());
        entity.setTitle(model.getTitle());
        entity.setContent(model.getContent());
        entity.setTags(new HashSet<>(model.getTags()));
        entity.setPublishedAt(Instant.ofEpochMilli(model.getTimestamp()));
        return entity;
    }
}