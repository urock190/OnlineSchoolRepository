package com.academy.models;

import com.academy.models.lectures.AdditionalMaterial;
import com.academy.models.lectures.Homework;

import java.util.Arrays;

import static com.academy.models.Course.getCounterOfCourses;
import static com.academy.models.Person.getCounterOfPersons;

public class Lecture extends Models {
    private int amount;
    private String description;
    private static int counterOfLectures;
    private Homework [] homeworks;
    private AdditionalMaterial additionalMaterial;
    private int courseID;
    private int personID;
    @Override
    public String toString() {
        return "Lecture (" +
                "name = '" + getName() + '\'' +
                ", amount = " + amount +
                ", description = \"" + description + '\"'+
                ", ID = " + getID() +
                ", homework = " + Arrays.toString(homeworks) +
                ", additionalMaterial = " + additionalMaterial +
                ", courseID = " + courseID +
                ", personID = " + personID +')';
    }
    public Lecture (String name, int amount, Homework [] homeworks, AdditionalMaterial additionalMaterial) {
        this.setName(name);
        this.amount = amount;
        this.homeworks = homeworks;
        this.additionalMaterial = additionalMaterial;
        setID(++counterOfLectures);
        courseID = getCounterOfCourses();
        personID = getCounterOfPersons();
        for (Homework homework : homeworks) {
            if (homework == null) continue;
            homework.setLectureID(getID());
        }
    }
    public Lecture(String name, int amount, String description, Homework [] homeworks, AdditionalMaterial additionalMaterial) {
        this.setName(name);
        this.amount = amount;
        this.description = description;
        this.homeworks = homeworks;
        this.additionalMaterial = additionalMaterial;
        setID(++counterOfLectures);
        courseID = getCounterOfCourses();
        personID = getCounterOfPersons();
        for (Homework homework : homeworks) {
            if (homework == null) continue;
            homework.setLectureID(getID());
        }
    }

    public Lecture(){
        setID(++counterOfLectures);
        courseID = getCounterOfCourses();
        personID = getCounterOfPersons();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setHomework(Homework [] homeworks) {
        this.homeworks = homeworks;
    }

    public void setAdditionalMaterial(AdditionalMaterial additionalMaterial) {
        this.additionalMaterial = additionalMaterial;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
    public int getPersonID() {
        return personID;
    }
    public void setPersonID(int personID) {
        this.personID = personID;
    }
    public int getAmount() {
        return amount;
    }

    public Homework[] getHomework() {
        return homeworks;
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