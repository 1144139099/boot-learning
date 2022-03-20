package com.example.demo.dao;

import com.example.demo.entity.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class ArticleDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public int save(Article article){
        String sql = "INSERT INTO article (author,title,content,create_time) VALUES (?,?,?,?) ";
        return jdbcTemplate.update(sql, article.getAuthor(), article.getTitle(), article.getContent(), article.getCreateTime());

    }
}
