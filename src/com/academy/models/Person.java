package com.academy.models;

import static com.academy.models.Course.getCounterOfCourses;

public class Person extends Models {
    private String secondName;
    private int courseID;
    private Role role;
    private int teacherID;
    private int studentID;
    private static int counterOfPersons;
    private static int counterOfStudents;
    private static int counterOfTeachers;

    public Person (Role role, String name, String secondName) {
        this.role = role;
        this.setName(name);
        this.secondName = secondName;
        setID(++counterOfPersons);
        if (this.role == Role.STUDENT) {++counterOfStudents; studentID = super.getID();}
        else {++counterOfTeachers; teacherID = super.getID();}
        courseID = getCounterOfCourses();
    }

    @Override
    public String toString() {
        return role + " (" +
                "name = '" + getName() + '\'' +
                ", secondName = '" + secondName + '\'' + ')';
    }

    public Person(){
        setID(++counterOfPersons);
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public static int getCounterOfStudents() {
        return counterOfStudents;
    }

    public static void setCounterOfStudents(int counterOfStudents) {
        Person.counterOfStudents = counterOfStudents;
    }

    public static int getCounterOfTeachers() {
        return counterOfTeachers;
    }

    public static void setCounterOfTeachers(int counterOfTeachers) {
        Person.counterOfTeachers = counterOfTeachers;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public static void setCounterOfPersons(int counter) {
        Person.counterOfPersons = counter;
    }

    public String getSecondName() {
        return secondName;
    }
    public static int getCounterOfPersons() {
        return counterOfPersons;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
