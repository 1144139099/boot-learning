package com.hlh.bootrt.controller;

import com.hlh.bootrt.entity.Article;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/template")
public class TemplateController {
    @GetMapping("/freemarker")
    public String freemarkerindex(Model model){
        List<Article> articles = Arrays.asList(
                new Article(1, "张三", "java")
                , new Article(2, "张思", "spring")
                , new Article(3, "张无", "springboot")
        );
        model.addAttribute("articles", articles);
        System.out.println(articles);
        return "freemarker-demo";
    }
    @GetMapping("/thymeleaf")
    public String thymeleafindex(Model model){
        List<Article> articles = Arrays.asList(
                new Article(1, "张三", "java")
                , new Article(2, "张思", "spring")
                , new Article(3, "张无", "springboot")
        );
        model.addAttribute("articles", articles);
        System.out.println(articles);
        return "thymeleaf-demo";
    }
}
