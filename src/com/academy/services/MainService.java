package com.academy.services;

import com.academy.courses.Course;
import com.academy.courses.Lecture;
import com.academy.courses.Student;
import com.academy.courses.Teacher;
import com.academy.courses.lectures.AdditionalMaterial;
import com.academy.courses.lectures.Homework;
import com.academy.repository.CourseRepository;
import com.academy.repository.LectureRepository;
import com.academy.repository.StudentRepository;
import com.academy.repository.TeacherRepository;

import java.util.Scanner;

public class MainService {

    public static void init(){
        CourseService courseService = new CourseService();
        Course firstCourse = courseService.createCourse("firstCourse", new Teacher("Victoriya",
                "Karnauh"), new Student("Yurii", "Shovkoplias"));
        CourseRepository.addCourse(firstCourse);
        TeacherRepository.addTeacher(firstCourse.getTeacher());
        StudentRepository.addStudent(firstCourse.getStudent());
        Lecture firstLecture = LectureService.createLecture("Chemistry", 70, new Homework(),
                new AdditionalMaterial());
        LectureRepository.addLecture(firstLecture);
        Lecture secondLecture = LectureService.createLecture("English", 75, new Homework(),
                new AdditionalMaterial());
        LectureRepository.addLecture(secondLecture);
        Lecture thirdLecture = LectureService.createLecture("Informatics", 70, new Homework(),
                new AdditionalMaterial());
        LectureRepository.addLecture(thirdLecture);
    }
    public static void chooseCategoryAndCreateLecture(){
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
                    case 1:
                        System.out.println("You have choose the category \"Course\"");
                        System.out.println("Do you want to print short info about course objects? " +
                            "Type \"yes\" to confirm.\nType \"no\" to choose another category." +
                                "\nType anything else to continue creating lectures.");
                        String confirmation = scanner.next();
                        if(confirmation.equals("yes")) {
                            CourseService.printID(); continue OUTER;
                        } else if (confirmation.equals("no")) {
                            continue OUTER;
                        } else break;
                    case 2:
                        System.out.println("You have choose the category \"Lecture\"");
                        System.out.println("Do you want to print short info about lecture objects? " +
                                "Type \"yes\" to confirm.\nType \"no\" to choose another category." +
                                "\nType anything else to continue creating lectures.");
                        String confirmation1 = scanner.next();
                        if(confirmation1.equals("yes")) {
                            LectureService.printID(); continue OUTER;
                        } else if (confirmation1.equals("no")) {
                            continue OUTER;
                        } else break;
                    case 3:
                        System.out.println("You have choose the category \"Student\"");
                        System.out.println("Do you want to print short info about students? " +
                                "Type \"yes\" to confirm.\nType \"no\" to choose another category." +
                                "\nType anything else to continue creating lectures.");
                        String confirmation2 = scanner.next();
                        if(confirmation2.equals("yes")) {
                            StudentService.printID(); continue OUTER;
                        } else if (confirmation2.equals("no")) {
                            continue OUTER;
                        } else break;
                    case 4:
                        System.out.println("You have choose the category \"Teacher\"");
                        System.out.println("Do you want to print short info about teachers? " +
                                "Type \"yes\" to confirm.\nType \"no\" to choose another category." +
                                "\nType anything else to continue creating lectures.");
                        String confirmation3 = scanner.next();
                        if(confirmation3.equals("yes")) {
                            TeacherService.printID(); continue OUTER;
                        } else if (confirmation3.equals("no")) {
                            continue OUTER;
                        } else break;
                    default: System.out.println("Please, enter a number from 1 to 4");}
            } while (categoryNumber < 1 || categoryNumber > 4);
            while (Lecture.getCounterOfLectures() < 8){
                Lecture newLecture = LectureService.createLectureFromConsole();
                LectureRepository.addLecture(newLecture);
                System.out.println(newLecture);
                if (Lecture.getCounterOfLectures() == 8) {
                    System.out.println("=================\nExiting program: eight lectures have already been created.");
                    break OUTER;}
                LectureService.printCourseID(newLecture);
                System.out.println("==========================================\nDo you want to finish creating lectures? " +
                        "Enter \"yes\" to confirm.\nEnter anything else to create new lecture.");
                String confirmation = scanner.next();
                if(confirmation.equals("yes")) break;
            }
        } LectureService.printCounter(); // Counter of lectures
    }
}
/**CourseService courseService = new CourseService();
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
 LectureService.printCourseID(sixthLecture); // courseID of sixthLecture*/