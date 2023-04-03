package com.marcin.courses.exception.course;

public enum CoursesError {
    COURSE_NOT_FOUND("courses not found"),
    COURSE_END_BEFORE_START("course must start before finish"),
    COURSE_IS_FULL("course have to many participants"),
    COURSE_MUST_BE_FULL("course still have some place"),
    COURSE_NOT_AVAILABLE("course is not available now"),
    COURSE_IS_ALREADY_INACTIVE("course is already inactive"),
    MEMBER_IS_ALREADY_JOIN_TO_COURSE("member already join to course");


    private final String message;

    CoursesError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
