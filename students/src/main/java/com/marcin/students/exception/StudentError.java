package com.marcin.students.exception;

public enum StudentError {

    STUDENT_NOT_FOUND("Student does not exists"),
    STUDENT_EMAIL_ALREADY_EXISTS("Student email already exists"),
    STUDENT_IS_INACTIVE("student is inactive"),
    EMPTY_ARRAY("cannot process empty array");

    private final String message;

    StudentError(String message) {
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

}
