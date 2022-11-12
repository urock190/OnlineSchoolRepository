package com.academy.courses;

public class Teacher {
    private String name;
    private String secondName;
    private int ID;
    private static int counterOfTeachers;

    @Override
    public String toString() {
        return "Teacher (" +
                "name = '" + name + '\'' +
                ", secondName = '" + secondName + '\'' + ')';
    }
    public Teacher (String name, String secondName) {
        this.name = name;
        this.secondName = secondName;
        ID = ++counterOfTeachers;
    }
    public String getName() {
        return name;
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
