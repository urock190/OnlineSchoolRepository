package com.academy.models;

import com.academy.superclasses.Models;

public class Teacher extends Models {
    private String secondName;
    private static int counterOfTeachers;
    @Override
    public String toString() {
        return "Teacher (" +
                "name = '" + getName() + '\'' +
                ", secondName = '" + secondName + '\'' + ')';
    }

    public Teacher() {
        setID(++counterOfTeachers);
    }

    public Teacher (String name, String secondName) {
        this.setName(name);
        this.secondName = secondName;
        setID(++counterOfTeachers);
    }
    public String getSecondName() {
        return secondName;
    }
    public static int getCounterOfTeachers() {
        return counterOfTeachers;
    }

    public static void setCounterOfTeachers(int counterOfTeachers) {
        Teacher.counterOfTeachers = counterOfTeachers;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
}
