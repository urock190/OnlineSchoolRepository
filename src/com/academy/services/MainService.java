package com.academy.services;

import com.academy.exceptions.EntityNotFoundException;
import com.academy.models.*;
import com.academy.models.lectures.AdditionalMaterial;
import com.academy.models.lectures.Homework;
import com.academy.repository.CourseRepository;
import com.academy.repository.LectureRepository;
import com.academy.repository.PersonRepository;
import com.academy.repository.lectures.AdditionalMaterialRepository;
import com.academy.repository.lectures.HomeworkRepository;
import com.academy.services.lectures.AdditionalMaterialService;
import com.academy.services.lectures.HomeworkService;

import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainService {
    public static void init() {
        LectureRepository lectureRepository = LectureRepository.getInstance();
        CourseRepository courseRepository = CourseRepository.getInstance();
        PersonRepository personRepository = PersonRepository.getInstance();
        AdditionalMaterialRepository addMaterialRepository = AdditionalMaterialRepository.getInstance();
        CourseService courseService = new CourseService();
        Course firstCourse = courseService.createCourse("firstCourse", new Person(Role.TEACHER, "Victoriya",
                "Karnauh"), new Person(Role.STUDENT, "Yurii", "Shovkoplias"));
        courseRepository.add(firstCourse);
        personRepository.add(firstCourse.getTeacher());
        personRepository.add(firstCourse.getStudent());
        Lecture firstLecture = LectureService.createLecture("Chemistry", 70, new Homework[]{},
                new AdditionalMaterial("Organic chemistry", ResourceType.BOOK));
        lectureRepository.add(firstLecture);
        addMaterialRepository.add(firstLecture.getAdditionalMaterial());
        Lecture secondLecture = LectureService.createLecture("English", 75, new Homework[]{},
                new AdditionalMaterial("Big Fat Video-course", ResourceType.VIDEO));
        lectureRepository.add(secondLecture);
        Lecture thirdLecture = LectureService.createLecture("Informatics", 70, new Homework[]{},
                new AdditionalMaterial("Head First Java", ResourceType.BOOK));
        lectureRepository.add(thirdLecture);
        addMaterialRepository.add(thirdLecture.getAdditionalMaterial());
        addMaterialRepository.add(secondLecture.getAdditionalMaterial());
    }

    private static int categoryNumMethod(Scanner scanner) {
        try {
            scanner.skip(".*");
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println(e + ": Incorrect input. Only integer values are allowed.");
            return categoryNumMethod(scanner);
        }
    }

    public static void chooseCategoryAndCreateLecture() {
        Scanner scanner = new Scanner(System.in);
        LectureRepository lectureRepository = LectureRepository.getInstance();
        int categoryNumber;
        OUTER:
        while (true) {
            do {
                System.out.println("""
                        Choose category: press 1 - for "Course", 2 - for "Lecture", 3 - for "Student" or 4 - for "Teacher", 5 - for "Homework", 6 - for "Additional Material".
                        Type "ex" to exit the program""");
                if (scanner.hasNext("ex")) break OUTER;
                try {
                    categoryNumber = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println(e + ": Incorrect input. Only integer values are allowed.");
                    categoryNumber = categoryNumMethod(scanner);
                }

                switch (categoryNumber) {
                    case 1:
                        System.out.println("You have choose the category \"Course\"");
                        System.out.println("""
                                Do you want to print short info about course objects? Type "yes" to confirm. Type "no" to choose another category.
                                Enter "1" to create new course. Enter "2" to get course by it's ID. Enter "3" to print full info about courses.
                                Type anything else to continue creating lectures.""");
                        String confirmation = scanner.next();
                        switch (confirmation) {
                            case "yes", "no", "1", "2", "3" -> {
                                courseMenu(confirmation, scanner);
                                continue OUTER;
                            }
                            default -> {
                                continue;
                            }
                        }
                    case 2:
                        System.out.println("You have choose the category \"Lecture\"");
                        System.out.println("""
                                Do you want to print short info about lecture objects? Type "yes" to confirm. Type "no" to choose another category.\s
                                Enter "1" to add teacher's ID to the lecture. Enter "2" to get lecture by it's ID. Enter "3" to print full info about lectures.
                                Type anything else to continue creating lectures.""");
                        String confirmation1 = scanner.next();
                        switch (confirmation1) {
                            case "yes", "no", "1", "2", "3" -> {
                                lectureMenu(confirmation1, scanner);
                                continue OUTER;
                            }
                            default -> {
                                continue;
                            }
                        }
                    case 3:
                        System.out.println("You have choose the category \"Student\"");
                        System.out.println("""
                                Do you want to print short info about students? Type "yes" to confirm. Type "no" to choose another category.\s
                                Enter "1" to create new student. Enter "2" to get student by their ID. Enter "3" to print full info about students.
                                Type anything else to continue creating lectures.""");
                        String confirmation2 = scanner.next();
                        switch (confirmation2) {
                            case "yes", "no", "1", "2", "3" -> {
                                studentMenu(confirmation2, scanner);
                                continue OUTER;
                            }
                            default -> {
                                continue;
                            }
                        }
                    case 4:
                        System.out.println("You have choose the category \"Teacher\"");
                        System.out.println("""
                                Do you want to print short info about teachers? Type "yes" to confirm. Type "no" to choose another category.\s
                                Enter "1" to create new teacher. Enter "2" to get teacher by their ID. Enter "3" to print full info about teachers.
                                Type anything else to continue creating lectures.""");
                        String confirmation3 = scanner.next();
                        switch (confirmation3) {
                            case "yes", "no", "1", "2", "3" -> {
                                teacherMenu(confirmation3, scanner);
                                continue OUTER;
                            }
                            default -> {
                                continue;
                            }
                        }

                    case 5:
                        System.out.println("You have choose the category \"Homework\"");
                        System.out.println("""
                                Do you want to print short info about homeworks? Type "yes" to confirm. Type "no" to choose another category.\s
                                Enter "1" to create new homework. Enter "2" to get homework by it's ID. Enter "3" to print full info about homeworks.
                                Type anything else to continue creating lectures.""");
                        String confirmation4 = scanner.next();
                        switch (confirmation4) {
                            case "yes", "no", "1", "2", "3" -> {
                                homeworkMenu(confirmation4, scanner);
                                continue OUTER;
                            }
                            default -> {
                                continue;
                            }
                        }

                    case 6:
                        System.out.println("You have choose the category \"Additional Material\"");
                        System.out.println("""
                                Do you want to print short info about additional materials? Type "yes" to confirm. Type "no" to choose another category.\s
                                Enter "1" to create new additional material. Enter "2" to get additional material by it's ID. Enter "3" to print full info about additional materials.
                                Type anything else to continue creating lectures.""");
                        String confirmation5 = scanner.next();
                        switch (confirmation5) {
                            case "yes", "no", "1", "2", "3" -> {
                                addMaterialMenu(confirmation5, scanner);
                                continue OUTER;
                            }
                            default -> {
                                continue;
                            }
                        }
                    default:
                        System.out.println("Please, enter a number from 1 to 6");
                }
            } while (categoryNumber < 1 || categoryNumber > 6);
            while (Lecture.getCounterOfLectures() < 8) {
                Lecture newLecture = LectureService.createLectureFromConsole();
                lectureRepository.add(newLecture);
                System.out.println(newLecture);
                if (Lecture.getCounterOfLectures() == 8) {
                    System.out.println("=================\nExiting program: eight lectures have already been created.");
                    break OUTER;
                }
                LectureService.printCourseID(newLecture);
                System.out.println("""
                        ==========================================
                        Do you want to finish creating lectures? Enter "yes" to confirm.
                        Enter anything else to create new lecture.""");
                if (scanner.next().equals("yes")) break;
            }
        }
        LectureService.printCounter(); // Counter of lectures
    }

    private static void courseMenu(String confirmation, Scanner scanner) {
        CourseService courseService = new CourseService();
        CourseRepository courseRepository = CourseRepository.getInstance();
        Collections.sort(courseRepository.getAll());
        if (confirmation.equals("yes")) {
            courseService.printID();
        } else if (confirmation.equals("1")) {
            do {
                Course newCourse = courseService.createCourseFromConsole();
                courseRepository.add(newCourse);
                System.out.println("""
                        ==========================================
                        Do you want to create new course? Enter "yes" to confirm.
                        Enter anything else to finish creating courses and return to "Choose category" menu.""");
            } while (scanner.next().equals("yes"));
        } else if (confirmation.equals("2")) {
            do {
                System.out.println("====================================\nEnter ID number of the course.");
                int id = scanner.nextInt();
                try {
                    System.out.println(courseRepository.getById(id));
                } catch (EntityNotFoundException e) {
                    System.out.println(e);
                }
                System.out.println("""
                        ====================================
                        Would you like to get another course? Enter "yes" to confirm.
                        Enter anything else to finish showing course's info and return to "Choose category" menu.""");
            } while (scanner.next().equals("yes"));
        } else if (confirmation.equals("3")) {
            courseRepository.findAll();
            System.out.println("\n++++++++++++++++++++++");
        }
    }

    private static void lectureMenu(String confirmation1, Scanner scanner) {
        LectureService lectureService = new LectureService();
        LectureRepository lectureRepository = LectureRepository.getInstance();
        if (confirmation1.equals("yes")) {
            lectureService.printID();
        } else if (confirmation1.equals("1")) {
            lectureService.addTeacherByID();
        } else if (confirmation1.equals("2")) {
            do {
                System.out.println("====================================\nEnter ID number of the lecture.");
                int id = scanner.nextInt();
                try {
                    System.out.println(lectureRepository.getById(id));
                } catch (EntityNotFoundException e) {
                    System.out.println(e);
                }
                System.out.println("""
                        ====================================
                        Would you like to get another lecture? Enter "yes" to confirm.
                        Enter anything else to finish showing lecture's info and return to "Choose category" menu.""");
            } while (scanner.next().equals("yes"));
        } else if (confirmation1.equals("3")) {
            lectureRepository.findAll();
            System.out.println("\n++++++++++++++++++++++");
        }
    }

    private static void studentMenu(String confirmation2, Scanner scanner) {
        PersonService personService = new PersonService();
        PersonRepository personRepository = PersonRepository.getInstance();
        Collections.sort(personRepository.getAll());
        if (confirmation2.equals("yes")) {
            personService.printStudentsID();
        } else if (confirmation2.equals("1")) {
            do {
                Person newStudent = PersonService.createStudentFromConsole();
                personRepository.add(newStudent);
                System.out.println("""
                        ==========================================
                        Do you want to create new student? Enter "yes" to confirm.
                        Enter anything else to finish creating students and return to "Choose category" menu.""");
            } while (scanner.next().equals("yes"));
        } else if (confirmation2.equals("2")) {
            do {
                System.out.println("====================================\nEnter ID number of the student.");
                int id = scanner.nextInt();
                try {
                    System.out.println(personRepository.getStudentById(id));
                } catch (EntityNotFoundException e) {
                    System.out.println(e);
                }
                System.out.println("""
                        ====================================
                        Would you like to get another student? Enter "yes" to confirm.
                        Enter anything else to finish showing student's info and return to "Choose category" menu.""");
            } while (scanner.next().equals("yes"));
        } else if (confirmation2.equals("3")) {
            personRepository.findAll(Role.STUDENT);
            System.out.println("\n++++++++++++++++++++++");
        }
    }

    private static void teacherMenu(String confirmation3, Scanner scanner) {
        PersonService personService = new PersonService();
        PersonRepository personRepository = PersonRepository.getInstance();
        Collections.sort(personRepository.getAll());
        if (confirmation3.equals("yes")) {
            personService.printTeachersID();
        } else if (confirmation3.equals("1")) {
            do {
                Person newTeacher = PersonService.createTeacherFromConsole();
                personRepository.add(newTeacher);
                System.out.println("""
                        ==========================================
                        Do you want to create new teacher? Enter "yes" to confirm.
                        Enter anything else to finish creating teachers and return to "Choose category" menu.""");
            } while (scanner.next().equals("yes"));
        } else if (confirmation3.equals("2")) {
            do {
                System.out.println("====================================\nEnter ID number of the teacher.");
                int id = scanner.nextInt();
                try {
                    System.out.println(personRepository.getTeacherById(id));
                } catch (EntityNotFoundException e) {
                    System.out.println(e);
                }
                System.out.println("""
                        ====================================
                        Would you like to get another teacher? Enter "yes" to confirm.
                        Enter anything else to finish showing teacher's info and return to "Choose category" menu.""");
            } while (scanner.next().equals("yes"));
        } else if (confirmation3.equals("3")) {
            personRepository.findAll(Role.TEACHER);
            System.out.println("\n++++++++++++++++++++++");
        }
    }

    private static void homeworkMenu(String confirmation4, Scanner scanner) {
        HomeworkService homeworkService = new HomeworkService();
        HomeworkRepository homeworkRepository = HomeworkRepository.getInstance();
        if (confirmation4.equals("yes")) {
            homeworkService.printID();
        } else if (confirmation4.equals("1")) {
            do {
                Homework newHomework = HomeworkService.createHomeworkFromConsole();
                homeworkRepository.add(newHomework);
                System.out.println("""
                        ==========================================
                        Do you want to create new homework? Enter "yes" to confirm.
                        Enter anything else to finish creating homeworks and return to "Choose category" menu.""");
            } while (scanner.next().equals("yes"));
        } else if (confirmation4.equals("2")) {
            do {
                System.out.println("====================================\nEnter ID number of the homework.");
                int id = scanner.nextInt();
                try {
                    System.out.println(homeworkRepository.getById(id));
                } catch (EntityNotFoundException e) {
                    System.out.println(e);
                }
                System.out.println("""
                        ====================================
                        Would you like to get another homework? Enter "yes" to confirm.
                        Enter anything else to finish showing homework's info and return to "Choose category" menu.""");
            } while (scanner.next().equals("yes"));
        } else if (confirmation4.equals("3")) {
            homeworkRepository.findAll();
            System.out.println("\n++++++++++++++++++++++");
        }
    }

    private static void addMaterialMenu(String confirmation5, Scanner scanner) {
        AdditionalMaterialService additionalMaterialService = new AdditionalMaterialService();
        AdditionalMaterialRepository additionalMaterialRepository = AdditionalMaterialRepository.getInstance();
        Collections.sort(additionalMaterialRepository.getAll());
        String conf;
        if (confirmation5.equals("yes")) {
            do {
                additionalMaterialService.printID();
                System.out.println("""
                        ==========================================
                        Enter "1" if you want to sort additional materials by lecture's ID or enter "2" if you want to sort by resource type.
                        Enter anything else to return to "Choose category" menu.""");
                conf = scanner.next();
                if (conf.equals("1")) Collections.sort(additionalMaterialRepository.getAll(), AdditionalMaterial.lectureIDComparator);
                else if (conf.equals("2")) Collections.sort(additionalMaterialRepository.getAll(), AdditionalMaterial.resourceTypeComparator);
            } while (conf.equals("1")||conf.equals("2"));
        } else if (confirmation5.equals("1")) {
            do {
                AdditionalMaterial additionalMaterial = AdditionalMaterialService.createAddMaterialFromConsole();
                additionalMaterialRepository.add(additionalMaterial);
                System.out.println("""
                        ==========================================
                        Do you want to create new additional material? Enter "yes" to confirm.
                        Enter anything else to finish creating additional materials and return to "Choose category" menu.""");
            } while (scanner.next().equals("yes"));
        } else if (confirmation5.equals("2")) {
            do {
                System.out.println("====================================\nEnter ID number of the additional material.");
                int id = scanner.nextInt();
                try {
                    System.out.println(additionalMaterialRepository.getById(id));
                } catch (EntityNotFoundException e) {
                    System.out.println(e);
                }
                System.out.println("""
                        ====================================
                        Would you like to get another additional material? Enter "yes" to confirm.
                        Enter anything else to finish showing additional material's info and return to "Choose category" menu.""");
            } while (scanner.next().equals("yes"));
        } else if (confirmation5.equals("3")) {
            do {
                additionalMaterialRepository.findAll();
                System.out.println("""
                        ==========================================
                        Enter "1" if you want to sort additional materials by lecture's ID or enter "2" if you want to sort by resource type.
                        Enter anything else to return to "Choose category" menu.""");
                conf = scanner.next();
                if (conf.equals("1")) Collections.sort(additionalMaterialRepository.getAll(), AdditionalMaterial.lectureIDComparator);
                else if (conf.equals("2")) Collections.sort(additionalMaterialRepository.getAll(), AdditionalMaterial.resourceTypeComparator);
            } while (conf.equals("1")||conf.equals("2"));
        } System.out.println("++++++++++++++++++++++");
    }
}