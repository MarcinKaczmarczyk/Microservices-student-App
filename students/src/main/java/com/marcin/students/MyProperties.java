package com.marcin.students;


import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
@Validated
public class MyProperties {

    @NotNull
    private String mark;
    private String model;

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
