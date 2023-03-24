package com.academy.services;

import com.academy.ServerClient.Client;
import com.academy.ServerClient.Server;
import com.academy.ServerClient.Service;
import com.academy.exceptions.EntityNotFoundException;
import com.academy.models.*;
import com.academy.models.lectures.AdditionalMaterial;
import com.academy.models.lectures.Homework;
import com.academy.repository.CourseRepository;
import com.academy.repository.LectureRepository;
import com.academy.repository.PersonRepository;
import com.academy.repository.lectures.AdditionalMaterialRepository;
import com.academy.repository.lectures.HomeworkRepository;
import com.academy.serializationUtil.SerializationUtils;
import com.academy.services.lectures.AdditionalMaterialService;
import com.academy.services.lectures.HomeworkService;
import com.academy.util.LogService;
import com.academy.util.Logger;

import java.time.LocalDateTime;
import java.util.*;

public class MainService {
    private static final Logger LOGGER = new Logger(MainService.class.getName());
    private static final PersonService personService = new PersonService();
    public static Person[] studentsForControlWork() {
        Person[] studs = new Person[10];
        studs[0] = new Person(Role.STUDENT, "A.", "Arnold");
        studs[1] = new Person(Role.STUDENT, "B.", "Johnson");
        studs[2] = new Person(Role.STUDENT, "C.", "Evans");
        studs[3] = new Person(Role.STUDENT, "D.", "Johnson");
        studs[4] = new Person(Role.STUDENT, "E.", "Moriarty");
        studs[5] = new Person(Role.STUDENT, "F.", "Ferdinand");
        studs[6] = new Person(Role.STUDENT, "G.", "Stacy");
        studs[7] = new Person(Role.STUDENT, "I.", "Elba");
        studs[8] = new Person(Role.STUDENT, "J.", "McCain");
        studs[9] = new Person(Role.STUDENT, "K.", "Denver");
        return studs;
    }

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
                new AdditionalMaterial("Organic chemistry", ResourceType.BOOK), LocalDateTime.of(2023,2,14,16,25));
        lectureRepository.add(firstLecture);
        homeworkRepository.put(firstLecture.getID(), new ArrayList<>());
        for (Homework homework : firstLecture.getHomeworks()) {
            homeworkRepository.get(firstLecture.getID()).add(homework);}
        addMaterialRepository.put(firstLecture.getID(), new ArrayList<>());
        addMaterialRepository.get(firstLecture.getID()).add(firstLecture.getAdditionalMaterial());
        Lecture secondLecture = LectureService.createLecture("English", 75, new Homework[]{},
                new AdditionalMaterial("Big Fat Video-course", ResourceType.VIDEO), LocalDateTime.of(2023,2,14,17,55));
        lectureRepository.add(secondLecture);
        Lecture thirdLecture = LectureService.createLecture("Informatics", 70, new Homework[]{},
                new AdditionalMaterial("Head First Java", ResourceType.BOOK), LocalDateTime.of(2023,2,16,19,25));
        lectureRepository.add(thirdLecture);
        AdditionalMaterial additionalMaterial2 = new AdditionalMaterial("Holy Bible", ResourceType.BOOK);
        addMaterialRepository.put(thirdLecture.getID(), new ArrayList<>());
        addMaterialRepository.add(thirdLecture.getID(), thirdLecture.getAdditionalMaterial());
        addMaterialRepository.put(secondLecture.getID(), new ArrayList<>());
        addMaterialRepository.add(secondLecture.getID(), secondLecture.getAdditionalMaterial());
        addMaterialRepository.add(secondLecture.getID(), additionalMaterial2);
        SerializationUtils.serializeToFile(firstCourse);
        Person t1 = new Person(Role.TEACHER, "Count", "Dracula","-","dracula@gmail.com");
        secondLecture.setTeacherID(t1.getTeacherID());
        Person t2 = new Person(Role.TEACHER, "Hakeem", "Olajuwon","1234567890","-");
        Person t3 = new Person(Role.TEACHER, "Vega", "Nico","-","nicovega@ddd.com");
        Person t4 = new Person(Role.TEACHER, "Olena", "Abakumova","+380991080133","abakumova@ukr.net");
        Person s1 = new Person(Role.STUDENT, "Olena", "Pchilka","+3809977777","pchilka@ukr.net");
        Person s2 = new Person(Role.STUDENT, "Olha", "Kosach","+3809977779","kosach@ukr.net");
        Person s3 = new Person(Role.STUDENT, "Fernando", "Torres","+3809977569","torresF@esp.net");
        Person s4 = new Person(Role.STUDENT, "Gwen", "Stacy","+3809944477","stacyGwen@gmail.com");
        Person s5 = new Person(Role.STUDENT, "Aston", "Martin","+3806656977","astonMartin@gmail.com");
        personRepository.add(t1); personRepository.add(t2); personRepository.add(t3); personRepository.add(t4);
        personRepository.add(s1); personRepository.add(s2); personRepository.add(s3); personRepository.add(s4); personRepository.add(s5);
        personService.writeStudentsEmails();
    }

    private static int categoryNumMethod(Scanner scanner) {
        try {
            scanner.skip(".*");
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            LOGGER.warning("Incorrect input.", e);
            System.out.println(e + ": Incorrect input. Only integer values are allowed.");
            return categoryNumMethod(scanner);
        }
    }

    public static void chooseCategoryAndCreateLecture() {
        LOGGER.debug("Entering chooseCategoryAndCreateLecture() method in MainService class");
        Scanner scanner = new Scanner(System.in);
        LectureRepository lectureRepository = LectureRepository.getInstance();
        int categoryNumber;
        OUTER:
        while (true) {
            do {LOGGER.info("Choose category, enter a number");
                System.out.println("""
                        Choose category: press 1 - for "Course", 2 - for "Lecture", 3 - for "Student" or 4 - for "Teacher", 5 - for "Homework", 6 - for "Additional Material",
                        7 - for "Log", 8 - for "Control Work" or 9 - for "Server-Client".
                        Type "ex" to exit the program""");
                if (scanner.hasNext("ex")) break OUTER;
                try {
                    categoryNumber = scanner.nextInt();
                } catch (InputMismatchException e) {
                    LOGGER.warning("Incorrect input.", e);
                    System.out.println(e + ": Incorrect input. Only integer values are allowed.");
                    categoryNumber = categoryNumMethod(scanner);
                }

                switch (categoryNumber) {
                    case 1:
                        LOGGER.info("Course menu info"); CourseService.courseMenuTitle();
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
                        LOGGER.info("Lecture menu info"); LectureService.lectureMenuTitle();
                        String confirmation1 = scanner.next();
                        switch (confirmation1) {
                            case "yes", "no", "1", "2", "3", "4", "5" -> {
                                lectureMenu(confirmation1, scanner);
                                continue OUTER;
                            }
                            default -> {
                                continue;
                            }
                        }
                    case 3:
                        LOGGER.info("Student menu info"); PersonService.studentMenuTitle();
                        String confirmation2 = scanner.next();
                        switch (confirmation2) {
                            case "yes", "no", "1", "2", "3", "4" -> {
                                studentMenu(confirmation2, scanner);
                                continue OUTER;
                            }
                            default -> {
                                continue;
                            }
                        }
                    case 4:
                        LOGGER.info("Teacher menu info"); PersonService.teacherMenuTitle();
                        String confirmation3 = scanner.next();
                        switch (confirmation3) {
                            case "yes", "no", "1", "2", "3", "4" -> {
                                teacherMenu(confirmation3, scanner);
                                continue OUTER;
                            }
                            default -> {
                                continue;
                            }
                        }
                    case 5:
                        LOGGER.info("Homework menu info"); HomeworkService.homeworkMenuTitle();
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
                        LOGGER.info("Additional Material menu info"); AdditionalMaterialService.addMaterialMenuTitle();
                        String confirmation5 = scanner.next();
                        switch (confirmation5) {
                            case "yes", "no", "1", "2", "3", "4" -> {
                                addMaterialMenu(confirmation5, scanner);
                                continue OUTER;
                            }
                            default -> {
                                continue;
                            }
                        }
                    case 7:
                    LOGGER.info("Log menu info"); LogService.logMenuTitle();
                        String confirmation6 = scanner.next();
                        switch (confirmation6) {
                            case "yes":
                                System.out.println(LogService.readLog());
                                continue OUTER;
                            case "no":
                                continue OUTER;
                            case "1":
                                LogService.readMessagesOnly();
                                continue OUTER;
                            default:
                                continue;
                        }

                    case 8:
                        LOGGER.info("Control work menu info"); ControlWork.controlWorkMenuTitle();
                        String confirmation7 = scanner.next();
                        if (confirmation7.equals("yes")) {
                            ControlWork.startControlWork(studentsForControlWork());
                            try {
                                Thread.sleep(15000);
                                System.out.println("++++++++++++++++++++++");
                            } catch (InterruptedException e) {
                                LOGGER.error("Interrupted exception. Need to solve the problem", e);
                                throw new RuntimeException(e);
                            }
                            continue OUTER;
                        } else if (confirmation7.equals("no")) {
                            continue OUTER;
                        } else continue ;

                    case 9:
                        LOGGER.info("Server-client menu info"); Service.serverClientMenuTitle();
                        String confirmation8 = scanner.next();
                        if (confirmation8.equals("yes")) {
                            Thread server = new Thread(new Server(), "server");
                            server.start();
                            try {
                                Thread.sleep(300);
                            Thread client = new Thread(new Client(), "client");
                            client.start();
                                server.join();
                            } catch (InterruptedException e) {
                                LOGGER.error("Interrupted exception. Need to solve the problem", e);
                                throw new RuntimeException(e);
                            }
                            continue OUTER;
                        } else if (confirmation8.equals("no")) {
                            continue OUTER;
                        } else continue ;

                    default:
                        System.out.println("Please, enter a number from 1 to 9.");
                }
            } while (categoryNumber < 1 || categoryNumber > 9);
            while (Lecture.getCounterOfLectures() < 8) {
                Lecture newLecture = LectureService.createLectureFromConsole();
                lectureRepository.add(newLecture);
                LOGGER.info("Printing new lecture"); System.out.println(newLecture);
                if (Lecture.getCounterOfLectures() == 8) {
                    LOGGER.debug("Eight lectures have been created. You can't create more.\nExiting chooseCategoryAndCreateLecture()...");
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
        } LOGGER.info("Counter of lectures"); LectureService.printCounter();
        personService.writeStudentsEmails();
        LOGGER.debug("Exiting chooseCategoryAndCreateLecture() method...");
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
                LOGGER.debug("New course has been created successfully.");
                courseRepository.add(newCourse);
                System.out.println("""
                        ==========================================
                        Do you want to create new course? Enter "yes" to confirm.
                        Enter anything else to finish creating courses and return to "Choose category" menu.""");
            } while (scanner.next().equals("yes"));
        } else if (confirmation.equals("2")) {
            do {
                System.out.println("====================================\nEnter ID number of the course.");
                int id = 0;
                try {
                    id = scanner.nextInt();
                } catch (InputMismatchException ex) {
                    LOGGER.error("Incorrect input. Need to solve the problem", ex);
                    scanner.skip(".*");
                }
                try {
                    System.out.println(courseRepository.getById(id));
                } catch (EntityNotFoundException e) {
                    LOGGER.warning("There's no course with such ID", e);
                    System.out.println(e);
                }
                System.out.println("""
                        ====================================
                        Would you like to get another course? Enter "yes" to confirm.
                        Enter anything else to finish showing course's info and return to "Choose category" menu.""");
            } while (scanner.next().equals("yes"));
        } else if (confirmation.equals("3")) {
            courseRepository.findAll();
            LOGGER.info("All information about courses.");
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
                int id = 0;
                try {
                    id = scanner.nextInt();
                } catch (InputMismatchException ex){
                    LOGGER.error("Incorrect input. Need to solve the problem", ex);
                    scanner.skip(".*");
                }
                try {
                    System.out.println(lectureRepository.getById(id));
                    lectureService.printHomeworks(id); lectureService.printAddMaterials(id);
                    LOGGER.info("Printing info about lecture by it's ID.");
                    homeworkRepository.putIfAbsent(id, new ArrayList<>());
                    addMaterialRepository.putIfAbsent(id, new ArrayList<>());
                } catch (EntityNotFoundException e) {
                    LOGGER.warning("There's no lecture with such ID", e);
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
                        LOGGER.warning("There's no homework with such ID", e);
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
                        LOGGER.warning("There's no additional material with such ID", e);
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
            LOGGER.info("All information about lectures.");
        } else if (confirmation1.equals("4")) {
            do {
                System.out.println("Enter \"1\" to print lectures starting from the specified date. \"2\" to print lectures " +
                    "that start before the specified date.\n\"3\" to print lectures between the specified dates. " +
                    "Enter anything else to return to \"Choose category\" menu.");
                conf = scanner.next();
                if (conf.equals("1")) lectureService.printLecturesFromDate(lectureRepository.getAll(),
                        LectureService.dateFromConsole(scanner, true));
                else if (conf.equals("2")) lectureService.printLecturesBeforeDate(lectureRepository.getAll(),
                        LectureService.dateFromConsole(scanner, false));
                else if (conf.equals("3")) {
                    lectureService.printLecturesBetweenDates(lectureRepository.getAll(),
                            LectureService.dateFromConsole(scanner,true), LectureService.dateFromConsole(scanner,false));
                }
            } while (conf.equals("1")||conf.equals("2")||conf.equals("3"));
        } else if (confirmation1.equals("5")) {
            lectureService.printGroupedByTeachers();
            LOGGER.info("All information about lectures grouped by teachers.");
        }
        System.out.println("++++++++++++++++++++++");
    }

    private static void studentMenu(String confirmation2, Scanner scanner) {
        PersonRepository personRepository = PersonRepository.getInstance();
        Collections.sort(personRepository.getAll());
        if (confirmation2.equals("yes")) {
            personService.printStudentsID();
        } else if (confirmation2.equals("1")) {
            do {
                Person newStudent = personService.createStudentFromConsole();
                LOGGER.debug("New student has been created successfully.");
                personRepository.add(newStudent);
                System.out.println("""
                        ==========================================
                        Do you want to create new student? Enter "yes" to confirm.
                        Enter anything else to finish creating students and return to "Choose category" menu.""");
            } while (scanner.next().equals("yes"));
        } else if (confirmation2.equals("2")) {
            do {
                System.out.println("====================================\nEnter ID number of the student.");
                int id = 0;
                try {
                    id = scanner.nextInt();
                } catch (InputMismatchException ex) {
                    LOGGER.error("Incorrect input. Need to solve the problem", ex);
                    scanner.skip(".*");
                }
                try {
                    System.out.println(personRepository.getStudentById(id));
                } catch (EntityNotFoundException e) {
                    LOGGER.warning("There's no student with such ID.", e);
                    System.out.println(e);
                }
                System.out.println("""
                        ====================================
                        Would you like to get another student? Enter "yes" to confirm.
                        Enter anything else to finish showing student's info and return to "Choose category" menu.""");
            } while (scanner.next().equals("yes"));
        } else if (confirmation2.equals("3")) {
            personRepository.findAll(Role.STUDENT);
            LOGGER.info("All information about students.");
        } else if (confirmation2.equals("4")) {
            personService.printGroupedByEmail(Role.STUDENT);
            LOGGER.info("Emails and students who own these emails.");
        }
        System.out.println("++++++++++++++++++++++");
    }

    private static void teacherMenu(String confirmation3, Scanner scanner) {
        PersonRepository personRepository = PersonRepository.getInstance();
        Collections.sort(personRepository.getAll());
        if (confirmation3.equals("yes")) {
            personService.printTeachersID();
        } else if (confirmation3.equals("1")) {
            do {
                Person newTeacher = personService.createTeacherFromConsole();
                LOGGER.debug("New teacher has been created successfully.");
                personRepository.add(newTeacher);
                System.out.println("""
                        ==========================================
                        Do you want to create new teacher? Enter "yes" to confirm.
                        Enter anything else to finish creating teachers and return to "Choose category" menu.""");
            } while (scanner.next().equals("yes"));
        } else if (confirmation3.equals("2")) {
            do {
                System.out.println("====================================\nEnter ID number of the teacher.");
                int id = 0;
                try {
                    id = scanner.nextInt();
                } catch (InputMismatchException ex) {
                    LOGGER.error("Incorrect input. Need to solve the problem", ex);
                    scanner.skip(".*");
                }
                try {
                    System.out.println(personRepository.getTeacherById(id));
                } catch (EntityNotFoundException e) {
                    LOGGER.warning("There's no teacher with such ID.", e);
                    System.out.println(e);
                }
                System.out.println("""
                        ====================================
                        Would you like to get another teacher? Enter "yes" to confirm.
                        Enter anything else to finish showing teacher's info and return to "Choose category" menu.""");
            } while (scanner.next().equals("yes"));
        } else if (confirmation3.equals("3")) {
            personRepository.findAll(Role.TEACHER);
            LOGGER.info("All information about teachers.");
        } else if (confirmation3.equals("4")) {
            personService.printGroupedByEmail(Role.TEACHER);
            LOGGER.info("Emails and teachers who own these emails.");
        }
        System.out.println("++++++++++++++++++++++");
    }

    private static void homeworkMenu(String confirmation4, Scanner scanner) {
        HomeworkService homeworkService = new HomeworkService();
        HomeworkRepository homeworkRepository = HomeworkRepository.getInstance();
        if (confirmation4.equals("yes")) {
            homeworkService.printID();
        } else if (confirmation4.equals("1")) {
            do {
                Homework newHomework = HomeworkService.createHomeworkFromConsole();
                LOGGER.debug("New homework has been created successfully.");
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
                int id = 0;
                try{
                    id = scanner.nextInt();
                } catch (InputMismatchException ex){
                    LOGGER.error("Incorrect input. Need to solve the problem", ex);
                    scanner.skip(".*");
                }
                try {
                    System.out.println(homeworkRepository.getById(id));
                } catch (EntityNotFoundException e) {
                    LOGGER.warning("There's no homework with such ID", e);
                    System.out.println(e);
                }
                System.out.println("""
                        ====================================
                        Would you like to get another homework? Enter "yes" to confirm.
                        Enter anything else to finish showing homework's info and return to "Choose category" menu.""");
            } while (scanner.next().equals("yes"));
        } else if (confirmation4.equals("3")) {
            homeworkRepository.findAll();
            LOGGER.info("All information about homeworks.");
        }System.out.println("++++++++++++++++++++++");
    }

    private static void addMaterialMenu(String confirmation5, Scanner scanner) {
        AdditionalMaterialService additionalMaterialService = new AdditionalMaterialService();
        AdditionalMaterialRepository additionalMaterialRepository = AdditionalMaterialRepository.getInstance();
        List<AdditionalMaterial> list = additionalMaterialRepository.toAddMaterialsList();
        Collections.sort(list);
        String conf;
        String sortAddMatsMenu = """
                        ==========================================
                        Enter "1" if you want to sort additional materials by lecture's ID or enter "2" if you want to sort by resource type.
                        Enter "3" if you want to group additional materials by lectures.
                        Enter anything else to return to "Choose category" menu.""";
        if (confirmation5.equals("yes")) {
                additionalMaterialService.printList(list);
            do {
                System.out.println(sortAddMatsMenu);
                conf = scanner.next();
                if (conf.equals("1")) {list.sort(AdditionalMaterial.lectureIDComparator);
                    additionalMaterialService.printList(list);}
                else if (conf.equals("2")) {list.sort(AdditionalMaterial.resourceTypeComparator);
                    additionalMaterialService.printList(list);}
                else if (conf.equals("3")) additionalMaterialService.shortGroupedByLectures(additionalMaterialRepository.getAll());
            } while (conf.equals("1")||conf.equals("2")||conf.equals("3"));
        } else if (confirmation5.equals("1")) {
            do {
                AdditionalMaterial additionalMaterial = AdditionalMaterialService.createAddMaterialFromConsole();
                LOGGER.debug("New additional material has been created successfully.");
                additionalMaterialRepository.putIfAbsent(additionalMaterial.getLectureID(), new ArrayList<>());
                additionalMaterialRepository.add(additionalMaterial.getLectureID(), additionalMaterial);
                System.out.println("""
                        ==========================================
                        Do you want to create new additional material? Enter "yes" to confirm.
                        Enter anything else to finish creating additional materials and return to "Choose category" menu.""");
            } while (scanner.next().equals("yes"));
        } else if (confirmation5.equals("2")) {
            do {
                System.out.println("====================================\nEnter ID number of the additional material.");
                int id = 0;
                try {
                    id = scanner.nextInt();
                } catch (InputMismatchException ex){
                    LOGGER.error("Incorrect input. Need to solve the problem", ex);
                    scanner.skip(".*");
                }
                try {
                    System.out.println(additionalMaterialRepository.getById(id));
                } catch (EntityNotFoundException e) {
                    LOGGER.warning("There's no additional material with such ID", e);
                    System.out.println(e);
                }
                System.out.println("""
                        ====================================
                        Would you like to get another additional material? Enter "yes" to confirm.
                        Enter anything else to finish showing additional material's info and return to "Choose category" menu.""");
            } while (scanner.next().equals("yes"));
        } else if (confirmation5.equals("3")) {
                additionalMaterialService.findAllFromTheList(list);
            do {
                System.out.println(sortAddMatsMenu);
                conf = scanner.next();
                if (conf.equals("1")) {list.sort(AdditionalMaterial.lectureIDComparator);
                    additionalMaterialService.findAllFromTheList(list);}
                else if (conf.equals("2")) {list.sort(AdditionalMaterial.resourceTypeComparator);
                    additionalMaterialService.findAllFromTheList(list);}
                else if (conf.equals("3")) additionalMaterialService.printGroupedByLectures();
            } while (conf.equals("1")||conf.equals("2")||conf.equals("3"));
            LOGGER.info("All information about additional materials.");
        } else if (confirmation5.equals("4")) {
            do {
                System.out.println("Enter additional material's ID to remove it from database.");
                int id = 0;
                try {
                    id = scanner.nextInt();
                } catch (InputMismatchException ex){
                    LOGGER.error("Incorrect input. Need to solve the problem", ex);
                    scanner.skip(".*");
                }

                try {
                    additionalMaterialRepository.deleteById(additionalMaterialRepository.getById(id).getLectureID(), id);
                } catch (EntityNotFoundException e) {
                    LOGGER.warning(e.getMessage(), e);
                    System.out.println(e.getMessage());
                } System.out.println("""
                        ==========================================
                        Do you want to remove other additional material? Enter "yes" to confirm.
                        Enter anything else to return to "Choose category" menu.""");
            } while (scanner.next().equals("yes"));
        } System.out.println("++++++++++++++++++++++");
    }
}