package com.marcin.students;

import com.marcin.students.model.Student;
import com.marcin.students.repository.StudentRepository;
import com.marcin.students.service.StudentService;
import com.marcin.students.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
public class StudentsApplication {


    public static void main(String[] args) {
        SpringApplication.run(StudentsApplication.class, args);
    }




}
