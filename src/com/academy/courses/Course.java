package com.academy.courses;

public class Course {
    private String name;
    private int ID;
    private static int counterOfCourses;
    private Teacher teacher;
    private Student student;
    private Lecture lecture;

    @Override
    public String toString() {
        return "Course (" +
                "name = '" + name + '\'' +
                ", ID = " + ID +
                ", teacher = " + teacher +
                ", student = " + student +
                ", lecture = " + lecture + ')';
    }
    public Course (String name, Teacher teacher, Student student, Lecture lecture) {
        this.name = name;
        this.teacher = teacher;
        this.student = student;
        this.lecture = lecture;
        ID = ++counterOfCourses;
        lecture.courseID = ID;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Student getStudent() {
        return student;
    }

    public Course (String name, Teacher teacher, Student student) {
        this.name = name;
        this.teacher = teacher;
        this.student = student;
        ID = ++counterOfCourses;
    }
    public Course(){
        ID = ++counterOfCourses;
    }
    public String getName() {
        return name;
    }
    public int getID() {
        return ID;
    }
    public static int getCounterOfCourses() {
        return counterOfCourses;
    }
}