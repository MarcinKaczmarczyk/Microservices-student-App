package com.marcin.courses.controller;

import com.marcin.courses.model.Course;
import com.marcin.courses.model.CourseStatus;
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

    @PostMapping("/{courseId}/student/{studentId}")
    public ResponseEntity<?> courseEnrollment(@PathVariable String courseId, @PathVariable Long studentId) {
        return ResponseEntity.ok(courseService.addStudentToCourse(studentId, courseId));
    }

    @PatchMapping("/{id}")
    public Course patchCourse(@PathVariable String id, @RequestBody Course course) {
        return courseService.patchCourse(id, course);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable String id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/test")
    public List<Student> testFeingClient() {
        return studentServiceClient.getStudents();
    }

    @GetMapping("/{code}/members")
    public List<Student> getMembers(@PathVariable String code) {
        return courseService.getCourseMembers(code);
    }

    @GetMapping("/{code}/finish-enroll")
    public ResponseEntity<?> finishEnrollment(@PathVariable String code) {
        courseService.closeEnrollment(code);
        return ResponseEntity.ok().build();
    }

}