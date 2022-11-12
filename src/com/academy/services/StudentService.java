package com.academy.services;

import com.academy.courses.Student;
import com.academy.repository.StudentRepository;

public class StudentService {
    public static void printCounter(){
        System.out.println(Student.getCounterOfStudents());
    }
    public static Student createStudent() {
        return new Student();
    }
    public static void printID(){
        System.out.println("======================\nShort students info:");
        for (Student student : StudentRepository.getArray()) {
            if (student == null) break;
            System.out.println("{Student \"" + student.getName() + "\" ID = " + student.getID() + '}');
        }
        System.out.println();
    }
}
