package com.hlh.boot.model;

import lombok.Data;

import java.util.List;
@Data
public class Child {
    private String name;
    private Integer age;
    private List<Friend> friend;

}
