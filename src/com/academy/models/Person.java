package com.academy.models;

import static com.academy.models.Course.getCounterOfCourses;

public class Person extends Models implements Comparable<Person> {
    private String lastName;
    private String phone;
    private String email;
    private int courseID;
    private Role role;
    private int teacherID;
    private int studentID;
    private static int counterOfPersons;
    private static int counterOfStudents;
    private static int counterOfTeachers;

    public Person (Role role, String name, String lastName) {
        this.role = role;
        this.setName(name);
        this.lastName = lastName;
        setID(++counterOfPersons);
        if (this.role == Role.STUDENT) {++counterOfStudents; studentID = super.getID();}
        else {++counterOfTeachers; teacherID = super.getID();}
        courseID = getCounterOfCourses();
    }
    public Person(Role role, String firstName, String lastName, String phone, String email) {
        this.role = role;
        this.setName(firstName);
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        setID(++counterOfPersons);
        if (this.role == Role.STUDENT) {++counterOfStudents; studentID = super.getID();}
        else {++counterOfTeachers; teacherID = super.getID();}
        courseID = getCounterOfCourses();
    }

    @Override
    public String toString() {
        return role + " (" +
                "name = '" + getName() + '\'' +
                ", last name = '" + lastName + '\'' +
                ", phone = " + phone + ", email = " + email + ')';
    }

    public Person(){
        setID(++counterOfPersons);
        courseID = getCounterOfCourses();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public static int getCounterOfStudents() {
        return counterOfStudents;
    }

    public static void setCounterOfStudents(int counterOfStudents) {
        Person.counterOfStudents = counterOfStudents;
    }

    public static int getCounterOfTeachers() {
        return counterOfTeachers;
    }

    public static void setCounterOfTeachers(int counterOfTeachers) {
        Person.counterOfTeachers = counterOfTeachers;
    }

    public void setLastName(String secondName) {
        this.lastName = secondName;
    }

    public static void setCounterOfPersons(int counter) {
        Person.counterOfPersons = counter;
    }

    public String getLastName() {
        return lastName;
    }
    public static int getCounterOfPersons() {
        return counterOfPersons;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Compares two persons by lastName field lexicographically, ignoring case differences. This method
     * returns an integer whose sign is that of calling {@code compareTo} with case folded versions of the strings
     * where case differences have been eliminated by calling {@code Character.toLowerCase(Character.toUpperCase(int))}
     * on each Unicode code point. */
    @Override
    public int compareTo(Person o) {
        return  lastName.compareToIgnoreCase(o.lastName);
    }
}
