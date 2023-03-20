package com.marcin.students.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@SequenceGenerator(name = "seqIdGen", initialValue = 2000, allocationSize = 1)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqIdGen")
    private Long id;
    @NotNull(message = "Pole nie może być null")
    private String firstname;
    @NotNull(message = "Pole nie może być null")
    private String lastname;
    @Column(unique = true)
    private String email;
    private Boolean active;

    public Student() {
    }

    public Student(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
