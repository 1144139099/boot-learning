package com.hlh.orm.controller;

import com.hlh.orm.common.AjaxResponse;
import com.hlh.orm.entity.Article;
import com.hlh.orm.service.ArticleService;
import com.hlh.orm.service.impl.ArticleJpaServicelmpl;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/articles")

public class ArticleController {
    @Resource
    private ArticleJpaServicelmpl articleJpaService;

    @GetMapping(value = "/all")
    public AjaxResponse selectAll(){

        return AjaxResponse.success(articleJpaService.selectAll());
    }

    @PostMapping()
    public AjaxResponse addArticle(@RequestBody Article article){
        System.err.println(article + "-----------");
        return AjaxResponse.success(articleJpaService.saveArticle(article));
    }

    @PutMapping()
    public AjaxResponse updateArticle(@RequestBody Article article){
        if (articleJpaService.saveArticle(article) != null){
            return AjaxResponse.success("修改成功");
        }else {
            return AjaxResponse.failure();
        }
    }

    @GetMapping("{id}")
    public AjaxResponse findById(@PathVariable int id){
        return AjaxResponse.success(articleJpaService.findById(id));
    }
}
