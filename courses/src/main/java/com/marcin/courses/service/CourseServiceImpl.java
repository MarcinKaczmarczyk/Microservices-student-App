package com.marcin.courses.service;

import com.marcin.courses.exception.course.CoursesError;
import com.marcin.courses.exception.course.CoursesException;
import com.marcin.courses.exception.student.StudentError;
import com.marcin.courses.exception.student.StudentException;
import com.marcin.courses.model.Course;
import com.marcin.courses.model.CourseStatus;
import com.marcin.courses.model.NotificationDto;
import com.marcin.courses.model.dto.CourseMember;
import com.marcin.courses.model.dto.Student;
import com.marcin.courses.repository.CourseRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    private final RabbitTemplate rabbitTemplate;
    private final CourseRepository repository;
    private final StudentServiceClient studentService;

    public CourseServiceImpl(RabbitTemplate rabbitTemplate, CourseRepository repository, StudentServiceClient studentService) {
        this.rabbitTemplate = rabbitTemplate;
        this.repository = repository;
        this.studentService = studentService;
    }

    @Override
    public List<Course> getCourses(CourseStatus status) {
        if (status != null) {
            return repository.findByStatus(status);
        }
        return repository.findAll();
    }

    @Override
    public Course getCourse(String code) {
        return repository.findById(code).orElseThrow(() -> new CoursesException(CoursesError.COURSE_NOT_FOUND));
    }

    @Override
    public Course addCourse(Course course) {
        courseValidation(course);
        return repository.save(course);
    }

    @Override
    public void deleteCourse(String id) {
        Course courseFromDb = repository.findById(id)
                .orElseThrow(() -> new CoursesException(CoursesError.COURSE_NOT_FOUND));
        courseFromDb.setStatus(CourseStatus.INACTIVE);
        repository.save(courseFromDb);
    }

    @Override
    public Course patchCourse(String id, Course course) {
        courseValidation(course);
        return repository.findById(id).map(courseFromDb -> {
            if (course.getName() != null && !course.getName().equals("")) {
                courseFromDb.setName(course.getName());
            }
            if (course.getDescriptions() != null && !course.getDescriptions().equals("")) {
                courseFromDb.setDescriptions(course.getDescriptions());
            }
            if (course.getStartDate() != null) {
                courseFromDb.setStartDate(course.getStartDate());
            }
            if (course.getEndDate() != null) {
                courseFromDb.setEndDate(course.getEndDate());
            }
            if (course.getParticipantsLimit() != null) {
                courseFromDb.setParticipantsLimit(course.getParticipantsLimit());
            }
            if (course.getParticipantsNumber() != null) {
                courseFromDb.setParticipantsNumber(course.getParticipantsNumber());
            }
            if (course.getStatus() != null) {
                courseFromDb.setStatus(course.getStatus());
            }
        return repository.save(courseFromDb);
        }).orElseThrow(()->new CoursesException(CoursesError.COURSE_NOT_FOUND));
    }

    @Override
    public String addStudentToCourse(Long studentId, String courseId) {
        Student student=studentService.getStudent(studentId);
        Course course=repository.findById(courseId)
                .orElseThrow(()->new CoursesException(CoursesError.COURSE_NOT_FOUND));
        validateCourseAvailability(course,student);
        CourseMember courseMember=new CourseMember(student);
        course.addCourseMembers(courseMember);
        course.setParticipantsNumber(course.getParticipantsNumber()+1);
        if (course.getParticipantsNumber().equals(course.getParticipantsLimit())){
            course.setStatus(CourseStatus.FULL);
        }
        repository.save(course);

        return "zapisano studenta";
    }

    @Override
    public void closeEnrollment(String code) {
        Course course= repository.findById(code)
                .orElseThrow(()->new CoursesException(CoursesError.COURSE_NOT_FOUND));
        if (course.getStatus().equals(CourseStatus.INACTIVE)){
            throw new CoursesException(CoursesError.COURSE_IS_ALREADY_INACTIVE);
        }
        course.setStatus(CourseStatus.INACTIVE);
        repository.save(course);
        SendRabbitNotification(course);
    }
    private void SendRabbitNotification(Course course){
        NotificationDto json= NotificationDto.courseConverter(course);
        rabbitTemplate.convertAndSend("courseApp", json);
    }

    private void validateCourseAvailability(Course course,Student student){
        if (!student.getActive()){
            throw new StudentException(StudentError.STUDENT_IS_INACTIVE);
        }
        if (!course.getStatus().equals(CourseStatus.ACTIVE)){
            throw new CoursesException(CoursesError.COURSE_NOT_AVAILABLE);
        }
        if (course.getParticipantsNumber()>=course.getParticipantsLimit()){
            throw new CoursesException(CoursesError.COURSE_IS_FULL);
        }
        if (course.getCourseMembers().stream().map(CourseMember::getEmail).anyMatch(email->email.equals(student.getEmail()))){
            throw new CoursesException(CoursesError.MEMBER_IS_ALREADY_JOIN_TO_COURSE);
        }
    }

    public void courseValidation(Course course){
        if (course.getStartDate()!=null&&course.getEndDate()!=null&&course.getStartDate().isAfter(course.getEndDate())){
            throw new CoursesException(CoursesError.COURSE_END_BEFORE_START);
        }
        if (course.getParticipantsNumber()!=null&&course.getParticipantsLimit()!=null&&course.getParticipantsNumber()>course.getParticipantsLimit()){
            throw new CoursesException(CoursesError.COURSE_IS_FULL);
        }
        if (course.getParticipantsNumber()!=null&&course.getParticipantsLimit()!=null&&course.getStatus()!=null&&!course.getParticipantsNumber().equals(course.getParticipantsLimit())&&course.getStatus().equals(CourseStatus.FULL)){
            throw new CoursesException(CoursesError.COURSE_MUST_BE_FULL);
        }
    }

    public List<Student> getCourseMembers(String code) {
        List<String> emails = repository.findById(code)
                .orElseThrow(() -> new CoursesException(CoursesError.COURSE_NOT_FOUND))
                .getCourseMembers()
                .stream()
                .map(CourseMember::getEmail)
                .collect(Collectors.toList());
        return studentService.getStudentsByEmails(emails);
    }
//    public void courseValidation(Course course,Course fromDB){
//        if (course.getStartDate().isAfter(course.getEndDate())){
//            throw new CoursesException(CoursesError.COURSE_END_BEFORE_START);
//        }
//        if (course.getParticipantsNumber()>course.getParticipantsLimit()){
//            throw new CoursesException(CoursesError.COURSE_IS_FULL);
//        }
//        if (!course.getParticipantsNumber().equals(course.getParticipantsLimit())&&course.getStatus().equals(CourseStatus.FULL)){
//            throw new CoursesException(CoursesError.COURSE_MUST_BE_FULL);
//        }
//    }
}
