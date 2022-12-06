package com.academy.services;

import com.academy.models.Lecture;
import com.academy.models.lectures.AdditionalMaterial;
import com.academy.models.lectures.Homework;
import com.academy.repository.LectureRepository;
import com.academy.repository.PersonRepository;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LectureService {
    private static Pattern pattern = Pattern.compile("^\\s$|[\\W&&[\\S]]");
    private static Pattern pattern1 = Pattern.compile("^\\s$|.{201,}");
    public static void printCounter(){
    System.out.println("number of lectures = " + Lecture.getCounterOfLectures());
    }
    public static void printCourseID(Lecture lecture){
        System.out.println("course ID of lecture №" + lecture.getID() + " = " + lecture.getCourseID());
    }
    public static Lecture createLecture() {
        return new Lecture();
    }
    public static Lecture createLecture(String name, int amount, Homework homework, AdditionalMaterial additionalMaterial){
        return new Lecture(name, amount, homework, additionalMaterial);
    }
    public static Lecture createLecture(String name, int amount, String description, Homework homework,
                                        AdditionalMaterial additionalMaterial){
        return new Lecture(name, amount, description, homework, additionalMaterial);
    }
    public static Lecture createLectureFromConsole() {
        Scanner scanner = new Scanner(System.in);
        String name = " ";
        boolean out = false;
        System.out.println("==========================\nCreate new lecture. \nEnter the name of this lecture");
        while (!out) {
            name = scanner.next() + scanner.nextLine();
            Matcher matcher = pattern.matcher(name);
            if (matcher.find() == false) out = true;
            else System.out.println("The lecture name must contain only English letters and numbers.");}
        System.out.println("Enter amount of lectures");
        int amount = scanner.nextInt();
        System.out.println("Enter a short description of the lecture, please.");
        String description  = " ";
        while (out) {
            description = scanner.next() + scanner.nextLine();
            Matcher matcher = pattern1.matcher(description);
            if (matcher.find() == false) out = false;
            else System.out.println("The description must contain a maximum of 200 characters. You have entered " +
                            description.length() + " characters. Please enter a shorter description.");}
        System.out.println("Enter homework's name");
        String homeworkName = scanner.next() + scanner.nextLine();
        System.out.println("Enter number of tasks");
        int numberOfTasks = scanner.nextInt();
        Homework homework = new Homework(homeworkName, numberOfTasks);
        System.out.println("Enter the name of additional material");
        String addMatName = scanner.next() + scanner.nextLine();
        System.out.println("Enter amount of articles");
        int numberOfArticles = scanner.nextInt();
        AdditionalMaterial additionalMaterial = new AdditionalMaterial(addMatName, numberOfArticles);
        return new Lecture(name, amount, description, homework, additionalMaterial);
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
        int lectureID;
        INNER:
        do {
            lectureID = scanner.nextInt();
            if (lectureRepository.getById(lectureID) != null){
                System.out.println("==========================\nEnter teacher's ID, please.");
                int teacherID;
                do {
                    teacherID = scanner.nextInt();
                    if (personRepository.getTeacherById(teacherID) != null){
                        lectureRepository.getById(lectureID).setPersonID(teacherID);
                        System.out.println("Teacher's ID has been successfully added.\n=============================");
                        break INNER;
                    }else {System.out.println("There's no teacher with ID = " + teacherID + ". Please, enter correct ID or type" +
                            " \"ex\" for exit.");}
                    if(scanner.hasNext("ex")) break;
                }while (personRepository.getTeacherById(teacherID) == null);
            }else {System.out.println("There's no lecture with ID = " + lectureID + ". Please, enter correct ID or type" +
                    " \"ex\" for exit.");}
            if(scanner.hasNext("ex")) break;
        }while (lectureRepository.getById(lectureID) == null);
    }
}
