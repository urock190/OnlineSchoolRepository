package com.academy.services;

import com.academy.courses.Course;
import com.academy.courses.Lecture;
import com.academy.courses.lectures.AdditionalMaterial;
import com.academy.courses.lectures.Homework;

public class LectureService {
    public static void printCounter(){
    System.out.println("number of lectures = " + Lecture.getCounterOfLectures());
    }
    public static void printCourseID(Lecture lecture){
        System.out.println("course ID of lecture №" + lecture.getID() + " = " + lecture.courseID);
    }
    public static Lecture createLecture() {
        return new Lecture();
    }
    public static Lecture createLecture(String name, int amount, Homework homework,
                                        AdditionalMaterial additionalMaterial, Course course){
        return new Lecture(name, amount, homework, additionalMaterial, course);
    }
}
