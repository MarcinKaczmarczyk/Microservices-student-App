package com.marcin.courses.service;

import com.marcin.courses.exception.CoursesError;
import com.marcin.courses.exception.CoursesException;
import com.marcin.courses.model.Course;
import com.marcin.courses.exception.CourseStatus;
import com.marcin.courses.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository repository;

    public CourseServiceImpl(CourseRepository repository) {
        this.repository = repository;
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
