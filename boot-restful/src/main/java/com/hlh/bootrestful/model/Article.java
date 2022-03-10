package com.hlh.bootrestful.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author hlh
 */
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
@JsonPropertyOrder(value = {"content", "title"})
public class Article {
    @JsonIgnore
    private Long id;

    private String title;

//    @JsonProperty("writer")
    private String author;

    private String content;

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private Date updateTime;
    private List<Reader> readerList;

}
