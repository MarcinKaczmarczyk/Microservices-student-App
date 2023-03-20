package com.marcin.courses.service;

import com.marcin.courses.model.Course;
import com.marcin.courses.exception.CourseStatus;

import java.util.List;

public interface CourseService {

    List<Course> getCourses(CourseStatus status);
    Course getCourse(String code);
    Course addCourse(Course course);
    void deleteCourse(String id);
    Course patchCourse(String id, Course course);
}
