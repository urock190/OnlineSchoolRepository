package com.academy.courses;

public class Student {
    private String name;
    private String secondName;
    private int ID;
    private static int counterOfStudents;

    public Student (String name, String secondName) {
        this.name = name;
        this.secondName = secondName;
        ID = ++counterOfStudents;
    }
    public Student(){
        ID = ++counterOfStudents;
    }
    public int getID() {
        return ID;
    }
    public static int getCounterOfStudents() {
        return counterOfStudents;
    }
}
