package com.academy.services;

import com.academy.exceptions.ValidationErrorException;
import com.academy.models.Course;
import com.academy.models.Lecture;
import com.academy.models.Person;
import com.academy.repository.CourseRepository;
import com.academy.repository.LectureRepository;
import com.academy.repository.PersonRepository;
import com.academy.util.Logger;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CourseService {
    private static final Logger LOGGER = new Logger(CourseService.class.getName());
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
        LOGGER.debug("Creating new course");
        PersonRepository personRepository = PersonRepository.getInstance();
        LectureRepository lectureRepository = LectureRepository.getInstance();
        Scanner scanner = new Scanner(System.in);
        String name = " ";
        boolean out = false;
        System.out.println("==========================\nCreate new course. \nEnter the name of this course.");
        while (!out) {
            try {
                name = validationFindFalseMethod(NAME_PATTERN, scanner);
                out = true;
            } catch (ValidationErrorException e) {
                LOGGER.warning("Validation error", e);
                System.out.println("The course name must contain only English letters and numbers.");
            }
        }
        Person teacher = PersonService.createTeacherFromConsole();
        LOGGER.debug("New teacher has been created successfully.");
        personRepository.add(teacher);
        Person student = PersonService.createStudentFromConsole();
        LOGGER.debug("New student has been created successfully.");
        personRepository.add(student);
        Lecture lecture = LectureService.createLectureFromConsole();
        LOGGER.debug("New lecture has been created successfully.");
        lectureRepository.add(lecture);
        return new Course(name, teacher, student, lecture);
    }
    CourseRepository courseRepository = CourseRepository.getInstance();
    public void printID(){
        System.out.println("======================\nShort courses info:");
        for (Course course : courseRepository.getAll()) {
            if (course == null) continue;
            System.out.println("{Course \"" + course.getName() + "\" ID = " + course.getID() + '}');
        }
        System.out.println();
    }

    public static void courseMenuTitle() {
        System.out.println("You have choose the category \"Course\"");
        System.out.println("""
                                Do you want to print short info about course objects? Type "yes" to confirm. Type "no" to choose another category.
                                Enter "1" to create new course. Enter "2" to get course by it's ID. Enter "3" to print full info about courses.
                                Type anything else to continue creating lectures.""");
    }
}