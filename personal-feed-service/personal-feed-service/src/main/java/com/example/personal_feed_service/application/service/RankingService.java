package com.example.personal_feed_service.application.service;

import com.example.personal_feed_service.domain.model.NewsArticle;
import com.example.personal_feed_service.domain.model.UserActivity;
import com.example.personal_feed_service.domain.model.UserPreference;
import com.example.personal_feed_service.domain.repository.UserActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RankingService {

    private static final double W1_TAG_MATCH = 0.5;
    private static final double W2_FRESHNESS = 0.3;
    private static final double W3_POPULARITY = 0.2;
    private static final double FRESHNESS_DECAY_RATE = 0.00000000001;

    @Autowired
    private UserActivityRepository userActivityRepository;

    public List<NewsArticle> rankAndGetTopN(
            List<NewsArticle> articles,
            UserPreference preferences,
            int topN) {
        List<ArticleScore> scoredArticles = articles.stream()
                .map(article -> {
                    double r = calculateRankingScore(article, preferences);
                    return new ArticleScore(article, r);
                })
                .collect(Collectors.toList());

        scoredArticles.sort(Comparator.comparingDouble(ArticleScore::score).reversed());

        return scoredArticles.stream()
                .limit(topN)
                .map(ArticleScore::article)
                .collect(Collectors.toList());
    }

    private double calculateRankingScore(NewsArticle article, UserPreference preferences) {
        double tagMatchScore = calculateTagMatch(article.getTags(), preferences.getPreferredTags());

        double freshnessScore = calculateFreshness(article.getTimestamp());

        List<UserActivity> activities = userActivityRepository.findByArticleId(article.getId());
        long likes = activities.stream().filter(a -> a.getType() == UserActivity.ActivityType.LIKE).count();
        long views = activities.stream().filter(a -> a.getType() == UserActivity.ActivityType.VIEW).count();
        double popularityScore = Math.min(1.0, (likes + views * 0.5) / 100.0);

        return W1_TAG_MATCH * tagMatchScore +
                W2_FRESHNESS * freshnessScore +
                W3_POPULARITY * popularityScore;
    }

    private double calculateTagMatch(Set<String> articleTags, Set<String> preferredTags) {
        if (preferredTags == null || preferredTags.isEmpty()) return 0.0;
        long matchCount = articleTags.stream().filter(preferredTags::contains).count();
        return (double) matchCount / preferredTags.size();
    }

    private double calculateFreshness(long articleTimestamp) {
        long now = Instant.now().toEpochMilli();
        long timeDiff = now - articleTimestamp;
        return Math.exp(-FRESHNESS_DECAY_RATE * timeDiff);
    }

    private record ArticleScore(NewsArticle article, double score) {}
}
