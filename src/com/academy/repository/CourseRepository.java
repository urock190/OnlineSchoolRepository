package com.academy.repository;

import com.academy.models.Course;
import com.academy.services.RepositoryService;

public class CourseRepository extends Repository {
    private static int capacity = 3;
    private static Course[] courses = new Course[capacity];
    private static RepositoryService <Course> courseRepositoryService = new RepositoryService<>(courses);
    @Override
    public Course[] getAll() {
        return courseRepositoryService.getElements();
    }

    public int size(){
        return courseRepositoryService.size();
    }

    public boolean isEmpty(){
        return courseRepositoryService.isEmpty();
    }

    public Course get (int index){
        return courseRepositoryService.get(index);
    }
    public void addCourse (Course course){
        if (getAll()[capacity-1] != null) expandArray();
        courseRepositoryService.add(course);
    }

    public void addCourse (int index, Course course) {
        if (getAll()[capacity-1] != null) expandArray();
        courseRepositoryService.add(index, course);
    }
    public void remove (int index) {
        courseRepositoryService.remove(index);
    }
    private void expandArray(){
        int newCapacity = capacity;
        capacity = capacity*3/2 + 1;
        Course[] tmpArray = new Course[capacity];
        System.arraycopy(getAll(), 0, tmpArray, 0, newCapacity);
        courseRepositoryService.setElements(tmpArray);
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
        for (int i = 0; i < size(); i++){
            if (getAll()[i] == null) continue;
            if (getAll()[i].getID() == ID) getAll()[i] = null;
        }
    }
}
