package com.marcin.courses.repository;

import com.marcin.courses.model.Course;
import com.marcin.courses.model.CourseStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface CourseRepository extends MongoRepository<Course, String> {

    List<Course> findByStatus(CourseStatus status);
}
