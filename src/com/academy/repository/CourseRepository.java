package com.academy.repository;

import com.academy.models.Course;

public class CourseRepository extends Repository {
    private static int capacity = 3;
    private static Course[] courses = new Course[capacity];

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    @Override
    public Course[] getAll() {
        return courses;
    }

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
    @Override
    public Course getById(int ID) {
        for (Course course : getAll()){
            if (course == null) continue;
            if (course.getID() == ID) return course;
        }
        return null;
    }

    @Override
    public void deleteById(int ID) {
        for (int i = 0; i < courses.length; i++){
        if (courses[i].getID() == ID) courses[i] = null;}
    }
}
