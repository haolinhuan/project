package com;

import java.util.Date;

public class Student {
    private String name;
    private String age;
    private Date birthday;

  /*  public Student(String name, String age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }*/

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
