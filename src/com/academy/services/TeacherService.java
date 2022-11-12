package com.academy.services;

import com.academy.courses.Teacher;
import com.academy.repository.TeacherRepository;

public class TeacherService {
    public static void printCounter(){
        System.out.println(Teacher.getCounterOfTeachers());
    }
    public static Teacher createTeacher() {
        return new Teacher();
    }
    public static void printID(){
        System.out.println("======================\nShort teachers info:");
        for (Teacher teacher : TeacherRepository.getArray()) {
            if (teacher == null) break;
            System.out.println("{Teacher \"" + teacher.getName() + "\" ID = " + teacher.getID() + '}');
        }
        System.out.println();
    }
}
