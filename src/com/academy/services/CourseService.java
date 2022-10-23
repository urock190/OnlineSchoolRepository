package com.academy.services;

import com.academy.courses.Course;
import com.academy.courses.Lecture;
import com.academy.courses.Student;
import com.academy.courses.Teacher;

public class CourseService {
    public static void printCounter(){
        System.out.println(Course.getCounterOfCourses());
    }
    public Course createCourse() {
        return new Course();
    }
    public Course createCourse(String name, Teacher teacher, Student student, Lecture lecture) {
        return new Course(name, teacher, student, lecture);}
}