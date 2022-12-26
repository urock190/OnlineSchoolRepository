package com.academy.repository;

import com.academy.exceptions.EntityNotFoundException;
import com.academy.models.Course;
import com.academy.models.Models;
import com.academy.services.RepositoryService;
import com.academy.services.SimpleIterator;

public class CourseRepository implements Repository {
    private static int capacity = 3;
    private static Course[] courses = new Course[capacity];
    private static RepositoryService <Course> courseRepositoryService = new RepositoryService<>(courses);

    @Override
    public Course[] getAll() {
        return courseRepositoryService.getElements();
    }

    @Override
    public int size(){
        return courseRepositoryService.size();
    }
    @Override
    public boolean isEmpty(){
        return courseRepositoryService.isEmpty();
    }

    @Override
    public void add(Models model) {
        if (getAll()[capacity-1] != null) expandArray();
        courseRepositoryService.add((Course) model);
    }

    @Override
    public void add(int index, Models model) {
        if (getAll()[capacity-1] != null) expandArray();
        courseRepositoryService.add(index, (Course) model);
    }
    @Override
    public Course get (int index){
        return courseRepositoryService.get(index);
    }
    @Override
    public void remove (int index) {
        courseRepositoryService.remove(index);
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

    private void expandArray(){
        int newCapacity = capacity;
        capacity = capacity*3/2 + 1;
        Course[] tmpArray = new Course[capacity];
        System.arraycopy(getAll(), 0, tmpArray, 0, newCapacity);
        courseRepositoryService.setElements(tmpArray);
    }
    @Override
    public Course getById(int ID) throws EntityNotFoundException{
        for (Course course : getAll()){
            if (course == null) continue;
            if (course.getID() == ID) return course;
        }
            throw new EntityNotFoundException("There's no course with such ID");
    }

    @Override
    public void deleteById(int ID) {
        for (int i = 0; i < size(); i++){
            if (getAll()[i] == null) continue;
            if (getAll()[i].getID() == ID) getAll()[i] = null;
        }
    }
    @Override
    public SimpleIterator<Course> iterator(){
        return new SimpleIterator<>(getAll());
    }
}
