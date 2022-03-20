package com.hlh.orm.service;

import com.hlh.orm.entity.Article;

import java.util.List;

public interface ArticleService {




        void deleteById(int id);

        Article saveArticle(Article article);



        void updateArticle(Article article);



        Article findById(int id);



        List<Article> selectAll();

        List<Article> findByAuthor(String author);

}
