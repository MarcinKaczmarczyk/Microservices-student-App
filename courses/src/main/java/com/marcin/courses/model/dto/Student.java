package com.marcin.courses.model.dto;

import javax.validation.constraints.NotBlank;

public class Student {


    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @NotBlank
    private String email;
    private Boolean active;


    public Student(String firstname, String lastname, String email, Boolean active) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.active = active;
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
