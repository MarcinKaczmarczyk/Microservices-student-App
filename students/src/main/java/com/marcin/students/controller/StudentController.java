package com.marcin.students.controller;

import com.marcin.students.model.Student;
import com.marcin.students.service.StudentService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;

    }

    @PostMapping("/emails")
    public List<Student> getListByEmails(@RequestBody List<String> emails){
        return studentService.getStudentsByEmails(emails);
    }

    @GetMapping()
    public List<Student> getStudents(@RequestParam(required = false) Boolean active) {
        return studentService.getStudents(active);
    }
//    @GetMapping()
//    public List<Student> getStudents() {
//        return studentService.getStudents(null);
//    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @PostMapping()
    public Student saveStudent(@RequestBody @Valid Student st) {
        return studentService.addStudent(st);
    }

    @PutMapping("/{id}")
    public Student putStudent(@PathVariable Long id, @RequestBody @Valid Student student) {
        return studentService.putStudent(id, student);
    }

    @PatchMapping("/{id}")
    public Student patchStudent(@PathVariable Long id, @RequestBody Student student) {
        return studentService.patchStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);
    }
//    @GetMapping("/ln")
//    public List<Student> findStudent(@RequestParam String lastName){
//        return studentRepository.findAll().stream()
//                .filter(student -> student.getLastname().equals(lastName))
//                .collect(Collectors.toList());
//    }
}
