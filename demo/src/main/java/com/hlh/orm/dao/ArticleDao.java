package com.hlh.orm.dao;

import com.hlh.orm.entity.Article;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ArticleDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public int saveArticle(Article article){
        String sql = "INSERT INTO article (author,title,content) VALUES (?,?,?) ";
        return jdbcTemplate.update(sql,
                article.getAuthor(),
                article.getTitle(),
                article.getContent());
    }

    public int deleteById(Integer id){
        return jdbcTemplate.update("DELETE FROM article WHERE id = ?",id);
    }

    public int updateArticle(Article article){
        String sql = "UPDATE article SET author = ?,title = ?,content = ?,create_time = ? WHERE id = ?";
        return jdbcTemplate.update(sql,
                article.getAuthor(),
                article.getTitle(),
                article.getContent(),
                article.getCreateTime(),
                article.getId()
                );
    }

    public Article findById(Integer id){
        String sql = "SELECT * FROM article WHERE id = ?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Article.class), id);
    }

    public List<Article> selectAll(){
        String sql = "SELECT * FROM article";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Article.class));
    }
}
