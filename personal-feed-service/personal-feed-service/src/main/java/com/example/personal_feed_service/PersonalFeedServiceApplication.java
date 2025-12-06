package com.example.personal_feed_service;

import com.example.personal_feed_service.domain.model.NewsArticle;
import com.example.personal_feed_service.domain.model.User;
import com.example.personal_feed_service.domain.model.UserPreference;
import com.example.personal_feed_service.domain.repository.NewsArticleRepository;
import com.example.personal_feed_service.domain.repository.UserPreferenceRepository;
import com.example.personal_feed_service.domain.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableCaching
@ComponentScan(basePackages = "com.example.personal_feed_service")
public class PersonalFeedServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonalFeedServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(UserRepository userRepo,
                               UserPreferenceRepository prefRepo,
                               NewsArticleRepository articleRepo) {
        return args -> {
            User user = new User(null, "testuser", "test@email.com", "password");
            userRepo.save(user);

            Set<String> tags = new HashSet<>();
            tags.add("tech");
            tags.add("news");
            UserPreference pref = new UserPreference(user.getId(), tags, new HashSet<>());
            prefRepo.save(pref);

            articleRepo.save(NewsArticle.builder()
                    .title("Tech News 1")
                    .content("Content 1")
                    .tags(tags)
                    .timestamp(System.currentTimeMillis())
                    .build());

            articleRepo.save(NewsArticle.builder()
                    .title("Other News")
                    .content("Content 2")
                    .tags(new HashSet<>(Set.of("sports")))
                    .timestamp(System.currentTimeMillis() - 3600000)
                    .build());

            System.out.println("Test data initialized. Access /feed/1 for user 1 feed.");
        };
    }
}