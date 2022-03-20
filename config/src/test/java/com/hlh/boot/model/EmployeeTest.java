//package com.hlh.boot.model;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import javax.annotation.Resource;
//
//import java.util.List;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//class EmployeeTest {
//
//
//    @Resource
//    private Employee employee;
//
//
//    @Test
//    void valueBing() throws Exception{
////        System.out.println(employee);
//
//        Map<String, Integer> map = employee.getEmployeeAge();
//        map.forEach((key,value) -> System.out.println(key + "->" + value));
//    }
//    @Test
//    public void testList(){
//        List<String> list = List.of("bbb", "ccc", "aaa");
//        list.forEach(System.out::println);
//    }
//
//}