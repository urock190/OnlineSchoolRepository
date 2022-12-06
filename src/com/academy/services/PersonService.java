package com.academy.services;

import com.academy.models.Person;
import com.academy.models.Role;
import com.academy.repository.PersonRepository;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PersonService {
    private static Pattern pattern = Pattern.compile("^\\s$|\\d|[\\W&&[\\S]]|.{30,}");
    private static Pattern mobileNumberPattern = Pattern.compile("(^\\+(\\(?\\d{3}\\)?-?){4} {0,4}$)|(^\\s{0,4}-\\s{0,4}$)");
    private static Pattern emailPattern = Pattern.compile("(^[\\w-]+@\\w+\\.\\w+ {0,4}$)|(^\\s{0,3}-\\s{0,3}$)");
    public static void printCounter(){
        System.out.println(Person.getCounterOfPersons());
    }
    public static Person createPerson() {
        return new Person();
    }
    public static Person createPerson (Role role, String name, String lastName){
        return new Person(role, name, lastName);
    }
    public static Person createPerson (Role role, String name, String lastName, String phone, String email){
        return new Person (role, name, lastName, phone, email);
    }
    public static void printCourseID(Person person){
        System.out.println("course ID of " + person.getRole() +" №"+ person.getID() + " = " + person.getCourseID());
    }
    public static Person createStudentFromConsole() {
        Scanner scanner = new Scanner(System.in);
        String name = " ";
        boolean out = false;
        System.out.println("==========================\nCreate new student. \nEnter the name of this student");
        while (!out) {
            name = scanner.next() + scanner.nextLine();
            Matcher matcher = pattern.matcher(name);
            if (matcher.find() == false) out = true;
            else System.out.println("Student's name must contain only English letters and be shorter than 30 characters.");
        }
        System.out.println("Enter student's second name");
        String lastName = " ";
        while (out) {
            lastName = scanner.next() + scanner.nextLine();
            Matcher matcher = pattern.matcher(lastName);
            if (matcher.find() == false) out = false;
            else System.out.println("Student's last name must contain only English letters and be shorter than 30 characters.");
        }
        System.out.println("Enter student's telephone number");
        String phone = " ";
        while (!out) {
            phone = scanner.next() + scanner.nextLine();
            Matcher matcher = mobileNumberPattern.matcher(phone);
            if (matcher.find()) out = true;
            else System.out.println("The phone number must contain 12 digits and can be written in the format " +
                    "+(XXX)-(XXX)(XXX)(XXX), with or without brackets and dashes.\nTo skip this option, enter \" - \"");
        }
        System.out.println("Enter student's email");
        String email = " ";
        while (out) {
            email = scanner.next() + scanner.nextLine();
            Matcher matcher = emailPattern.matcher(email);
            if (matcher.find()) out = false;
            else System.out.println("Email entered in incorrect format. Try again, please.\nTo skip this option, enter \" - \"");
        }
        return new Person (Role.STUDENT, name, lastName, phone, email);
    }
    public static Person createTeacherFromConsole() {
        Scanner scanner = new Scanner(System.in);
        String name = " ";
        boolean out = false;
        System.out.println("==========================\nCreate new teacher. \nEnter the name of this teacher");
        while (!out) {
            name = scanner.next() + scanner.nextLine();
            Matcher matcher = pattern.matcher(name);
            if (matcher.find() == false) out = true;
            else System.out.println("Teacher's name must contain only English letters and be shorter than 30 characters.");
        }
        System.out.println("Enter teacher's second name");
        String lastName = " ";
        while (out) {
            lastName = scanner.next() + scanner.nextLine();
            Matcher matcher = pattern.matcher(lastName);
            if (matcher.find() == false) out = false;
            else System.out.println("Teacher's last name must contain only English letters and be shorter than 30 characters.");
        }
        System.out.println("Enter teacher's telephone number");
        String phone = " ";
        while (!out) {
            phone = scanner.next() + scanner.nextLine();
            Matcher matcher = mobileNumberPattern.matcher(phone);
            if (matcher.find()) out = true;
            else System.out.println("The phone number must contain 12 digits and can be written in the format " +
                    "+(XXX)-(XXX)(XXX)(XXX), with or without brackets and dashes.\nTo skip this option, enter \" - \"");
        }
        System.out.println("Enter teacher's email");
        String email = " ";
        while (out) {
            email = scanner.next() + scanner.nextLine();
            Matcher matcher = emailPattern.matcher(email);
            if (matcher.find()) out = false;
            else System.out.println("Email entered in incorrect format. Try again, please.\nTo skip this option, enter \" - \"");
        }
        return new Person (Role.TEACHER, name, lastName, phone, email);
    }
    PersonRepository personRepository = new PersonRepository();
   public void printAllID(){
        System.out.println("======================\nShort persons info:");
        for (Person person : personRepository.getAll()) {
            if (person == null) continue;
            System.out.println("{" + person.getRole()+" \""+ person.getName()+" "+ person.getLastName()+"\" ID = " +
                    person.getID() + '}');
        }
        System.out.println();
    }
    public void printStudentsID(){
        System.out.println("======================\nShort students info:");
        for (Person student : personRepository.getAll()) {
            if (student == null || student.getRole() != Role.STUDENT) continue;
            System.out.println("{" + student.getRole()+" \""+ student.getName()+" "+ student.getLastName()+"\" ID = " +
                    student.getID() + '}');
        }
        System.out.println();
    }
    public void printTeachersID(){
        System.out.println("======================\nShort teachers info:");
        for (Person teacher : personRepository.getAll()) {
            if (teacher == null || teacher.getRole() != Role.TEACHER) continue;
            System.out.println("{" + teacher.getRole()+" \""+ teacher.getName()+" "+ teacher.getLastName()+"\" ID = " +
                    teacher.getID() + '}');
        }
        System.out.println();
    }
}
