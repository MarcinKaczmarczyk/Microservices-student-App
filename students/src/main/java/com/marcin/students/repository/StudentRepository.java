package com.marcin.students.repository;

import com.marcin.students.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {

    List<Student> findByActive(Boolean active);

    boolean existsByEmail(String email);
}
