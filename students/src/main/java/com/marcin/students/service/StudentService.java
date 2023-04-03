package com.marcin.students.service;

import com.marcin.students.model.Student;

import java.util.List;

public interface StudentService {

    List<Student> getStudents(Boolean activ);

    Student getStudent(Long id);

    Student addStudent(Student student);

    void deleteStudent(Long id);

    Student putStudent(Long id, Student student);

    Student patchStudent(Long id, Student student);

    List<Student> getStudentsByEmails(List<String> emails);
}
