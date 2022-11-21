package com.academy.models;

import com.academy.superclasses.Models;

public class Student extends Models {
    private String secondName;
    private static int counterOfStudents;
    @Override
    public String toString() {
        return "Student (" +
                "name = '" + getName() + '\'' +
                ", secondName = '" + secondName + '\'' + ')';
    }
    public Student (String name, String secondName) {
        this.setName(name);
        this.secondName = secondName;
        setID(++counterOfStudents);
    }
    public Student(){
        setID(++counterOfStudents);
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public static void setCounterOfStudents(int counterOfStudents) {
        Student.counterOfStudents = counterOfStudents;
    }

    public String getSecondName() {
        return secondName;
    }
    public static int getCounterOfStudents() {
        return counterOfStudents;
    }
}
