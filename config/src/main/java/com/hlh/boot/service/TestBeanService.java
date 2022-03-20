package com.hlh.boot.service;

import lombok.Data;

@Data
public class TestBeanService {
    private String name;
    public TestBeanService(String name){
        this.name = name;
    }
    public TestBeanService(){

    }
}
