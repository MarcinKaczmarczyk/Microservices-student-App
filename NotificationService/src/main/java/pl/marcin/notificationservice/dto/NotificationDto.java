package pl.marcin.notificationservice.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public class NotificationDto {
    private List<String> emails;
    private String title;
    private String desc;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime courseStartDate;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime courseEndDate;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public LocalDateTime getCourseEndDate() {
        return courseEndDate;
    }

    public void setCourseEndDate(LocalDateTime courseEndDate) {
        this.courseEndDate = courseEndDate;
    }

    @Override
    public String toString() {
        return "NotificationJson{" +
                "emails=" + emails +
                ", title='" + title + '\'' +
                ", courseStartDate=" + courseStartDate +
                ", courseEndDate=" + courseEndDate +
                '}';
    }

}
