package com.academy.services;

import com.academy.exceptions.EntityNotFoundException;
import com.academy.exceptions.ValidationErrorException;
import com.academy.models.Lecture;
import com.academy.models.Models;
import com.academy.models.Person;
import com.academy.models.lectures.AdditionalMaterial;
import com.academy.models.lectures.Homework;
import com.academy.myDateTimeFormats.DateTimeFormats;
import com.academy.repository.LectureRepository;
import com.academy.repository.PersonRepository;
import com.academy.repository.lectures.AdditionalMaterialRepository;
import com.academy.repository.lectures.HomeworkRepository;
import com.academy.services.lectures.AdditionalMaterialService;
import com.academy.services.lectures.HomeworkService;
import com.academy.util.Logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LectureService {
    private static final Logger LOGGER = new Logger(LectureService.class.getName());
    private static final Pattern namePattern = Pattern.compile("^\\s$|[\\W&&[\\S]]");
    private static final Pattern descriptionPattern = Pattern.compile("^\\s$|.{201,}");

    private static String validationFindFalseMethod(Pattern pattern, Scanner scanner) throws ValidationErrorException {
        String newString = scanner.next() + scanner.nextLine();
        Matcher matcher = pattern.matcher(newString);
        if (!matcher.find()) return newString;
        else throw new ValidationErrorException();
    }

    public static void printCounter() {
        System.out.println("number of lectures = " + Lecture.getCounterOfLectures());
    }

    public static void printCourseID(Lecture lecture) {
        System.out.println("course ID of lecture №" + lecture.getID() + " = " + lecture.getCourseID());
    }

    public static Lecture createLecture(String name, int amount, Homework[] homeworks, AdditionalMaterial additionalMaterial,
                                        LocalDateTime lectureDate) {
        return new Lecture(name, amount, homeworks, additionalMaterial, lectureDate);
    }

    public static Lecture createLecture(String name, int amount, String description, Homework[] homeworks,
                                        AdditionalMaterial additionalMaterial, LocalDateTime lectureDate) {
        return new Lecture(name, amount, description, homeworks, additionalMaterial, lectureDate);
    }

    public static Lecture createLectureFromConsole() {
        LOGGER.debug("Creating lecture.");
        HomeworkRepository homeworkRepository = HomeworkRepository.getInstance();
        AdditionalMaterialRepository addMaterialRepository = AdditionalMaterialRepository.getInstance();
        Scanner scanner = new Scanner(System.in);
        int capacity = 1;
        Homework[] homeworks = new Homework[capacity];
        String name = " ";
        boolean out = false;
        System.out.println("==========================\nCreate new lecture. \nEnter the name of this lecture");
        while (!out) {
            try {
                name = validationFindFalseMethod(namePattern, scanner);
                out = true;
            } catch (ValidationErrorException e) {
                LOGGER.warning("Validation error", e);
                System.out.println("Validation Error. The lecture name must contain only English letters and numbers.");
            }
        }
        System.out.println("Enter amount of lectures");
        int amount = 0;
        try {
            amount = scanner.nextInt();
        } catch (InputMismatchException ex) {
            LOGGER.error("Incorrect input. Need to solve the problem", ex);
        }
        System.out.println("Enter a short description of the lecture, please.");
        String description = " ";
        while (out) {
            try {
                description = validationFindFalseMethod(descriptionPattern, scanner);
                out = false;
            } catch (ValidationErrorException e) {
                LOGGER.warning("Validation error", e);
                System.out.println("The description must contain a maximum of 200 characters. Please enter a shorter description.");
            }
        }
        while (!out) {
            Homework homework = HomeworkService.createHomeworkFromConsole();
            homeworkRepository.putIfAbsent(homework.getLectureID() + 1, new ArrayList<>());
            homeworkRepository.get(homework.getLectureID() + 1).add(homework);
            homeworks[capacity - 1] = homework;
            int newCapacity = capacity;
            Homework[] tmpArray = new Homework[++capacity];
            System.arraycopy(homeworks, 0, tmpArray, 0, newCapacity);
            homeworks = tmpArray;
            System.out.println("==========================================\nDo you want to create new homework? " +
                    "Enter something to confirm.\nEnter \"no\" to finish creating homeworks.");
            if (scanner.next().equals("no")) {LOGGER.debug("Homeworks have been created successfully."); out = true;}
        }
        AdditionalMaterial additionalMaterial = AdditionalMaterialService.createAddMaterialFromConsole();
        LOGGER.debug("Additional material has been created successfully.");
        addMaterialRepository.putIfAbsent(additionalMaterial.getLectureID() + 1, new ArrayList<>());
        addMaterialRepository.get(additionalMaterial.getLectureID() + 1).add(additionalMaterial);
        System.out.println("Set the date of the lecture.\nEnter the date of the lecture in the format YYYY-MM-DD (year-month-day in numbers).");
        String date = scanner.next() + scanner.nextLine();
        System.out.println("Enter the time of the lecture in the format HH:mm (hours:minutes).");
        String time = scanner.next() + scanner.nextLine();
        String dateTime = date + "T" + time;
        LocalDateTime lectureDate = LocalDateTime.of(2023,12,31,23,59);
        try{lectureDate = LocalDateTime.from(DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(dateTime));}
        catch (DateTimeParseException exception){
            LOGGER.warning("Error during parsing. The date and time are probably entered incorrectly.", exception);
            System.out.println("Error during parsing. The date 31.12.2023 23:59 will be set instead of the date of the lecture.");
        }
        return new Lecture(name, amount, description, homeworks, additionalMaterial, lectureDate);
    }
    public static LocalDate dateFromConsole(Scanner scanner, boolean isStartDate){
        LocalDate date;
        if (isStartDate){
            System.out.println("Enter the start date in the format YYYY-MM-DD (year-month-day in numbers).");
            date = LocalDate.MIN;
        } else {
            System.out.println("Enter the end date in the format YYYY-MM-DD (year-month-day in numbers).");
            date = LocalDate.MAX;
        }
        String dateString = scanner.next() + scanner.nextLine();
        try{date = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(dateString));}
        catch (DateTimeParseException exception){
            LOGGER.warning("Error during parsing. The date is probably entered incorrectly.", exception);
            System.out.println("Error during parsing. The date " + date + " will be set by default.");
        }
        return date;
    }
    LectureRepository lectureRepository = LectureRepository.getInstance();
    PersonRepository personRepository = PersonRepository.getInstance();
    HomeworkRepository homeworkRepository = HomeworkRepository.getInstance();
    AdditionalMaterialRepository addMaterialRepository = AdditionalMaterialRepository.getInstance();

    public List<Lecture> lecturesBetweenDates (List<Lecture> lectures, LocalDate fromDate, LocalDate toDate){
        Predicate<Lecture> isAfterDate = lecture -> lecture.getLectureDate().toLocalDate().isAfter(fromDate)||
                lecture.getLectureDate().toLocalDate().isEqual(fromDate);
        Predicate<Lecture> isBeforeDate = lecture -> lecture.getLectureDate().toLocalDate().isBefore(toDate);
        return lectures.stream().filter(isAfterDate.and(isBeforeDate)).toList();
    }
    public List<Lecture> lecturesFromTheDate (List<Lecture> lectures, LocalDate fromDate){
        Predicate<Lecture> isAfterDate = lecture -> lecture.getLectureDate().toLocalDate().isAfter(fromDate)||
                lecture.getLectureDate().toLocalDate().isEqual(fromDate);
        return lectures.stream().filter(isAfterDate).toList();
    }
    public List<Lecture> lecturesBeforeDate (List<Lecture> lectures, LocalDate toDate){
        Predicate<Lecture> isBeforeDate = lecture -> lecture.getLectureDate().toLocalDate().isBefore(toDate);
        return lectures.stream().filter(isBeforeDate).toList();
    }

    Consumer<List<Lecture>> consumer = lectures -> {for (Lecture lecture : lectures) {
        if (lecture == null) continue;
        System.out.println(lecture);
        }
        System.out.println();
    };

    public void printLecturesBeforeDate(List<Lecture> lectures, LocalDate toDate){
        System.out.printf("======================\nLectures before date %s:\n", DateTimeFormats.dayMonthYear(toDate));
        consumer.accept(lecturesBeforeDate(lectures, toDate));
    }
    public void printLecturesFromDate(List<Lecture> lectures, LocalDate fromDate){
        System.out.printf("======================\nLectures from date %s:\n", DateTimeFormats.dayMonthYear(fromDate));
        consumer.accept(lecturesFromTheDate(lectures, fromDate));
    }
    public void printLecturesBetweenDates(List<Lecture> lectures, LocalDate fromDate, LocalDate toDate){
        System.out.printf("======================\nLectures between dates %s - %s:\n", DateTimeFormats.dayMonthYear(fromDate),
                DateTimeFormats.dayMonthYear(toDate));
        consumer.accept(lecturesBetweenDates(lectures, fromDate, toDate));
    }

    //Displays the lecture created earlier than all others, with the largest amount of additional materials.
    public void showLecture(){
       Optional<LocalDateTime> minCreationDate = lectureRepository.getAll().stream().map(Lecture::getCreationDate).
               min(Comparator.naturalOrder());
       int[] lectureIDs = lectureRepository.getAll().stream().filter(lecture ->
               lecture.getCreationDate().equals(minCreationDate.orElseThrow())).mapToInt(Models::getID).toArray();

       OptionalInt maxSize = Arrays.stream(lectureIDs).map(lectureID -> addMaterialRepository.get(lectureID).size()).max();

       int ID = Arrays.stream(lectureIDs).mapToObj(lectureID -> addMaterialRepository.get(lectureID)).
               filter(addMats -> addMats.size() == maxSize.orElseThrow()).flatMap(Collection::stream).
               map(AdditionalMaterial::getLectureID).findFirst().orElseThrow();
        try {
            System.out.println("Lecture created earlier than all others, with the largest amount of additional materials:\n" +
                    lectureRepository.getById(ID) + "\n");
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            LOGGER.error("There's no lecture with such ID", e);
        }
    }

    public void printID() {
        System.out.println("======================\nShort lectures info:");
        for (Lecture lecture : lectureRepository.getAll()) {
            if (lecture == null) continue;
            System.out.println("{Lecture \"" + lecture.getName() + "\" ID = " + lecture.getID() + '}');
        }
        System.out.println();
    }

    public void printHomeworks(int id) {
        System.out.println("======================\nList of homeworks:");
        if (homeworkRepository.get(id) == null) {
            System.out.println("List is empty.");
            return;
        }
        for (Homework homework : homeworkRepository.get(id)) {
            if (homework == null) continue;
            System.out.println(homework);
        }
        System.out.println();
    }

    public void printAddMaterials(int id) {
        System.out.println("======================\nList of additional materials:");
        if (addMaterialRepository.get(id) == null) {
            System.out.println("List is empty.");
            return;
        }
        for (AdditionalMaterial material : addMaterialRepository.get(id)) {
            if (material == null) continue;
            System.out.println(material);
        }
        System.out.println();
    }

    public void printGroupedByTeachers(){
        lectureRepository.getAll().stream().collect(Collectors.groupingBy(Lecture::getTeacherID)).
                forEach((key, value) -> {
                    try {
                        Person teacher = personRepository.getTeacherById(key);
                        System.out.println(teacher.getName() + " " + teacher.getLastName() + " - lectures:");
                        value.forEach(System.out::println);
                    } catch (EntityNotFoundException e) {
                        LOGGER.warning(e.getMessage(), e);
                    }
                });
    }

    public void addTeacherByID() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==========================\nAdd a teacher for a lecture. \nEnter lecture's ID first.");
        int lectureID = 0;
        while (lectureID == 0) {
            try {
                lectureID = scanner.nextInt();
            } catch (InputMismatchException ex) {
                LOGGER.error("Incorrect input. Need to solve the problem", ex);
            }
            try {
                lectureRepository.getById(lectureID);
                System.out.println("==========================\nEnter teacher's ID, please.");
                int teacherID = 0;
                while (teacherID == 0) {
                    try {
                        teacherID = scanner.nextInt();
                    } catch (InputMismatchException ex){
                        LOGGER.error("Incorrect input. Need to solve the problem", ex);
                    }
                    try {
                        personRepository.getTeacherById(teacherID);
                        lectureRepository.getById(lectureID).setTeacherID(teacherID);
                        System.out.println("Teacher's ID has been successfully added.\n=============================");
                        return;
                    } catch (EntityNotFoundException a) {
                        LOGGER.warning("EntityNotFoundException, no teacher with such ID", a);
                        System.out.println("There's no teacher with ID = " + teacherID + ". Please, enter correct ID or type" +
                                " \"ex\" for exit.");
                        teacherID = 0;
                    }
                    if (scanner.hasNext("ex")) break;
                }
            } catch (EntityNotFoundException e) {
                LOGGER.warning("EntityNotFoundException, no lecture with such ID", e);
                System.out.println("There's no lecture with ID = " + lectureID + ". Please, enter correct ID or type" +
                        " \"ex\" for exit.");
                lectureID = 0;
            }
            if (scanner.hasNext("ex")) break;
        }
    }

    public static void lectureMenuTitle(){
        System.out.println("You have choose the category \"Lecture\"");
        System.out.println("""
                                Do you want to print short info about lecture objects? Type "yes" to confirm. Type "no" to choose another category.
                                Enter "1" to add teacher's ID to the lecture. Enter "2" to get lecture by it's ID. Enter "3" to print full info about lectures.
                                Enter "4" if you want to filter and print lectures by dates. "5" to show information about lectures grouped by teachers.
                                Type anything else to continue creating lectures.""");
    }
}
