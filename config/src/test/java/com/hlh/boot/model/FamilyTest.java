package com.hlh.boot.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class FamilyTest {
    //自动注入springboot托管的java类
    @Autowired
    private Family family;

    @Test
    public void print(){
        System.out.println(family);
    }
}