package com.academy.repository;

import com.academy.courses.Student;

public class StudentRepository {
    private static int capacity = 10;
    private static Student [] students = new Student[capacity];

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
    public static Student[] getArray(){
        return students;
    }
}
