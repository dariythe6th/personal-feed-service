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
import java.util.List;
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
            // Создаём тестового пользователя
            User user = userRepo.findById(1L).orElseGet(() -> {
                User u = new User(null, "gamer2025", "gamer@mail.ru", "pass");
                return userRepo.save(u);
            });

            // Настраиваем его предпочтения — любит игры!
            UserPreference pref = prefRepo.findByUserId(user.getId()).orElseGet(() -> {
                Set<String> tags = Set.of("игры", "gaming", "ps5", "pc", "cyberpunk", "gta", "elden ring", "steam");
                UserPreference p = new UserPreference(user.getId(), new HashSet<>(tags), new HashSet<>());
                return prefRepo.save(p);
            });

            // Только если новостей меньше 5 — закидываем большую порцию игровых новостей
            if (articleRepo.findAll().size() < 5) {
                List.of(
                        "CD Projekt RED анонсировала The Witcher 4 на Unreal Engine 5",
                        "GTA VI — первый трейлер побил все рекорды YouTube",
                        "Elden Ring: Shadow of the Erdtree — дата выхода и новые боссы",
                        "Steam Deck 2 уже мощнее PS4 Pro — Valve опубликовала тесты",
                        "Cyberpunk 2077 Phantom Liberty продался тиражом 8 млн копий",
                        "Black Myth: Wukong — китайский шедевр на Unreal Engine 5",
                        "Starfield получит официальную поддержку модов в 2025 году",
                        "Hogwarts Legacy 2 уже в разработке — инсайд от Bloomberg",
                        "Palworld обогнал Counter-Strike по онлайну в Steam",
                        "Nintendo Switch 2 покажут в марте 2025 — надёжные источники",
                        "Half-Life 3 существует! Valve случайно слила арты",
                        "S.T.A.L.K.E.R. 2: Heart of Chornobyl наконец-то вышла без багов",
                        "Baldur’s Gate 3 выиграла все награды года — снова",
                        "Minecraft достиг 1 триллиона просмотров на YouTube",
                        "Fortnite x Metallica — новый сезон уже в игре",
                        "Resident Evil 9 будет в открытом мире — Capcom подтвердила",
                        "Genshin Impact 5.0 — новый регион Natlan и драконы",
                        "Assassin’s Creed Shadows перенесена на 2025 год",
                        "DOOM: The Dark Ages — средневековый думгай на фоне замков",
                        "Silent Hill 2 Remake — первые оценки 96/100 на Metacritic"
                ).forEach(title -> {
                    Set<String> tags = generateTags(title);
                    articleRepo.save(NewsArticle.builder()
                            .title(title)
                            .content("Подробности в полной статье. Игра уже в топе ожиданий 2025 года! " +
                                    "Разработчики обещают революционный геймплей, фотореалистичную графику и открытый мир.")
                            .tags(tags)
                            .timestamp(System.currentTimeMillis() - (long)(Math.random() * 7 * 24 * 60 * 60 * 1000)) // до недели назад
                            .build());
                });

                System.out.println("Добавлено 20 игровых новостей! Открой http://localhost:8080");
            }
        };
    }

    // Вспомогательный метод — рандомные теги на основе заголовка
    private Set<String> generateTags(String title) {
        Set<String> tags = new HashSet<>(Set.of("игры", "gaming"));
        String lower = title.toLowerCase();
        if (lower.contains("witcher")) tags.addAll(Set.of("rpg", "witcher"));
        if (lower.contains("gta")) tags.addAll(Set.of("gta", "rockstar", "open world"));
        if (lower.contains("elden")) tags.addAll(Set.of("elden ring", "soulslike", "fromsoftware"));
        if (lower.contains("cyberpunk")) tags.addAll(Set.of("cyberpunk", "cdpr", "rpg"));
        if (lower.contains("steam")) tags.add("steam");
        if (lower.contains("ps5")) tags.add("ps5");
        if (lower.contains("nintendo")) tags.add("nintendo");
        if (lower.contains("pc")) tags.add("pc");
        if (lower.contains("aaa")) tags.add("aaa");
        return tags;
    }
}