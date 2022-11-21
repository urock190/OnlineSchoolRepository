package com.academy.services;

import com.academy.models.Student;
import com.academy.repository.StudentRepository;

public class StudentService {
    public static void printCounter(){
        System.out.println(Student.getCounterOfStudents());
    }
    public static Student createStudent() {
        return new Student();
    }
    StudentRepository studentRepository = new StudentRepository();
    public void printID(){
        System.out.println("======================\nShort students info:");
        for (Student student : studentRepository.getAll()) {
            if (student == null) continue;
            System.out.println("{Student \"" + student.getName() + "\" ID = " + student.getID() + '}');
        }
        System.out.println();
    }
}
