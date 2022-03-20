package com.hlh.orm.dao;

import com.hlh.orm.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    List<Article> findByAuthor(String author);
}
