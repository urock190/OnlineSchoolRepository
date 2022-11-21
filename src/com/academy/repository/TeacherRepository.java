package com.academy.repository;

import com.academy.models.Teacher;
import com.academy.superclasses.Repository;

public class TeacherRepository extends Repository {
    private static int capacity = 10;
    private static Teacher [] teachers = new Teacher[capacity];

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public Teacher[] getAll() {
        return teachers;
    }

    public static void addTeacher (Teacher teacher){
        for (int i = 0; i < teachers.length; i++) {
            if (teachers[i] == null){
                teachers[i] = teacher;
                break;
            }else if (i == capacity-1){
                expandArray();
            }
        }
    }
    private static void expandArray(){
        int newCapacity = capacity;
        capacity = capacity*3/2 + 1;
        Teacher[] tmpArray = new Teacher[capacity];
        System.arraycopy(teachers, 0, tmpArray, 0, newCapacity);
        teachers = tmpArray;
    }

    @Override
    public Teacher getById (int ID){
        for (Teacher teacher : getAll()){
            if (teacher == null) continue;
            if (teacher.getID() == ID) return teacher;
        }
        return null;
    }
    @Override
    public void deleteById(int ID){
        for (int i = 0; i < teachers.length; i++){
            if (teachers[i].getID() == ID) teachers[i] = null;
        }
    }
}
