package com.example.personal_feed_service.domain.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_preferences")
public class UserPreference {
    @Id
    private Long userId;
    @ElementCollection
    private Set<String> preferredTags = new HashSet<>();
    @ElementCollection
    private Set<String> ignoredSources = new HashSet<>();

    public UserPreference(Long userId, Set<String> preferredTags, Set<String> ignoredSources) {
        this.userId = userId;
        this.preferredTags = preferredTags;
        this.ignoredSources = ignoredSources;
    }
}