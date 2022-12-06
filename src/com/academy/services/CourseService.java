package com.academy.services;

import com.academy.models.Course;
import com.academy.models.Lecture;
import com.academy.models.Person;
import com.academy.repository.CourseRepository;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CourseService {
    private static Pattern pattern = Pattern.compile("^\\s$|[\\W&&[\\S]]");
    public static void printCounter(){
        System.out.println(Course.getCounterOfCourses());
    }
    public Course createCourse() {
        return new Course();
    }
    public Course createCourse(String name, Person teacher, Person student, Lecture lecture) {
        return new Course(name, teacher, student, lecture);}
    public Course createCourse(String name, Person teacher, Person student) {
        return new Course(name, teacher, student);}

    public Course createCourseFromConsole(){
        Scanner scanner = new Scanner(System.in);
        String name = " ";
        boolean out = false;
        System.out.println("==========================\nCreate new course. \nEnter the name of this course.");
        while (!out) {
        name = scanner.next() + scanner.nextLine();
        Matcher matcher = pattern.matcher(name);
        if (matcher.find() == false) out = true;
        else System.out.println("The course name must contain only English letters and numbers.");}
        Person teacher = PersonService.createTeacherFromConsole();
        Person student = PersonService.createStudentFromConsole();
        Lecture lecture = LectureService.createLectureFromConsole();
        return new Course(name, teacher, student, lecture);
    }
    CourseRepository courseRepository = new CourseRepository();
    public void printID(){
        System.out.println("======================\nShort courses info:");
        for (Course course : courseRepository.getAll()) {
            if (course == null) continue;
            System.out.println("{Course \"" + course.getName() + "\" ID = " + course.getID() + '}');
        }
        System.out.println();
    }
}