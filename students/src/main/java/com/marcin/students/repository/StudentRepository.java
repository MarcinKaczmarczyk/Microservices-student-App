package com.marcin.students.repository;

import com.marcin.students.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {

    List<Student> findByActive(Boolean active);

    boolean existsByEmail(String email);

//    @Query("select s from Student s where s.email in (:emails)")
    List<Student> findAllByEmailIn(List<String>emails);
}
