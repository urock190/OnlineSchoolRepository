package com.academy;

import com.academy.courses.Course;
import com.academy.courses.Lecture;
import com.academy.courses.Student;
import com.academy.courses.Teacher;
import com.academy.courses.lectures.AdditionalMaterial;
import com.academy.courses.lectures.Homework;
import com.academy.services.CourseService;
import com.academy.services.LectureService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CourseService courseService = new CourseService();
        Course firstCourse = courseService.createCourse("firstCourse", new Teacher(), new Student(),
                new Lecture("English", 77, new Homework("first", 2),
                        new AdditionalMaterial()));                           // creating course 1
        Lecture secondLecture = LectureService.createLecture("Math", 70, new Homework(),
                new AdditionalMaterial());

        Course secondCourse = courseService.createCourse("secondCourse", new Teacher("Victoriya",
                "Karnauh"), new Student("Yurii", "Shovkoplias"),
                new Lecture("Literature", 30, new Homework(),
                        new AdditionalMaterial()));                            // creating course 2
        Lecture fourthLecture = LectureService.createLecture("Chemistry", 70, new Homework(),
                new AdditionalMaterial());
        Lecture fifthLecture = LectureService.createLecture("English", 75, new Homework(),
                new AdditionalMaterial());
        Lecture sixthLecture = LectureService.createLecture("Informatics", 70, new Homework(),
                new AdditionalMaterial());

      //  LectureService.printCourseID(sixthLecture); // courseID of sixthLecture
        chooseCategoryAndCreateLecture();
    }
    static void chooseCategoryAndCreateLecture(){
        Scanner scanner = new Scanner(System.in);
        int categoryNumber = 0;
        OUTER:
        while (true){
          do {
              System.out.println("Choose category: press 1 - for \"Course\", 2 - for \"Lecture\", 3 - for \"Student\" " +
                      "or 4 - for \"Teacher\"\n Type \"ex\" to exit the program");
              if(scanner.hasNext("ex")) break OUTER;
            categoryNumber = scanner.nextInt();
        switch(categoryNumber){
            case 1 -> System.out.println("You have choose the category \"Course\"");
            case 2 -> System.out.println("You have choose the category \"Lecture\"");
            case 3 -> System.out.println("You have choose the category \"Student\"");
            case 4 -> System.out.println("You have choose the category \"Teacher\"");
            default -> System.out.println("Please, enter a number from 1 to 4");}
          } while (categoryNumber < 1 || categoryNumber > 4);
            while (Lecture.getCounterOfLectures() < 8){
                Lecture newLecture = LectureService.createLectureFromConsole();
            System.out.println(newLecture);
                if (Lecture.getCounterOfLectures() == 8) {
                    System.out.println("=================\nExiting program: eight lectures have already been created.");
                    break OUTER;}
            LectureService.printCourseID(newLecture);
            System.out.println("===========================================\nDo you want to finish creating lectures? " +
                    "Enter \"yes\" to confirm.\nEnter anything else to create new lecture.");
            String confirmation = scanner.next();
            if(confirmation.equals("yes")) break;
                }
            } LectureService.printCounter(); // Counter of lectures
        }
}