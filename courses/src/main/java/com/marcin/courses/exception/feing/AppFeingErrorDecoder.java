package com.marcin.courses.exception.feing;//package com.marcin.courses.exception.feing;
//
//import com.marcin.courses.exception.student.StudentError;
//import com.marcin.courses.exception.student.StudentException;
//import feign.Response;
//import feign.codec.ErrorDecoder;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//
//@Component
//public class AppFeingErrorDecoder implements ErrorDecoder {
//
//    private final ErrorDecoder defaultErrorDecoder=new Default();
//    @Override
//    public Exception decode(String s, Response response) {
//        if (HttpStatus.valueOf(response.status()).is4xxClientError()){
//            throw new StudentException(StudentError.STUDENT_CANNOT_BE_ENROLL);
//        }
//        return null;
//    }
//}
