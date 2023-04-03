package com.marcin.courses.service;


import com.marcin.courses.model.dto.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "STUDENT-SERVICE")
public interface StudentServiceClient {

    @GetMapping("/students")
    List<Student> getStudents();

    @GetMapping("/students/{id}")
    Student getStudent(@PathVariable Long id);

    @PostMapping("/students/emails")
    List<Student> getStudentsByEmails(@RequestBody List<String> emails);

}
