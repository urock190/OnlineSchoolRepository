package com.academy.models;

import com.academy.models.lectures.AdditionalMaterial;
import com.academy.models.lectures.Homework;
import com.academy.superclasses.Models;

import static com.academy.models.Course.getCounterOfCourses;

public class Lecture extends Models {
    private int amount;
    private static int counterOfLectures;
    private Homework homework;
    private AdditionalMaterial additionalMaterial;
    private int courseID;
    @Override
    public String toString() {
        return "Lecture (" +
                "name = '" + getName() + '\'' +
                ", amount = " + amount +
                ", homework = " + homework +
                ", additionalMaterial = " + additionalMaterial + ')';
    }
    public Lecture (String name, int amount, Homework homework, AdditionalMaterial additionalMaterial) {
        this.setName(name);
        this.amount = amount;
        this.homework = homework;
        this.additionalMaterial = additionalMaterial;
        setID(++counterOfLectures);
        courseID = getCounterOfCourses();
    }
    public Lecture(){
        setID(++counterOfLectures);
        courseID = getCounterOfCourses();
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setHomework(Homework homework) {
        this.homework = homework;
    }

    public void setAdditionalMaterial(AdditionalMaterial additionalMaterial) {
        this.additionalMaterial = additionalMaterial;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getAmount() {
        return amount;
    }

    public Homework getHomework() {
        return homework;
    }

    public AdditionalMaterial getAdditionalMaterial() {
        return additionalMaterial;
    }

    public static void setCounterOfLectures(int counterOfLectures) {
        Lecture.counterOfLectures = counterOfLectures;
    }

    public int getCourseID() {
        return courseID;
    }
    public static int getCounterOfLectures() {
        return counterOfLectures;
    }
}