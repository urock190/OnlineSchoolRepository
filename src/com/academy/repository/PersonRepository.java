package com.academy.repository;

import com.academy.models.Person;
import com.academy.services.RepositoryService;

public class PersonRepository extends Repository{
    private static int capacity = 10;
    private static Person[] persons = new Person[capacity];
    private static RepositoryService <Person> personRepService = new RepositoryService<>(persons);

    public int size(){
        return personRepService.size();
    }

    public boolean isEmpty(){
        return personRepService.isEmpty();
    }

    public Person get (int index){
        return personRepService.get(index);
    }
    public void addPerson (Person person){
        if (getAll()[capacity-1] != null) expandArray();
        personRepService.add(person);
    }

    public void addPerson (int index, Person person) {
        if (getAll()[capacity-1] != null) expandArray();
        personRepService.add(index, person);
    }
    public void remove (int index) {
        personRepService.remove(index);
    }

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
    public Person getById (int ID){
        for (Person person : getAll()){
            if (person == null) continue;
            if (person.getID() == ID) return person;
        }
        return null;
    }
    public Person getTeacherById (int teacherID){
        for (Person teacher : getAll()){
            if (teacher == null) continue;
            if (teacher.getTeacherID() == teacherID) return teacher;
        }
        return null;
    }
    public Person getStudentById (int studentID){
        for (Person student : getAll()){
            if (student == null) continue;
            if (student.getStudentID() == studentID) return student;
        }
        return null;
    }
    @Override
    public void deleteById(int ID){
        for (int i = 0; i < size(); i++){
            if (getAll()[i] == null) continue;
            if (getAll()[i].getID() == ID) getAll()[i] = null;
        }
    }
}
