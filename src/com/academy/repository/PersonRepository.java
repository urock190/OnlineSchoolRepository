package com.academy.repository;

import com.academy.exceptions.EntityNotFoundException;
import com.academy.models.Models;
import com.academy.models.Person;
import com.academy.services.RepositoryService;

public class PersonRepository implements Repository {
    private static int capacity = 10;
    private static Person[] persons = new Person[capacity];
    private static RepositoryService <Person> personRepService = new RepositoryService<>(persons);

    @Override
    public int size(){
        return personRepService.size();
    }

    @Override
    public boolean isEmpty(){
        return personRepService.isEmpty();
    }

    @Override
    public void add(Models model) {
        if (getAll()[capacity-1] != null) expandArray();
        personRepService.add((Person) model);
    }

    @Override
    public void add(int index, Models model) {
        if (getAll()[capacity-1] != null) expandArray();
        personRepService.add(index, (Person) model);
    }

    @Override
    public Person get (int index){
        return personRepService.get(index);
    }

    @Override
    public void remove (int index) {
        personRepService.remove(index);
    }
    @Override
    public Person[] getAll() {
        return personRepService.getElements();
    }

    private void expandArray(){
        int newCapacity = capacity;
        capacity = capacity*3/2 + 1;
        Person[] tmpArray = new Person[capacity];
        System.arraycopy(getAll(), 0, tmpArray, 0, newCapacity);
        personRepService.setElements(tmpArray);
    }

    @Override
    public Person getById (int ID) throws EntityNotFoundException {
        for (Person person : getAll()){
            if (person == null) continue;
            if (person.getID() == ID) return person;
        }
        throw new EntityNotFoundException("There's no person with such ID");
    }
    public Person getTeacherById (int teacherID) throws EntityNotFoundException {
        for (Person teacher : getAll()){
            if (teacher == null) continue;
            if (teacher.getTeacherID() == teacherID) return teacher;
        }
        throw new EntityNotFoundException("There's no teacher with such ID");
    }
    public Person getStudentById (int studentID) throws EntityNotFoundException {
        for (Person student : getAll()){
            if (student == null) continue;
            if (student.getStudentID() == studentID) return student;
        }
        throw new EntityNotFoundException("There's no student with such ID");
    }
    @Override
    public void deleteById(int ID){
        for (int i = 0; i < size(); i++){
            if (getAll()[i] == null) continue;
            if (getAll()[i].getID() == ID) getAll()[i] = null;
        }
    }
}
