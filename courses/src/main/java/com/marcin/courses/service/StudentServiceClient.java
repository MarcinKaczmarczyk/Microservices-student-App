package com.marcin.courses.service;


import com.marcin.courses.model.dto.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "STUDENT-SERVICE")
public interface StudentServiceClient {

    @GetMapping("/students")
    List<Student> getStudents();






}
