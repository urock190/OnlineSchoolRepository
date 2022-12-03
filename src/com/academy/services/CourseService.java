package com.academy.services;

import com.academy.models.Course;
import com.academy.models.Lecture;
import com.academy.models.Person;
import com.academy.repository.CourseRepository;

public class CourseService {
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