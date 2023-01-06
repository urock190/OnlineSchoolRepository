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

import java.util.*;

public class MainService {
    public static void init() {
        LectureRepository lectureRepository = LectureRepository.getInstance();
        CourseRepository courseRepository = CourseRepository.getInstance();
        PersonRepository personRepository = PersonRepository.getInstance();
        AdditionalMaterialRepository addMaterialRepository = AdditionalMaterialRepository.getInstance();
        HomeworkRepository homeworkRepository = HomeworkRepository.getInstance();
        CourseService courseService = new CourseService();
        Course firstCourse = courseService.createCourse("firstCourse", new Person(Role.TEACHER, "Victoriya",
                "Karnauh"), new Person(Role.STUDENT, "Yurii", "Shovkoplias"));
        courseRepository.add(firstCourse);
        personRepository.add(firstCourse.getTeacher());
        personRepository.add(firstCourse.getStudent());
        Lecture firstLecture = LectureService.createLecture("Chemistry", 70, new Homework[]{new Homework("exercises",
                        3, "task 1"), new Homework("reading", 1, "article 2")},
                new AdditionalMaterial("Organic chemistry", ResourceType.BOOK));
        lectureRepository.add(firstLecture);
        homeworkRepository.put(firstLecture.getID(), new ArrayList<>());
        for (Homework homework : firstLecture.getHomework()) {
            homeworkRepository.get(firstLecture.getID()).add(homework);}
        addMaterialRepository.put(firstLecture.getID(), new ArrayList<>());
        addMaterialRepository.get(firstLecture.getID()).add(firstLecture.getAdditionalMaterial());
        Lecture secondLecture = LectureService.createLecture("English", 75, new Homework[]{},
                new AdditionalMaterial("Big Fat Video-course", ResourceType.VIDEO));
        lectureRepository.add(secondLecture);
        Lecture thirdLecture = LectureService.createLecture("Informatics", 70, new Homework[]{},
                new AdditionalMaterial("Head First Java", ResourceType.BOOK));
        lectureRepository.add(thirdLecture);
        addMaterialRepository.put(thirdLecture.getID(), new ArrayList<>());
        addMaterialRepository.get(thirdLecture.getID()).add(thirdLecture.getAdditionalMaterial());
        addMaterialRepository.put(secondLecture.getID(), new ArrayList<>());
        addMaterialRepository.get(secondLecture.getID()).add(secondLecture.getAdditionalMaterial());
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
                        CourseService.courseMenuTitle();
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
                        LectureService.lectureMenuTitle();
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
                        PersonService.studentMenuTitle();
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
                        PersonService.teacherMenuTitle();
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
                        HomeworkService.homeworkMenuTitle();
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
                        AdditionalMaterialService.addMaterialMenuTitle();
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
        } LectureService.printCounter(); // Counter of lectures
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
        } System.out.println("++++++++++++++++++++++");
    }

    private static void lectureMenu(String confirmation1, Scanner scanner) {
        LectureService lectureService = new LectureService();
        LectureRepository lectureRepository = LectureRepository.getInstance();
        HomeworkRepository homeworkRepository = HomeworkRepository.getInstance();
        AdditionalMaterialRepository addMaterialRepository = AdditionalMaterialRepository.getInstance();
        String conf;
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
                    lectureService.printHomeworks(id); lectureService.printAddMaterials(id);
                    homeworkRepository.putIfAbsent(id, new ArrayList<>());
                    addMaterialRepository.putIfAbsent(id, new ArrayList<>());
                } catch (EntityNotFoundException e) {
                    System.out.println(e);
                }
                System.out.println("""
                        ====================================
                        Would you like to get another lecture? Enter "yes" to confirm.
                        Enter "1" if you want to add homework to the lecture by it's ID or enter "2" if you want to remove homework by it's ID.
                        Enter "3" if you want to add additional material to the lecture by it's ID or enter "4" if you want to remove additional material by it's ID.
                        Enter anything else to finish showing lecture's info and return to "Choose category" menu.""");
                conf = scanner.next();
                if (conf.equals("1")) {
                    try {
                        if (homeworkRepository.get(id) == null) {
                            System.out.println("There's no lecture with such ID"); continue;}
                        System.out.println("====================================\nEnter homework's ID number.");
                        homeworkRepository.addById(id, scanner.nextInt());
                    } catch (EntityNotFoundException e) {
                        System.out.println(e);
                    }
                } else if (conf.equals("2")) {
                    if (homeworkRepository.get(id) == null) {
                        System.out.println("There's no lecture with such ID"); continue;}
                    System.out.println("====================================\nEnter homework's ID to remove it from the lecture.");
                    homeworkRepository.deleteById(id, scanner.nextInt());
                } else if (conf.equals("3")) {
                    try {
                        if (addMaterialRepository.get(id) == null) {
                            System.out.println("There's no lecture with such ID"); continue;}
                        System.out.println("====================================\nEnter additional material's ID number.");
                        addMaterialRepository.addById(id, scanner.nextInt());
                    } catch (EntityNotFoundException e) {
                        System.out.println(e);
                    }
                } else if (conf.equals("4")) {
                    if (addMaterialRepository.get(id) == null) {
                        System.out.println("There's no lecture with such ID"); continue;}
                    System.out.println("====================================\nEnter additional material's ID to remove it from the lecture.");
                    addMaterialRepository.deleteById(id, scanner.nextInt());
                }
            } while (conf.equals("yes")||conf.equals("1")||conf.equals("2")||conf.equals("3")||conf.equals("4"));
        } else if (confirmation1.equals("3")) {
            lectureRepository.findAll();
        } System.out.println("++++++++++++++++++++++");
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
        } System.out.println("++++++++++++++++++++++");
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
        } System.out.println("++++++++++++++++++++++");
    }

    private static void homeworkMenu(String confirmation4, Scanner scanner) {
        HomeworkService homeworkService = new HomeworkService();
        HomeworkRepository homeworkRepository = HomeworkRepository.getInstance();
        if (confirmation4.equals("yes")) {
            homeworkService.printID();
        } else if (confirmation4.equals("1")) {
            do {
                Homework newHomework = HomeworkService.createHomeworkFromConsole();
                homeworkRepository.putIfAbsent(newHomework.getLectureID(), new ArrayList<>());
                homeworkRepository.get(newHomework.getLectureID()).add(newHomework);
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
        }System.out.println("++++++++++++++++++++++");
    }

    private static void addMaterialMenu(String confirmation5, Scanner scanner) {
        AdditionalMaterialService additionalMaterialService = new AdditionalMaterialService();
        AdditionalMaterialRepository additionalMaterialRepository = AdditionalMaterialRepository.getInstance();
        List<AdditionalMaterial> list = additionalMaterialRepository.toAddMaterialsList();
        Collections.sort(list);
        String conf;
        if (confirmation5.equals("yes")) {
            do {
                additionalMaterialService.printList(list);
                System.out.println("""
                        ==========================================
                        Enter "1" if you want to sort additional materials by lecture's ID or enter "2" if you want to sort by resource type.
                        Enter anything else to return to "Choose category" menu.""");
                conf = scanner.next();
                if (conf.equals("1")) Collections.sort(list, AdditionalMaterial.lectureIDComparator);
                else if (conf.equals("2")) Collections.sort(list, AdditionalMaterial.resourceTypeComparator);
            } while (conf.equals("1")||conf.equals("2"));
        } else if (confirmation5.equals("1")) {
            do {
                AdditionalMaterial additionalMaterial = AdditionalMaterialService.createAddMaterialFromConsole();
                additionalMaterialRepository.putIfAbsent(additionalMaterial.getLectureID(), new ArrayList<>());
                additionalMaterialRepository.get(additionalMaterial.getLectureID()).add(additionalMaterial);
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
                additionalMaterialService.findAllFromTheList(list);
                System.out.println("""
                        ==========================================
                        Enter "1" if you want to sort additional materials by lecture's ID or enter "2" if you want to sort by resource type.
                        Enter anything else to return to "Choose category" menu.""");
                conf = scanner.next();
                if (conf.equals("1")) Collections.sort(list, AdditionalMaterial.lectureIDComparator);
                else if (conf.equals("2")) Collections.sort(list, AdditionalMaterial.resourceTypeComparator);
            } while (conf.equals("1")||conf.equals("2"));
        } System.out.println("++++++++++++++++++++++");
    }
}