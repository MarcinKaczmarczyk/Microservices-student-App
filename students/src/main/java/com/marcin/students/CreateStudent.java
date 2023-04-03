package com.marcin.students;

import com.marcin.students.model.Student;
import com.marcin.students.repository.StudentRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class CreateStudent {

   private final StudentRepository studentRepository;

    public CreateStudent(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostConstruct
    void createStudents() {
        for (int i = 1; i <= 10; i++) {
            Student student=new Student("marian"+i,"ziomo"+i,"cybermarian"+i+"@gmail.com");
            student.setActive(i > 6);
            if (i==8){
                student.setEmail("jmsmicroserv@gmail.com");
            }
            if (i==7){
                student.setEmail("mojziomo123@gmail.com");
            }
            studentRepository.save(student);
        }
        System.out.println("poszło");

    }
}
