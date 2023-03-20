package com.marcin.students.service;

import com.marcin.students.exception.StudentError;
import com.marcin.students.exception.StudentException;
import com.marcin.students.model.Student;
import com.marcin.students.repository.StudentRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getStudents(Boolean active) {
    if (active!=null){
       return studentRepository.findByActive(active);
    }
        return studentRepository.findAll();
    }

    @Override
    public Student getStudent(Long id) {
        Student student=studentRepository.findById(id)
                .orElseThrow(() -> new StudentException(StudentError.STUDENT_NOT_FOUND));
        if (!student.isActive())throw new StudentException(StudentError.STUDENT_IS_INACTIVE);
        return student;
    }

    @Override
    public Student addStudent(Student student) {
        validateStudentEmailExists(student);
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentException(StudentError.STUDENT_NOT_FOUND));
        student.setActive(false);
        studentRepository.save(student);
    }

    @Override
    public Student putStudent(Long id, Student student) {
        return studentRepository.findById(id)
                .map(studentFromDb -> {
                    if (!studentFromDb.getEmail().equals(student.getEmail())){
                        validateStudentEmailExists(student);
                    }
                    studentFromDb.setFirstname(student.getFirstname());
                    studentFromDb.setLastname(student.getLastname());
                    studentFromDb.setEmail(student.getEmail());
                    studentFromDb.setActive(student.isActive());
                    return studentRepository.save(studentFromDb);
                }).orElseThrow(() -> new StudentException(StudentError.STUDENT_NOT_FOUND));
    }

    @Override
    public Student patchStudent(Long id, Student student) {
        return studentRepository.findById(id)
                .map(studentFromDb -> {
                    if (student.getFirstname() != null && !student.getFirstname().equals("")) {
                        studentFromDb.setFirstname(student.getFirstname());
                    }
                    if (student.getLastname() != null && !student.getLastname().equals("")) {
                        studentFromDb.setLastname(student.getLastname());
                    }
//                    if(student.getEmail()!=null&&!student.getEmail().equals("")){
//                        studentFromDb.setEmail(student.getEmail());
//                    }

                   if (student.isActive()!=null)studentFromDb.setActive(student.isActive());
                    return studentRepository.save(studentFromDb);
                }).orElseThrow(() -> new StudentException(StudentError.STUDENT_NOT_FOUND));
    }
    private void validateStudentEmailExists(Student student){
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new StudentException(StudentError.STUDENT_EMAIL_ALREADY_EXISTS);
        }
    }
}
