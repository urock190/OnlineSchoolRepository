package com.academy.courses;

public class Teacher {
    private String name;
    private String secondName;
    private int ID;
    private static int counterOfTeachers;

    public Teacher (String name, String secondName) {
        this.name = name;
        this.secondName = secondName;
        ID = ++counterOfTeachers;
    }
    public Teacher(){
        ID = ++counterOfTeachers;
    }
    public int getID() {
        return ID;
    }
    public static int getCounterOfTeachers() {
        return counterOfTeachers;
    }
}
