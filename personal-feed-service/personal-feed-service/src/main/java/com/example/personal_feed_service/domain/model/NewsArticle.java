package com.example.personal_feed_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.io.Serializable;   // ← ДОБАВЬ ЭТОТ ИМПОРТ

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsArticle implements Serializable {   // ← И ЭТО!
    private static final long serialVersionUID = 1L;  // ← Лучше добавить

    private Long id;
    private String title;
    private String content;
    private Set<String> tags;
    private long timestamp;
}