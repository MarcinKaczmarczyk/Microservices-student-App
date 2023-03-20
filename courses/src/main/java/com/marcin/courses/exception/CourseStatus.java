package com.marcin.courses.exception;

public enum CourseStatus {

    ACTIVE("A"),
    INACTIVE("I"),
    FULL("F");

    private final String info;

    CourseStatus(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

}
