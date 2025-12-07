package com.example.personal_feed_service.infrastructure.http;

import com.example.personal_feed_service.application.usecase.AdminNewsManagementUseCase;
import com.example.personal_feed_service.domain.model.NewsArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/articles")
public class AdminController {

    @Autowired
    private AdminNewsManagementUseCase adminUseCase;

    @PostMapping
    public NewsArticle create(@RequestBody NewsArticle article) {
        return adminUseCase.createArticle(article);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsArticle> get(@PathVariable Long id) {
        return adminUseCase.getArticle(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    public NewsArticle update(@RequestBody NewsArticle article) {
        return adminUseCase.updateArticle(article);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        adminUseCase.deleteArticle(id);
    }

    // Список всех статей
    @GetMapping
    public List<NewsArticle> getAll() {
        return adminUseCase.getAllArticles();
    }

}
