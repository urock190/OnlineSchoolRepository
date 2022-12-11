package com.academy.services;

import com.academy.models.Course;
import com.academy.models.Lecture;
import com.academy.models.Person;
import com.academy.models.Role;
import com.academy.models.lectures.AdditionalMaterial;
import com.academy.models.lectures.Homework;
import com.academy.repository.CourseRepository;
import com.academy.repository.LectureRepository;
import com.academy.repository.PersonRepository;

import java.util.Scanner;

public class MainService {
    public static void init(){
        LectureRepository lectureRepository = new LectureRepository();
        CourseRepository courseRepository = new CourseRepository();
        PersonRepository personRepository = new PersonRepository();
        CourseService courseService = new CourseService();
        Course firstCourse = courseService.createCourse("firstCourse", new Person(Role.TEACHER,"Victoriya",
                "Karnauh"), new Person(Role.STUDENT, "Yurii", "Shovkoplias"));
        courseRepository.addCourse(firstCourse);
        personRepository.addPerson(firstCourse.getTeacher());
        personRepository.addPerson(firstCourse.getStudent());
        Lecture firstLecture = LectureService.createLecture("Chemistry", 70, new Homework(),
                new AdditionalMaterial());
        lectureRepository.addLecture(firstLecture);
        Lecture secondLecture = LectureService.createLecture("English", 75, new Homework(),
                new AdditionalMaterial());
        lectureRepository.addLecture(secondLecture);
        Lecture thirdLecture = LectureService.createLecture("Informatics", 70, new Homework(),
                new AdditionalMaterial());
        lectureRepository.addLecture(thirdLecture);
    }
    public static void chooseCategoryAndCreateLecture(){
        Scanner scanner = new Scanner(System.in);
        LectureService lectureService = new LectureService();
        CourseService courseService = new CourseService();
        PersonService personService = new PersonService();
        LectureRepository lectureRepository = new LectureRepository();
        CourseRepository courseRepository = new CourseRepository();
        PersonRepository personRepository = new PersonRepository();
        int categoryNumber;
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
                        System.out.println("Do you want to print short info about course objects? Type \"yes\" to confirm." +
                                "\nType \"no\" to choose another category. Enter \"1\" to create new course." +
                                "\nEnter \"2\" to get course by it's ID. Type anything else to continue creating lectures.");
                        String confirmation = scanner.next();
                        if(confirmation.equals("yes")) {
                            courseService.printID(); continue OUTER;
                        } else if (confirmation.equals("no")) {
                            continue OUTER;
                        } else if (confirmation.equals("1")) {
                            do {
                                Course newCourse = courseService.createCourseFromConsole();
                                courseRepository.addCourse(newCourse);
                                System.out.println("==========================================\nDo you want to create new course? " +
                                        "Enter \"yes\" to confirm.\nEnter anything else to finish creating courses and" +
                                        " return to \"Choose category\" menu.");
                            }while (scanner.next().equals("yes"));
                            continue OUTER;
                        } else if (confirmation.equals("2")) {
                            do {System.out.println("====================================\nEnter ID number of the course.");
                                int id = scanner.nextInt();
                                if (courseRepository.getById(id) == null){
                                    System.out.println("There's no course with such ID");
                                } else System.out.println(courseRepository.getById(id));
                                System.out.println("====================================\nWould you like to get another course?" +
                                        " Enter \"yes\" to confirm.\nEnter anything else to finish showing course's info and" +
                                        " return to \"Choose category\" menu.");
                            }while (scanner.next().equals("yes"));
                            continue OUTER;
                        }else break;
                    case 2:
                        System.out.println("You have choose the category \"Lecture\"");
                        System.out.println("Do you want to print short info about lecture objects? Type \"yes\" to confirm." +
                                "\nType \"no\" to choose another category. Enter \"1\" to add teacher's ID to the lecture." +
                                "\nEnter \"2\" to get lecture by it's ID. Type anything else to continue creating lectures.");
                        String confirmation1 = scanner.next();
                        if(confirmation1.equals("yes")) {
                            lectureService.printID(); continue OUTER;
                        } else if (confirmation1.equals("no")) {
                            continue OUTER;
                        } else if (confirmation1.equals("1")) {
                            lectureService.addTeacherByID();
                            continue OUTER;
                        } else if (confirmation1.equals("2")) {
                            do {System.out.println("====================================\nEnter ID number of the lecture.");
                                int id = scanner.nextInt();
                                if (lectureRepository.getById(id) == null){
                                    System.out.println("There's no lecture with such ID");
                                } else System.out.println(lectureRepository.getById(id));
                                System.out.println("====================================\nWould you like to get another lecture?" +
                                        " Enter \"yes\" to confirm.\nEnter anything else to finish showing lecture's info and" +
                                        " return to \"Choose category\" menu.");
                            }while (scanner.next().equals("yes"));
                            continue OUTER;
                        }else break;
                    case 3:
                        System.out.println("You have choose the category \"Student\"");
                        System.out.println("Do you want to print short info about students? Type \"yes\" to confirm." +
                                "\nType \"no\" to choose another category. Enter \"1\" to create new student." +
                                "\nEnter \"2\" to get student by their ID. Type anything else to continue creating lectures.");
                        String confirmation2 = scanner.next();
                        if(confirmation2.equals("yes")) {
                            personService.printStudentsID(); continue OUTER;
                        } else if (confirmation2.equals("no")) {
                            continue OUTER;
                        } else if (confirmation2.equals("1")) {
                            do {
                                Person newStudent = PersonService.createStudentFromConsole();
                                personRepository.addPerson(newStudent);
                                System.out.println("==========================================\nDo you want to create new student? " +
                                    "Enter \"yes\" to confirm.\nEnter anything else to finish creating students and" +
                                        " return to \"Choose category\" menu.");
                            }while (scanner.next().equals("yes"));
                            continue OUTER;
                        } else if (confirmation2.equals("2")) {
                            do {System.out.println("====================================\nEnter ID number of the student.");
                                int id = scanner.nextInt();
                                if (personRepository.getStudentById(id) == null){
                                    System.out.println("There's no student with such ID");
                                } else System.out.println(personRepository.getStudentById(id));
                                System.out.println("====================================\nWould you like to get another student?" +
                                        " Enter \"yes\" to confirm.\nEnter anything else to finish showing student's info and" +
                                        " return to \"Choose category\" menu.");
                            }while (scanner.next().equals("yes"));
                            continue OUTER;
                        }else break;
                    case 4:
                        System.out.println("You have choose the category \"Teacher\"");
                        System.out.println("Do you want to print short info about teachers? Type \"yes\" to confirm." +
                                "\nType \"no\" to choose another category. Enter \"1\" to create new teacher." +
                                "\nEnter \"2\" to get teacher by their ID. Type anything else to continue creating lectures.");
                        String confirmation3 = scanner.next();
                        if(confirmation3.equals("yes")) {
                            personService.printTeachersID(); continue OUTER;
                        } else if (confirmation3.equals("no")) {
                            continue OUTER;
                        } else if (confirmation3.equals("1")) {
                            do {
                                Person newTeacher = PersonService.createTeacherFromConsole();
                                personRepository.addPerson(newTeacher);
                                System.out.println("==========================================\nDo you want to create new teacher? " +
                                        "Enter \"yes\" to confirm.\nEnter anything else to finish creating teachers and" +
                                        " return to \"Choose category\" menu.");
                            } while (scanner.next().equals("yes"));
                            continue OUTER;
                        } else if (confirmation3.equals("2")) {
                            do {System.out.println("====================================\nEnter ID number of the teacher.");
                                int id = scanner.nextInt();
                                if (personRepository.getTeacherById(id) == null){
                                    System.out.println("There's no teacher with such ID");
                                } else System.out.println(personRepository.getTeacherById(id));
                                System.out.println("====================================\nWould you like to get another teacher?" +
                                        " Enter \"yes\" to confirm.\nEnter anything else to finish showing teacher's info and" +
                                        " return to \"Choose category\" menu.");
                            }while (scanner.next().equals("yes"));
                            continue OUTER;
                        }else break;
                    default: System.out.println("Please, enter a number from 1 to 4");}
            } while (categoryNumber < 1 || categoryNumber > 4);
            while (Lecture.getCounterOfLectures() < 8){
                Lecture newLecture = LectureService.createLectureFromConsole();
                lectureRepository.addLecture(newLecture);
                System.out.println(newLecture);
                if (Lecture.getCounterOfLectures() == 8) {
                    System.out.println("=================\nExiting program: eight lectures have already been created.");
                    break OUTER;}
                LectureService.printCourseID(newLecture);
                System.out.println("==========================================\nDo you want to finish creating lectures? " +
                        "Enter \"yes\" to confirm.\nEnter anything else to create new lecture.");
                if(scanner.next().equals("yes")) break;
            }
        } LectureService.printCounter(); // Counter of lectures
    }
}