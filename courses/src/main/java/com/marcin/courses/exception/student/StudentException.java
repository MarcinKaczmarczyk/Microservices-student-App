package com.marcin.courses.exception.student;

public class StudentException extends RuntimeException{
    private final StudentError studentError;

    public StudentException(StudentError studentError) {
        this.studentError = studentError;
    }

    public StudentError getStudentError() {
        return studentError;
    }
}
