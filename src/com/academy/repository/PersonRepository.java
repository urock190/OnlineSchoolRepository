package com.academy.repository;

import com.academy.exceptions.EntityNotFoundException;
import com.academy.models.Models;
import com.academy.models.Person;
import com.academy.models.Role;
import com.academy.services.SimpleIterator;

import java.util.ArrayList;
import java.util.List;

public class PersonRepository implements Repository {
    private static PersonRepository instance;
    private List<Person> persons;

    private PersonRepository() {
        persons = new ArrayList<>();
    }

    public static PersonRepository getInstance(){
        if (instance == null) instance = new PersonRepository();
        return instance;
    }

    @Override
    public int size(){
        return persons.size();
    }

    @Override
    public boolean isEmpty(){
        return persons.isEmpty();
    }

    @Override
    public void add(Models model) {
        persons.add((Person) model);
    }

    @Override
    public void add(int index, Models model) {
        persons.add(index, (Person) model);
    }

    @Override
    public Person get (int index){
        return persons.get(index);
    }

    @Override
    public void remove (int index) {
        persons.remove(index);
    }

    @Override
    public void findAll() {
        System.out.println("======================\nFull persons info:");
        SimpleIterator<Person> iterator = iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Person person = iterator.next();
            if (person == null) {i++; continue;}
            System.out.println(person);
        }
        if (i == size()) System.out.println("Array is empty.");
    }

    public void findAll (Role role) {
        System.out.println("======================\nFull " + role.toString().toLowerCase() + "s info:");
        SimpleIterator<Person> iterator = iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Person person = iterator.next();
            if (person == null || person.getRole() != role) {i++; continue;}
            System.out.println(person);
        }
        if (i == size()) System.out.println("Array is empty.");
    }

    @Override
    public List<Person> getAll() {
        return persons;
    }

    @Override
    public Person getById (int ID) throws EntityNotFoundException {
        for (Person person : persons){
            if (person == null) continue;
            if (person.getID() == ID) return person;
        }
        throw new EntityNotFoundException("There's no person with such ID");
    }

    public Person getTeacherById (int teacherID) throws EntityNotFoundException {
        for (Person teacher : persons){
            if (teacher == null) continue;
            if (teacher.getTeacherID() == teacherID) return teacher;
        }
        throw new EntityNotFoundException("There's no teacher with such ID");
    }

    public Person getStudentById (int studentID) throws EntityNotFoundException {
        for (Person student : persons){
            if (student == null) continue;
            if (student.getStudentID() == studentID) return student;
        }
        throw new EntityNotFoundException("There's no student with such ID");
    }
    @Override
    public void deleteById(int ID){
        for (int i = 0; i < size(); i++){
            if (persons.get(i) == null) continue;
            if (persons.get(i).getID() == ID) persons.remove(i);
        }
    }

    @Override
    public SimpleIterator<Person> iterator() {
        return new SimpleIterator<>(persons);
    }
}
