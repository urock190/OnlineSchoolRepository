package com.academy.services;

import com.academy.courses.Course;

public class CourseService {
    public static void printCounter(){
        System.out.println(Course.getCounterOfCourses());
    }
    public static Course createCourse() {
        return new Course();
    }
}
