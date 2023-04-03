package com.marcin.courses.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CourseMember {

    @NotNull
    private LocalDateTime saveTime;
    @NotBlank
    private String email;

    public CourseMember (){
    }
    public CourseMember (Student student){
        this.saveTime=LocalDateTime.now();
        this.email=student.getEmail();
    }

    public LocalDateTime getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(LocalDateTime saveTime) {
        this.saveTime = saveTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
