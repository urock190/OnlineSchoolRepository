package com.academy.courses;

import com.academy.courses.lectures.AdditionalMaterial;
import com.academy.courses.lectures.Homework;

public class Lecture {
    private String name;
    private int amount;
    private int ID;
    private static int counterOfLectures;
    private Homework homework;
    private AdditionalMaterial additionalMaterial;

    public Lecture (String name, int amount, Homework homework, AdditionalMaterial additionalMaterial) {
        this.name = name;
        this.amount = amount;
        this.homework = homework;
        this.additionalMaterial = additionalMaterial;
        ID = ++counterOfLectures;
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
