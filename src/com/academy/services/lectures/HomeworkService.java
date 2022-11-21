package com.academy.services.lectures;

import com.academy.models.lectures.Homework;

public class HomeworkService {
    public static void printCounter(){
        System.out.println(Homework.getCounterOfHomework());
    }
    public static Homework createHomework() {
        return new Homework();
    }
}
