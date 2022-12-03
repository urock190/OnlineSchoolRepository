package com.academy.repository;

import com.academy.models.Person;

public class PersonRepository extends Repository{
    private static int capacity = 10;
    private static Person[] persons = new Person[capacity];

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public Person[] getAll() {
        return persons;
    }

    public static void addPerson (Person person){
        for (int i = 0; i < persons.length; i++) {
            if (persons[i] == null){
                persons[i] = person;
                break;
            }else if (i == capacity-1){
                expandArray();
            }
        }
    }
    private static void expandArray(){
        int newCapacity = capacity;
        capacity = capacity*3/2 + 1;
        Person[] tmpArray = new Person[capacity];
        System.arraycopy(persons, 0, tmpArray, 0, newCapacity);
        persons = tmpArray;
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
        for (int i = 0; i < persons.length; i++){
            if (persons[i].getID() == ID) persons[i] = null;
        }
    }
}
