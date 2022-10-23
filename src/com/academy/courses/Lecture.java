package com.academy.courses;

import com.academy.courses.lectures.AdditionalMaterial;
import com.academy.courses.lectures.Homework;

public class Lecture {
    private String name;
    private int amount;
    private int ID;
    private static int counterOfLectures;
    private Course course;
    private Homework homework;
    private AdditionalMaterial additionalMaterial;
    public int courseID;

    public Lecture (String name, int amount, Homework homework, AdditionalMaterial additionalMaterial, Course course) {
        this.name = name;
        this.amount = amount;
        this.homework = homework;
        this.additionalMaterial = additionalMaterial;
        ID = ++counterOfLectures;
        courseID = course.getID();
    }
    public Lecture(){
        ID = ++counterOfLectures;
    }
    public int getID() {
        return ID;
    }
    public static int getCounterOfLectures() {
        return counterOfLectures;
    }
}
