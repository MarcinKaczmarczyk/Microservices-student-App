package com.marcin.courses.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.marcin.courses.model.dto.CourseMember;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class NotificationDto {
    private List<String> emails;
    private String title;
    private String desc;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime courseStartDate;
   @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime courseEndDate;

    public static NotificationDto courseConverter(Course course){
        NotificationDto noti=new NotificationDto();
        noti.setEmails(course.getCourseMembers().stream()
                .map(CourseMember::getEmail)
                .collect(Collectors.toList()));
        noti.setTitle("Przypomnienie od kursie: "+course.getName());
        noti.setCourseStartDate(course.getStartDate());
        noti.setDesc(course.getDescriptions());
        noti.setCourseEndDate(course.getEndDate());
        return noti;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LocalDateTime getCourseEndDate() {
        return courseEndDate;
    }

    public void setCourseEndDate(LocalDateTime courseEndDate) {
        this.courseEndDate = courseEndDate;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCourseStartDate() {
        return courseStartDate;
    }

    public void setCourseStartDate(LocalDateTime courseStartDate) {
        this.courseStartDate = courseStartDate;
    }
}
