package com.example.personal_feed_service.infrastructure.persistence.jpa;

import com.example.personal_feed_service.infrastructure.persistence.jpa.NewsArticleEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface NewsArticleJpaRepository extends JpaRepository<NewsArticleEntity, Long> {
    List<NewsArticleEntity> findByTagsIn(Set<String> tags, Pageable pageable);
}