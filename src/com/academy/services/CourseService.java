package com.academy.services;

import com.academy.exceptions.EntityNotFoundException;
import com.academy.exceptions.ValidationErrorException;
import com.academy.models.Course;
import com.academy.models.Lecture;
import com.academy.models.Person;
import com.academy.models.lectures.AdditionalMaterial;
import com.academy.models.lectures.Homework;
import com.academy.repository.CourseRepository;
import com.academy.repository.LectureRepository;
import com.academy.repository.PersonRepository;
import com.academy.repository.lectures.AdditionalMaterialRepository;
import com.academy.repository.lectures.HomeworkRepository;
import com.academy.util.Logger;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CourseService {
    private static final Logger LOGGER = new Logger(CourseService.class.getName());
    private static final Pattern NAME_PATTERN = Pattern.compile("^\\s$|[\\W&&[\\S]]");
    CourseRepository courseRepository = CourseRepository.getInstance();
    PersonRepository personRepository = PersonRepository.getInstance();
    LectureRepository lectureRepository = LectureRepository.getInstance();
    AdditionalMaterialRepository materialRepository = AdditionalMaterialRepository.getInstance();
    HomeworkRepository homeworkRepository = HomeworkRepository.getInstance();
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
    public void printID(){
        System.out.println("======================\nShort courses info:");
        for (Course course : courseRepository.getAll()) {
            if (course == null) continue;
            System.out.println("{Course \"" + course.getName() + "\" ID = " + course.getID() + '}');
        }
        System.out.println();
    }
    public void printLecturesOfThisCourse(Course course){
        System.out.println("======================\nShort info about lectures of this course:");
        try {
            for (Lecture lecture : lectureRepository.getByCourseId(course.getID())){
                if (lecture == null) continue;
                System.out.println(lecture);
            }
        } catch (EntityNotFoundException e) {
            LOGGER.warning("There's no lectures with such course ID", e);
            System.out.println(e);
        }
    }
    public void printStudentsOfThisCourse(Course course){
        System.out.println("======================\nShort info about students of this course:");
        try {
            for (Person student : personRepository.getStudentsByCourseId(course.getID())){
                if (student == null) continue;
                System.out.println(student);
            }
        } catch (EntityNotFoundException e) {
            LOGGER.warning("There's no students with such course ID", e);
            System.out.println(e);
        }
    }

    public void printTeachersOfThisCourse(Course course){
        System.out.println("======================\nShort info about teachers of this course:");
        try {
            for (Person teacher : personRepository.getTeachersByCourseId(course.getID())){
                if (teacher == null) continue;
                System.out.println(teacher);
            }
        } catch (EntityNotFoundException e) {
            LOGGER.warning("There's no teachers with such course ID", e);
            System.out.println(e);
        }
    }

    public void printAddMatsOfThisCourse(Course course){
        System.out.println("======================\nShort info about additional materials of this course:");
        try {
            for (AdditionalMaterial additionalMaterial : materialRepository.getByCourseId(course.getID())){
                if (additionalMaterial == null) continue;
                System.out.println(additionalMaterial);
            }
        } catch (EntityNotFoundException e) {
            LOGGER.warning("There's no additional materials with such course ID", e);
            System.out.println(e);
        }
    }

    public void printHomeworksOfThisCourse(Course course){
        System.out.println("======================\nShort info about homeworks of this course:");
        try {
            for (Homework homework : homeworkRepository.getByCourseId(course.getID())){
                if (homework == null) continue;
                System.out.println(homework);
            }
        } catch (EntityNotFoundException e) {
            LOGGER.warning("There's no homeworks with such course ID", e);
            System.out.println(e);
        }
    }

    public void printCourseInfo(Course course){
        System.out.println(course);
        printLecturesOfThisCourse(course);
        printStudentsOfThisCourse(course);
        printTeachersOfThisCourse(course);
        printAddMatsOfThisCourse(course);
        printHomeworksOfThisCourse(course);
    }
    public static void courseMenuTitle() {
        System.out.println("You have choose the category \"Course\"");
        System.out.println("""
                                Do you want to print short info about course objects? Type "yes" to confirm. Type "no" to choose another category.
                                Enter "1" to create new course. Enter "2" to get course by it's ID. Enter "3" to print full info about courses.
                                Type anything else to continue creating lectures.""");
    }
}