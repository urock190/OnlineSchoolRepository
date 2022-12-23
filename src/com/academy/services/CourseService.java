package com.academy.services;

import com.academy.exceptions.ValidationErrorException;
import com.academy.models.Course;
import com.academy.models.Lecture;
import com.academy.models.Person;
import com.academy.repository.CourseRepository;
import com.academy.repository.LectureRepository;
import com.academy.repository.PersonRepository;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CourseService {
    private static final Pattern NAME_PATTERN = Pattern.compile("^\\s$|[\\W&&[\\S]]");
    private static String validationFindFalseMethod (Pattern pattern, Scanner scanner) throws ValidationErrorException {
        String newString = scanner.next() + scanner.nextLine();
        Matcher matcher = pattern.matcher(newString);
        if (!matcher.find()) return newString;
        else throw new ValidationErrorException();
    }
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
        PersonRepository personRepository = new PersonRepository();
        LectureRepository lectureRepository = new LectureRepository();
        Scanner scanner = new Scanner(System.in);
        String name = " ";
        boolean out = false;
        System.out.println("==========================\nCreate new course. \nEnter the name of this course.");
        while (!out) {
            try {
                name = validationFindFalseMethod(NAME_PATTERN, scanner);
                out = true;
            } catch (ValidationErrorException e) {
                System.out.println("The course name must contain only English letters and numbers.");
            }
        }
        Person teacher = PersonService.createTeacherFromConsole();
        personRepository.add(teacher);
        Person student = PersonService.createStudentFromConsole();
        personRepository.add(student);
        Lecture lecture = LectureService.createLectureFromConsole();
        lectureRepository.add(lecture);
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