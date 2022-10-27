package com.academy.courses;

public class Course {
    public String name;
    private int ID;
    private static int counterOfCourses;
    private Teacher teacher;
    private Student student;
    private Lecture lecture;

    public Course (String name, Teacher teacher, Student student, Lecture lecture) {
        this.name = name;
        this.teacher = teacher;
        this.student = student;
        this.lecture = lecture;
        ID = ++counterOfCourses;
        lecture.courseID = ID;
    }
    public Course(){
        ID = ++counterOfCourses;
    }
    public int getID() {
        return ID;
    }
    public static int getCounterOfCourses() {
        return counterOfCourses;
    }
}