package com.marcin.courses.controller;

import com.marcin.courses.model.Course;
import com.marcin.courses.exception.CourseStatus;
import com.marcin.courses.model.dto.Student;
import com.marcin.courses.service.CourseServiceImpl;
import com.marcin.courses.service.StudentServiceClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseServiceImpl courseService;
    private final StudentServiceClient studentServiceClient;

    public CourseController(CourseServiceImpl courseService, StudentServiceClient serviceClient) {
        this.courseService = courseService;
        this.studentServiceClient = serviceClient;
    }

    @GetMapping()
    public List<Course> getCourses(@RequestParam(required = false) CourseStatus status) {
        return courseService.getCourses(status);
    }

    @GetMapping("/{code}")
    public Course getCourse(@PathVariable String code) {
        return courseService.getCourse(code);
    }
    @PostMapping()
    public Course addCourse(@Valid @RequestBody Course course) {
        return courseService.addCourse(course);
    }
    @PatchMapping("/{id}")
    public Course patchCourse(@PathVariable String id,@RequestBody Course course){
        return courseService.patchCourse(id,course);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable String id){
        courseService.deleteCourse(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/test")
    public List<Student> testFeingClient(){
        return studentServiceClient.getStudents();
    }
}
