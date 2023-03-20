package com.marcin.courses.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CourseExceptionHandler {

    @ExceptionHandler(value = CoursesException.class)
    public ResponseEntity<ErrorInfo> exceptionHandler(CoursesException e){
        HttpStatus httpStatus= HttpStatus.INTERNAL_SERVER_ERROR;
        if (CoursesError.COURSE_NOT_FOUND.equals(e.getCoursesError())){
            httpStatus=HttpStatus.NOT_FOUND;
        }else if(CoursesError.COURSE_MUST_BE_FULL.equals(e.getCoursesError())||
                CoursesError.COURSE_IS_FULL.equals(e.getCoursesError())||
                CoursesError.COURSE_END_BEFORE_START.equals(e.getCoursesError())){
            httpStatus=HttpStatus.CONFLICT;
        }
        return ResponseEntity.status(httpStatus).body(new ErrorInfo(e.getCoursesError().getMessage()));
    }
}
