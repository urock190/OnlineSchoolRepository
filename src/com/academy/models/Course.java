package com.academy.models;

import com.academy.superclasses.Models;

public class Course extends Models {
    private static int counterOfCourses;
    private Teacher teacher;
    private Student student;
    private Lecture lecture;
    @Override
    public String toString() {
        return "Course (" +
                "name = '" + getName() + '\'' +
                ", ID = " + getID() +
                ", teacher = " + teacher +
                ", student = " + student +
                ", lecture = " + lecture + ')';
    }
    public Course (String name, Teacher teacher, Student student, Lecture lecture) {
        this.setName(name);
        this.teacher = teacher;
        this.student = student;
        this.lecture = lecture;
        setID(++counterOfCourses);
        lecture.setCourseID(getID());
    }
    public Course (String name, Teacher teacher, Student student) {
        this.setName(name);
        this.teacher = teacher;
        this.student = student;
        setID(++counterOfCourses);
    }
    public Course(){
        setID(++counterOfCourses);
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public static int getCounterOfCourses() {
        return counterOfCourses;
    }

    public static void setCounterOfCourses(int counterOfCourses) {
        Course.counterOfCourses = counterOfCourses;
    }

    public Lecture getLecture() {
        return lecture;
    }
    public Teacher getTeacher() {
        return teacher;
    }
    public Student getStudent() {
        return student;
    }
}