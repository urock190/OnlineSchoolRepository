package com.academy.services;

import com.academy.courses.Course;
import com.academy.courses.Lecture;
import com.academy.courses.lectures.AdditionalMaterial;
import com.academy.courses.lectures.Homework;

import java.util.Scanner;

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
                                        AdditionalMaterial additionalMaterial){
        return new Lecture(name, amount, homework, additionalMaterial);
    }
    public static Lecture createLectureFromConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Create new lecture. \nEnter the name of this lecture");
        String name = scanner.next();
        System.out.println("Enter amount of lectures");
        int amount = scanner.nextInt();
        System.out.println("Enter homework's name");
        String homeworkName = scanner.next();
        System.out.println("Enter number of tasks");
        int numberOfTasks = scanner.nextInt();
        Homework homework = new Homework(homeworkName, numberOfTasks);
        System.out.println("Enter the name of additional material");
        String addMatName = scanner.next();
        System.out.println("Enter amount of articles");
        int numberOfArticles = scanner.nextInt();
        AdditionalMaterial additionalMaterial = new AdditionalMaterial(addMatName, numberOfArticles);
        return new Lecture(name, amount, homework, additionalMaterial);
        }
}
