package com.academy;

import com.academy.courses.Lecture;
import com.academy.courses.lectures.AdditionalMaterial;
import com.academy.courses.lectures.Homework;
import com.academy.services.LectureService;

public class Main {
    public static void main(String[] args) {
        Lecture lecture = new Lecture();
        Lecture secondLecture = new Lecture ("Math", 70, new Homework(), new AdditionalMaterial());
        LectureService.createLecture();
        Lecture fourthLecture = LectureService.createLecture();
        Lecture fifthLecture = LectureService.createLecture();

        System.out.println(secondLecture.getID()); // ID of secondLecture

        LectureService.printCounter(); // Counter of lectures
    }
}