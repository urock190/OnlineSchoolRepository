package com.academy.services;

import com.academy.courses.Teacher;

public class TeacherService {
    public static void printCounter(){
        System.out.println(Teacher.getCounterOfTeachers());
    }
    public static Teacher createTeacher() {
        return new Teacher();
    }
}
