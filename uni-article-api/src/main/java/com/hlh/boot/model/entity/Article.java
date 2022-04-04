package com.hlh.boot.model.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName t_article
 */
@Data
public class Article implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 浏览次数
     */
    private Integer pageView;

    /**
     * 阅读时间
     */
    private Integer duration;

    /**
     * 总字数
     */
    private String totalWords;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 文章url
     */
    private String url;

    /**
     * 内容
     */
    private String content;

    /**
     * 封面图
     */
    private String cover;

    /**
     * 标题
     */
    private String title;

    /**
     * 作者id
     */
    private Integer userId;

    /**
     * 分类
     */
    private String category;

    private static final long serialVersionUID = 1L;
}