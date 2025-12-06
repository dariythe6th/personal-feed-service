package com.example.personal_feed_service.infrastructure.persistence.jpa;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "news_articles")
@Data
public class NewsArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ElementCollection
    @CollectionTable(name = "article_tags", joinColumns = @JoinColumn(name = "article_id"))
    @Column(name = "tag")
    private Set<String> tags = new HashSet<>();

    private Instant publishedAt;

}