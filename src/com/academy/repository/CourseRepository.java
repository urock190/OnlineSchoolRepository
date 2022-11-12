package com.academy.repository;

import com.academy.courses.Course;

public class CourseRepository {
    private static int capacity = 3;
    private static Course[] courses = new Course[capacity];

    public static void addCourse (Course course){
        for (int i = 0; i < courses.length; i++) {
            if (courses[i] == null){
                courses[i] = course;
            break;
            }else if (i == capacity-1){
                expandArray();
            }
        }
    }
    private static void expandArray(){
        int newCapacity = capacity;
        capacity = capacity*3/2 + 1;
        Course[] tmpArray = new Course[capacity];
        System.arraycopy(courses, 0, tmpArray, 0, newCapacity);
        courses = tmpArray;
    }
    public static Course[] getArray(){
        return courses;
    }
}
