package com.example.personal_feed_service.application.usecase;

import com.example.personal_feed_service.application.service.RankingService;
import com.example.personal_feed_service.domain.model.NewsArticle;
import com.example.personal_feed_service.domain.model.UserPreference;
import com.example.personal_feed_service.domain.repository.NewsArticleRepository;
import com.example.personal_feed_service.domain.repository.UserPreferenceRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Cacheable(value = "personalized_feed", key = "#userId")
public class GetPersonalizedFeedService implements GetPersonalizedFeedUseCase {

    private final NewsArticleRepository newsRepository;
    private final UserPreferenceRepository preferenceRepository;
    private final RankingService rankingService;

    public GetPersonalizedFeedService(NewsArticleRepository newsRepository,
                                      UserPreferenceRepository preferenceRepository,
                                      RankingService rankingService) {
        this.newsRepository = newsRepository;
        this.preferenceRepository = preferenceRepository;
        this.rankingService = rankingService;
    }

    @Override
    @Cacheable(value = "personalized_feed", key = "#userId")
    public List<NewsArticle> getFeed(Long userId, int topN) {
        Optional<UserPreference> optPrefs = preferenceRepository.findByUserId(userId);
        if (optPrefs.isEmpty()) {
            return newsRepository.findAll().subList(0, Math.min(topN, newsRepository.findAll().size()));
        }
        UserPreference prefs = optPrefs.get();
        List<NewsArticle> candidateArticles = newsRepository.findTop100ByTags(prefs.getPreferredTags());
        return rankingService.rankAndGetTopN(candidateArticles, prefs, topN);
    }
}