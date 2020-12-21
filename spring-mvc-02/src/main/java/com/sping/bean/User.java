package com.sping.bean;

import java.util.Date;

public class User {

    private String name;

    private String age;

    private Date birthday;

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
