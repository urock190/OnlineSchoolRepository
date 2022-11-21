package com.academy.services;

import com.academy.models.Teacher;
import com.academy.repository.TeacherRepository;

public class TeacherService {
    public static void printCounter(){
        System.out.println(Teacher.getCounterOfTeachers());
    }
    public static Teacher createTeacher() {
        return new Teacher();
    }
    TeacherRepository teacherRepository = new TeacherRepository();
    public void printID(){
        System.out.println("======================\nShort teachers info:");
        for (Teacher teacher : teacherRepository.getAll()) {
            if (teacher == null) continue;
            System.out.println("{Teacher \"" + teacher.getName() + "\" ID = " + teacher.getID() + '}');
        }
        System.out.println();
    }
}
