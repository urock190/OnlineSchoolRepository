package com.academy.services;

import com.academy.models.Person;
import com.academy.models.Role;
import com.academy.repository.PersonRepository;

import java.util.Scanner;

public class PersonService {
    public static void printCounter(){
        System.out.println(Person.getCounterOfPersons());
    }
    public static Person createPerson() {
        return new Person();
    }
    public static Person createPerson (Role role, String name, String secondName){
        return new Person(role, name, secondName);
    }
    public static void printCourseID(Person person){
        System.out.println("course ID of " + person.getRole() +" №"+ person.getID() + " = " + person.getCourseID());
    }
    public static Person createStudentFromConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==========================\nCreate new student. \nEnter the name of this student");
        String name = scanner.next();
        System.out.println("Enter student's second name");
        String secondName = scanner.next();
        return new Person (Role.STUDENT, name, secondName);
    }
    public static Person createTeacherFromConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==========================\nCreate new teacher. \nEnter the name of this teacher");
        String name = scanner.next();
        System.out.println("Enter teacher's second name");
        String secondName = scanner.next();
        return new Person (Role.TEACHER, name, secondName);
    }
    PersonRepository personRepository = new PersonRepository();
   public void printAllID(){
        System.out.println("======================\nShort persons info:");
        for (Person person : personRepository.getAll()) {
            if (person == null) continue;
            System.out.println("{" + person.getRole()+" \""+ person.getName()+" "+ person.getSecondName()+"\" ID = " +
                    person.getID() + '}');
        }
        System.out.println();
    }
    public void printStudentsID(){
        System.out.println("======================\nShort students info:");
        for (Person student : personRepository.getAll()) {
            if (student == null || student.getRole() != Role.STUDENT) continue;
            System.out.println("{" + student.getRole()+" \""+ student.getName()+" "+ student.getSecondName()+"\" ID = " +
                    student.getID() + '}');
        }
        System.out.println();
    }
    public void printTeachersID(){
        System.out.println("======================\nShort teachers info:");
        for (Person teacher : personRepository.getAll()) {
            if (teacher == null || teacher.getRole() != Role.TEACHER) continue;
            System.out.println("{" + teacher.getRole()+" \""+ teacher.getName()+" "+ teacher.getSecondName()+"\" ID = " +
                    teacher.getID() + '}');
        }
        System.out.println();
    }
}
