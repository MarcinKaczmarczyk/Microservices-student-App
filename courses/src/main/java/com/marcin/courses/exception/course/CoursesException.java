package com.marcin.courses.exception.course;

public class CoursesException extends RuntimeException{
    private final CoursesError coursesError;

   public CoursesException(CoursesError coursesError){
       this.coursesError=coursesError;
   }

    public CoursesError getCoursesError() {
        return coursesError;
    }
}
