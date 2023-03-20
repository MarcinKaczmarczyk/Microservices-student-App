package com.marcin.courses.exception;

public enum CoursesError {
    COURSE_NOT_FOUND("courses not found"),
    COURSE_END_BEFORE_START("course must start before finish"),
    COURSE_IS_FULL("course have to many participants"),
    COURSE_MUST_BE_FULL("course still have some place");

    private String message;

    CoursesError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
