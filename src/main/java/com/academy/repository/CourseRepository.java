package com.academy.repository;

import com.academy.exceptions.EntityNotFoundException;
import com.academy.models.Course;
import com.academy.models.Models;
import com.academy.services.SimpleIterator;

import java.util.ArrayList;
import java.util.List;

public class CourseRepository implements Repository {
    private static CourseRepository instance;
    private List<Course> courses;

    private CourseRepository() {
        courses = new ArrayList<>();
    }

    public static CourseRepository getInstance(){
        if (instance == null) instance = new CourseRepository();
        return instance;
    }

    @Override
    public List<Course> getAll() {
        return courses;
    }

    @Override
    public int size(){
        return courses.size();
    }
    @Override
    public boolean isEmpty(){
        return courses.isEmpty();
    }

    @Override
    public void add(Models model) {
        courses.add((Course) model);
    }

    @Override
    public void add(int index, Models model) {
        courses.add(index, (Course) model);
    }
    @Override
    public Course get (int index){
        return courses.get(index);
    }
    @Override
    public void remove (int index) {
        courses.remove(index);
    }
    @Override
    public void findAll() {
        System.out.println("======================\nFull courses info:");
        SimpleIterator<Course> iterator = iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Course course = iterator.next();
            if (course == null) {i++; continue;}
            System.out.println(course);
        }
        if (i == size()) System.out.println("Array is empty.");
    }
    @Override
    public Course getById(int ID) throws EntityNotFoundException{
        for (Course course : courses){
            if (course == null) continue;
            if (course.getID() == ID) return course;
        }
            throw new EntityNotFoundException("There's no course with such ID");
    }

    @Override
    public void deleteById(int ID) {
        for (int i = 0; i < size(); i++){
            if (courses.get(i) == null) continue;
            if (courses.get(i).getID() == ID) courses.remove(i);
        }
    }
    @Override
    public SimpleIterator<Course> iterator(){
        return new SimpleIterator<>(courses);
    }
}
