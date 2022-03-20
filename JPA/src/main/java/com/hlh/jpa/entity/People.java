package com.hlh.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "people")
public class People {
    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private int age;
}
