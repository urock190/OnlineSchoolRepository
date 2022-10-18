package com.academy.services;

import com.academy.courses.Student;

public class StudentService {
    public static void printCounter(){
        System.out.println(Student.getCounterOfStudents());
    }
    public static Student createStudent() {
        return new Student();
    }
}
