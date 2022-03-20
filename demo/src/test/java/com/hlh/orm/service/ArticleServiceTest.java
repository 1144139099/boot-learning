package com.hlh.orm.service;

import com.hlh.orm.entity.Article;
import lombok.Builder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
//@Builder
@SpringBootTest
@ExtendWith(SpringExtension.class)
class ArticleServiceTest {
    @Resource
    private ArticleService articleService;

    @Test
    void save() {
    }

    @Test
    void deleteById() {
    }

    @Test
    public void saveArticle() {
        Article article = Article.builder()
                .id(1003)
                .author("qzk")
                .title("java")
                .content("Java从入门到精通")
                .thumbnail("1.jpg")
                .build();
        Article saveArticle = articleService.saveArticle(article);
        assertNotNull(saveArticle);

    }

    @Test
    void updateById() {
    }

    @Test
    void findById() {
    }

    @Test
    void selectAll() {
    }
}