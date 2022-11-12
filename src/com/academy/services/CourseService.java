package com.academy.services;

import com.academy.courses.Course;
import com.academy.courses.Lecture;
import com.academy.courses.Student;
import com.academy.courses.Teacher;
import com.academy.repository.CourseRepository;

public class CourseService {
    public static void printCounter(){
        System.out.println(Course.getCounterOfCourses());
    }
    public Course createCourse() {
        return new Course();
    }
    public Course createCourse(String name, Teacher teacher, Student student, Lecture lecture) {
        return new Course(name, teacher, student, lecture);}
    public Course createCourse(String name, Teacher teacher, Student student) {
        return new Course(name, teacher, student);}
    /**public Course createCourseFromConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==========================\nCreate new Course. \nEnter the name of this course");
        String name = scanner.next();
        System.out.println("Enter teacher's name");
        String teacherName = scanner.next();
        System.out.println("Enter teacher's second name");
        String teacherSecondName = scanner.next();
        Teacher teacher = new Teacher(teacherName, teacherSecondName);
        System.out.println("Enter student's name");
        String studentName = scanner.next();
        System.out.println("Enter student's second name");
        String studentSecondName = scanner.next();
        Student student = new Student (studentName, studentSecondName);
        return new Course (name, teacher, student);
    }*/
    public static void printID(){
        System.out.println("======================\nShort courses info:");
        for (Course course : CourseRepository.getArray()) {
            if (course == null) break;
            System.out.println("{Course \"" + course.getName() + "\" ID = " + course.getID() + '}');
        }
        System.out.println();
    }
}