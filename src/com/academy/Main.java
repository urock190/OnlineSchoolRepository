package com.academy;

import com.academy.courses.Course;
import com.academy.courses.Lecture;
import com.academy.courses.lectures.AdditionalMaterial;
import com.academy.courses.lectures.Homework;
import com.academy.services.CourseService;
import com.academy.services.LectureService;

public class Main {
    public static void main(String[] args) {
        CourseService courseService = new CourseService();
        Course firstCourse = courseService.createCourse();     // creating course 1
        Course secondCourse = courseService.createCourse();     // creating course 2
        Lecture firstLecture = LectureService.createLecture("English", 77,
                new Homework("first", 2), new AdditionalMaterial(), firstCourse);
        Lecture secondLecture = LectureService.createLecture("Math", 70, new Homework(),
                new AdditionalMaterial(), firstCourse);
        Lecture thirdLecture = LectureService.createLecture("Literature", 30, new Homework(),
                new AdditionalMaterial(), secondCourse);
        Lecture fourthLecture = LectureService.createLecture("Chemistry", 70, new Homework(),
                new AdditionalMaterial(), secondCourse);
        Lecture fifthLecture = LectureService.createLecture("English", 75, new Homework(),
                new AdditionalMaterial(), secondCourse);
        Lecture sixthLecture = LectureService.createLecture("Informatics", 70, new Homework(),
                new AdditionalMaterial(), secondCourse);


        LectureService.printCourseID(sixthLecture); // courseID of sixthLecture
        LectureService.printCounter(); // Counter of lectures
    }
}