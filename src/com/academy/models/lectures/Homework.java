package com.academy.models.lectures;

import com.academy.models.Models;

import static com.academy.models.Lecture.getCounterOfLectures;

public class Homework extends Models {
    private int lectureID;
    private String task;
    private int numberOfTasks;
    private static int counterOfHomework;

    @Override
    public String toString() {
        return "Homework (" +
                "name = '" + getName() + '\'' +
                ", numberOfTasks = " + numberOfTasks +
                ", task = \"" + task + "\", ID = " + getID() +
                ", lectureID = " + lectureID +
                ')';
    }
    public Homework(String name, int numberOfTasks, String task) {
        this.setName(name);
        this.numberOfTasks = numberOfTasks;
        this.task = task;
        setID(++counterOfHomework);
        lectureID = getCounterOfLectures();
    }
    public Homework(){
        setID(++counterOfHomework);
        lectureID = getCounterOfLectures();
    }

    public void setNumberOfTasks(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
    }

    public static void setCounterOfHomework(int counterOfHomework) {
        Homework.counterOfHomework = counterOfHomework;
    }

    public int getNumberOfTasks() {
        return numberOfTasks;
    }
    public static int getCounterOfHomework() {
        return counterOfHomework;
    }

    public int getLectureID() {
        return lectureID;
    }

    public void setLectureID(int lectureID) {
        this.lectureID = lectureID;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}