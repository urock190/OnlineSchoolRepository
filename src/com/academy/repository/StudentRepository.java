package com.academy.repository;

import com.academy.models.Student;
import com.academy.superclasses.Repository;

public class StudentRepository extends Repository {
    private static int capacity = 10;
    private static Student [] students = new Student[capacity];

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public Student[] getAll() {
        return students;
    }

    public static void addStudent (Student student){
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null){
                students[i] = student;
                break;
            }else if (i == capacity-1){
                expandArray();
            }
        }
    }
    private static void expandArray(){
        int newCapacity = capacity;
        capacity = capacity*3/2 + 1;
        Student[] tmpArray = new Student[capacity];
        System.arraycopy(students, 0, tmpArray, 0, newCapacity);
        students = tmpArray;
    }

    @Override
    public Student getById(int ID) {
        for (Student student : getAll()){
            if (student == null) continue;
            if (student.getID() == ID) return student;
        }
        return null;
    }

    @Override
    public void deleteById(int ID) {
        for (int i = 0; i < students.length; i++){
            if (students[i].getID() == ID) students[i] = null;
        }
    }
}
