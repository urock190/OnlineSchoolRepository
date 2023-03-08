package com.academy.models;

import java.io.Serial;
import java.io.Serializable;

public class Course extends Models implements Comparable<Course>, Serializable {
    @Serial
    private static final long serialVersionUID = 1246742084L;
    private static int counterOfCourses;
    private Person teacher;
    private Person student;
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
    public Course (String name, Person teacher, Person student, Lecture lecture) {
        this.setName(name);
        this.teacher = teacher;
        this.student = student;
        this.lecture = lecture;
        setID(++counterOfCourses);
        lecture.setCourseID(getID());
        student.setCourseID(getID());
        teacher.setCourseID(getID());
    }
    public Course (String name, Person teacher, Person student) {
        this.setName(name);
        this.teacher = teacher;
        this.student = student;
        setID(++counterOfCourses);
        student.setCourseID(getID());
        teacher.setCourseID(getID());
    }
    public Course(){
        setID(++counterOfCourses);
    }

    public void setTeacher(Person teacher) {
        this.teacher = teacher;
    }

    public void setStudent(Person student) {
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
    public Person getTeacher() {
        return teacher;
    }
    public Person getStudent() {
        return student;
    }
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * Compares two Course instances by Name field lexicographically, ignoring case differences. This method
     * returns an integer whose sign is that of calling {@code compareTo} with case folded versions of the strings
     * where case differences have been eliminated by calling {@code Character.toLowerCase(Character.toUpperCase(int))}
     * on each Unicode code point.
     */
    @Override
    public int compareTo(Course o) {
        return  getName().compareToIgnoreCase(o.getName());
    }
}