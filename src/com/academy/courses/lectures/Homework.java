package com.academy.courses.lectures;

public class Homework {
    private String name;
    private int numberOfTasks;
    private int ID;
    private static int counterOfHomework;

    public Homework(String name, int numberOfTasks) {
        this.name = name;
        this.numberOfTasks = numberOfTasks;
        ID = ++counterOfHomework;
    }
    public Homework(){
        ID = ++counterOfHomework;
    }
    public int getID() {
        return ID;
    }
    public static int getCounterOfHomework() {
        return counterOfHomework;
    }
}
