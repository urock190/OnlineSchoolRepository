package com.academy.repository;

import com.academy.courses.Teacher;

public class TeacherRepository {
    private static int capacity = 10;
    private static Teacher [] teachers = new Teacher[capacity];

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
    public static Teacher[] getArray(){
        return teachers;
    }
}
