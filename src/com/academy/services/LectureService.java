package com.academy.services;

import com.academy.courses.Lecture;

public class LectureService {
    public static void printCounter(){
    System.out.println("number of lectures = " + Lecture.getCounterOfLectures());
    }
    public static Lecture createLecture() {
        return new Lecture();
    }
}
