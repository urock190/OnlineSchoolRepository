package com.academy.models.lectures;

import com.academy.models.Models;

public class Homework extends Models {
    private int numberOfTasks;
    private static int counterOfHomework;

    @Override
    public String toString() {
        return "Homework (" +
                "name = '" + getName() + '\'' +
                ", numberOfTasks = " + numberOfTasks +
                ')';
    }
    public Homework(String name, int numberOfTasks) {
        this.setName(name);
        this.numberOfTasks = numberOfTasks;
        setID(++counterOfHomework);
    }
    public Homework(){
        setID(++counterOfHomework);
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
}