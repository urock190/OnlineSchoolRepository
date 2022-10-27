package com.academy.courses;

import com.academy.courses.lectures.AdditionalMaterial;
import com.academy.courses.lectures.Homework;
import static com.academy.courses.Course.getCounterOfCourses;

public class Lecture {
    private String name;
    private int amount;
    private int ID;
    private static int counterOfLectures;
    private Homework homework;
    private AdditionalMaterial additionalMaterial;
    public int courseID;

    @Override
    public String toString() {
        return "Lecture (" +
                "name = '" + name + '\'' +
                ", amount = " + amount +
                ", homework = " + homework +
                ", additionalMaterial = " + additionalMaterial + ')';
    }
    public Lecture (String name, int amount, Homework homework, AdditionalMaterial additionalMaterial) {
        this.name = name;
        this.amount = amount;
        this.homework = homework;
        this.additionalMaterial = additionalMaterial;
        ID = ++counterOfLectures;
        courseID = getCounterOfCourses();
    }
    public Lecture(){
        ID = ++counterOfLectures;
        courseID = getCounterOfCourses();
    }
    public int getID() {
        return ID;
    }
    public static int getCounterOfLectures() {
        return counterOfLectures;
    }
}
