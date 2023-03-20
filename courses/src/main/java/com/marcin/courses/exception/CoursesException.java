package com.marcin.courses.exception;

public class CoursesException extends RuntimeException{
    private CoursesError coursesError;

   public CoursesException(CoursesError coursesError){
       this.coursesError=coursesError;
   }

    public CoursesError getCoursesError() {
        return coursesError;
    }
}
