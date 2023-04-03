package com.marcin.courses.exception.student;

public enum StudentError {
   STUDENT_IS_INACTIVE("inactive student cant join to course"),
   STUDENT_CANNOT_BE_ENROLL("Student cannot be enroll on course");

    private final String message;

    StudentError(String message) {
        this.message=message;
    }
    public String getMessage() {
        return message;
    }
}
