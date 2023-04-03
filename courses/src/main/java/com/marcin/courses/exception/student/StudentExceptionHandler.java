package com.marcin.courses.exception.student;

import com.marcin.courses.exception.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StudentExceptionHandler {

    @ExceptionHandler(value = StudentException.class)
    public ResponseEntity<ErrorInfo> exceptionHandler(StudentException e){
        HttpStatus httpStatus=HttpStatus.INTERNAL_SERVER_ERROR;
        if (StudentError.STUDENT_IS_INACTIVE.equals(e.getStudentError())){
            httpStatus=HttpStatus.CONFLICT;
        }else if (StudentError.STUDENT_CANNOT_BE_ENROLL.equals(e.getStudentError())){
            httpStatus=HttpStatus.BAD_REQUEST;
        }
       return ResponseEntity.status(httpStatus).body(new ErrorInfo(e.getStudentError().getMessage()));
    }
}
