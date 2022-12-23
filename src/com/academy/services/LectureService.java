package com.academy.services;

import com.academy.exceptions.EntityNotFoundException;
import com.academy.exceptions.ValidationErrorException;
import com.academy.models.Lecture;
import com.academy.models.lectures.AdditionalMaterial;
import com.academy.models.lectures.Homework;
import com.academy.repository.LectureRepository;
import com.academy.repository.PersonRepository;
import com.academy.repository.lectures.HomeworkRepository;
import com.academy.services.lectures.HomeworkService;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LectureService {
    private static final Pattern namePattern = Pattern.compile("^\\s$|[\\W&&[\\S]]");
    private static final Pattern descriptionPattern = Pattern.compile("^\\s$|.{201,}");
    private static String validationFindFalseMethod (Pattern pattern, Scanner scanner) throws ValidationErrorException {
        String newString = scanner.next() + scanner.nextLine();
        Matcher matcher = pattern.matcher(newString);
        if (!matcher.find()) return newString;
        else throw new ValidationErrorException();
    }
    public static void printCounter(){
    System.out.println("number of lectures = " + Lecture.getCounterOfLectures());
    }
    public static void printCourseID(Lecture lecture){
        System.out.println("course ID of lecture №" + lecture.getID() + " = " + lecture.getCourseID());
    }
    public static Lecture createLecture() {
        return new Lecture();
    }
    public static Lecture createLecture(String name, int amount, Homework [] homeworks, AdditionalMaterial additionalMaterial){
        return new Lecture(name, amount, homeworks, additionalMaterial);
    }
    public static Lecture createLecture(String name, int amount, String description, Homework [] homeworks,
                                        AdditionalMaterial additionalMaterial){
        return new Lecture(name, amount, description, homeworks, additionalMaterial);
    }
    public static Lecture createLectureFromConsole() {
        HomeworkRepository homeworkRepository = new HomeworkRepository();
        Scanner scanner = new Scanner(System.in);
        int capacity = 1;
        Homework [] homeworks= new Homework[capacity];
        String name = " ";
        boolean out = false;
        System.out.println("==========================\nCreate new lecture. \nEnter the name of this lecture");
        while (!out) {
            try{
            name = validationFindFalseMethod(namePattern, scanner);
            out = true;
            } catch (ValidationErrorException e) {
                System.out.println("Validation Error. The lecture name must contain only English letters and numbers.");
            }
        }
        System.out.println("Enter amount of lectures");
        int amount = scanner.nextInt();
        System.out.println("Enter a short description of the lecture, please.");
        String description  = " ";
        while (out) {
            try {
                description = validationFindFalseMethod(descriptionPattern, scanner);
                out = false;
            } catch (ValidationErrorException e) {
                System.out.println("The description must contain a maximum of 200 characters. You have entered " +
                        description.length() + " characters. Please enter a shorter description.");
            }
        }
        while (!out) {
            Homework homework = HomeworkService.createHomeworkFromConsole();
            homeworkRepository.add(homework);
            homeworks[capacity-1] = homework;
            int newCapacity = capacity;
            Homework[] tmpArray = new Homework[++capacity];
            System.arraycopy(homeworks, 0, tmpArray, 0, newCapacity);
            homeworks = tmpArray;
            System.out.println("==========================================\nDo you want to create new homework? " +
                    "Enter something to confirm.\nEnter \"no\" to finish creating homeworks.");
            if (scanner.next().equals("no")) out = true;
        }
        System.out.println("Enter the name of additional material");
        String addMatName = scanner.next() + scanner.nextLine();
        System.out.println("Enter amount of articles");
        int numberOfArticles = scanner.nextInt();
        AdditionalMaterial additionalMaterial = new AdditionalMaterial(addMatName, numberOfArticles);
        return new Lecture(name, amount, description, homeworks, additionalMaterial);
        }
        LectureRepository lectureRepository = new LectureRepository();
    PersonRepository personRepository = new PersonRepository();
    public void printID(){
        System.out.println("======================\nShort lectures info:");
        for (Lecture lecture : lectureRepository.getAll()) {
            if (lecture == null) continue;
            System.out.println("{Lecture \"" + lecture.getName() + "\" ID = " + lecture.getID() + '}');
        }
        System.out.println();
    }
    public void addTeacherByID() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==========================\nAdd a teacher for a lecture. \nEnter lecture's ID first.");
        int lectureID = 0;
        INNER:
        while (lectureID == 0) {
            lectureID = scanner.nextInt();
            try {
                lectureRepository.getById(lectureID);
                System.out.println("==========================\nEnter teacher's ID, please.");
                int teacherID = 0;
                while (teacherID == 0) {
                        teacherID = scanner.nextInt();
                        try{
                            personRepository.getTeacherById(teacherID);
                            lectureRepository.getById(lectureID).setPersonID(teacherID);
                            System.out.println("Teacher's ID has been successfully added.\n=============================");
                            break INNER;
                        }catch (EntityNotFoundException a) {
                            System.out.println("There's no teacher with ID = " + teacherID + ". Please, enter correct ID or type" +
                                " \"ex\" for exit.");
                            teacherID = 0;}
                        if(scanner.hasNext("ex")) break;
                    }
            } catch (EntityNotFoundException e) {
                System.out.println("There's no lecture with ID = " + lectureID + ". Please, enter correct ID or type" +
                    " \"ex\" for exit.");
                lectureID = 0;
            }
            if(scanner.hasNext("ex")) break;
        }
    }
}
