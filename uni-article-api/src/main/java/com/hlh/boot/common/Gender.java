package com.hlh.boot.common;

/**
 * @author hlh
 */

public enum Gender {
    DEFAULT(-1, "默认"),

    MALE(0, "男"),

    FEMALE(1, "女"),

    SECRET(2, "保密");

    private int key;
    private String value;
    Gender(int key, String value) {
        this.key = key;
        this.value = value;
    }
    public static Gender getValue(int key) {
        for (Gender gender : Gender.values()) {
            if (gender.getKey() == key) {
                return gender;
            }
        }
        return DEFAULT;
    }
    public int getKey() {
        return key;
    }
    public void setKey(int key) {
        this.key = key;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}



