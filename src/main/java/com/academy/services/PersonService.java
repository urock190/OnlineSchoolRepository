package com.academy.services;

import com.academy.exceptions.ValidationErrorException;
import com.academy.models.Person;
import com.academy.models.Role;
import com.academy.repository.PersonRepository;
import com.academy.util.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PersonService {
    private static final Logger LOGGER = new Logger(PersonService.class.getName());
    private static final Pattern NAMES_PATTERN = Pattern.compile("^\\s$|\\d|[\\W&&[\\S]]|.{30,}");
    private static final Pattern mobileNumberPattern = Pattern.compile("(^\\+(\\(?\\d{3}\\)?-?){4} {0,4}$)|(^\\s{0,4}-\\s{0,4}$)");
    private static final Pattern emailPattern = Pattern.compile("(^[\\w-]+@\\w+\\.\\w+ {0,4}$)|(^\\s{0,3}-\\s{0,3}$)");
    private static String validationFindFalseMethod (Scanner scanner) throws ValidationErrorException {
        String newString = scanner.next() + scanner.nextLine();
        Matcher matcher = NAMES_PATTERN.matcher(newString);
        if (!matcher.find()) return newString;
        else throw new ValidationErrorException();
    }
    private static String validationFindTrueMethod (Pattern pattern, Scanner scanner) throws ValidationErrorException {
        String newString = scanner.next() + scanner.nextLine();
        Matcher matcher = pattern.matcher(newString);
        if (matcher.find()) return newString;
        else throw new ValidationErrorException();
    }

    public static Person createPerson (Role role, String name, String lastName, String phone, String email){
        return new Person (role, name, lastName, phone, email);
    }

    private boolean isEmailUsed(String email){
    return personRepository.getAll().stream().anyMatch(person -> Optional.ofNullable(person.getEmail()).orElse("-").
            equals(email) && !email.equals("-"));
    }

    public Person createStudentFromConsole() {
        LOGGER.debug("Creating new student");
        Scanner scanner = new Scanner(System.in);
        String name = " ";
        boolean out = false;
        System.out.println("==========================\nCreate new student. \nEnter the name of this student");
        while (!out) {
            try {
                name = validationFindFalseMethod(scanner);
                out = true;
            } catch (ValidationErrorException e) {
                LOGGER.warning("Validation error", e);
                System.out.println("Student's name must contain only English letters and be shorter than 30 characters.");
            }
        }
        System.out.println("Enter student's second name");
        String lastName = " ";
        while (out) {
            try {
                lastName = validationFindFalseMethod(scanner);
                out = false;
            } catch (ValidationErrorException e) {
                LOGGER.warning("Validation error", e);
                System.out.println("Student's last name must contain only English letters and be shorter than 30 characters.");
            }
        }
        System.out.println("Enter student's telephone number");
        String phone = " ";
        while (!out) {
            try {
                phone = validationFindTrueMethod(mobileNumberPattern, scanner);
                out = true;
            } catch (ValidationErrorException e) {
                LOGGER.warning("Validation error", e);
                System.out.println("The phone number must contain 12 digits and can be written in the format " +
                        "+(XXX)-(XXX)(XXX)(XXX), with or without brackets and dashes.\nTo skip this option, enter \" - \"");
            }
        }
        System.out.println("Enter student's email");
        String email = " ";
        while (out) {
            try {
                email = validationFindTrueMethod(emailPattern, scanner);
                if (isEmailUsed(email)){
                    System.out.println("This email is already in use. Type \" - \" to skip email or try another email.");
                } else out = false;
            } catch (ValidationErrorException e) {
                LOGGER.warning("Validation error", e);
                System.out.println("Email entered in incorrect format. Try again, please.\nTo skip this option, enter \" - \"");
            }
        }
        return new Person (Role.STUDENT, name, lastName, phone, email);
    }
    public Person createTeacherFromConsole() {
        LOGGER.debug("Creating new teacher");
        Scanner scanner = new Scanner(System.in);
        String name = " ";
        boolean out = false;
        System.out.println("==========================\nCreate new teacher. \nEnter the name of this teacher");
        while (!out) {
            try {
                name = validationFindFalseMethod(scanner);
                out = true;
            } catch (ValidationErrorException e) {
                LOGGER.warning("Validation error", e);
                System.out.println("Teacher's name must contain only English letters and be shorter than 30 characters.");
            }
        }
        System.out.println("Enter teacher's second name");
        String lastName = " ";
        while (out) {
            try {
                lastName = validationFindFalseMethod(scanner);
                out = false;
            } catch (ValidationErrorException e) {
                LOGGER.warning("Validation error", e);
                System.out.println("Teacher's last name must contain only English letters and be shorter than 30 characters.");
            }
        }
        System.out.println("Enter teacher's telephone number");
        String phone = " ";
        while (!out) {
            try {
                phone = validationFindTrueMethod(mobileNumberPattern, scanner);
                out = true;
            } catch (ValidationErrorException e) {
                LOGGER.warning("Validation error", e);
                System.out.println("The phone number must contain 12 digits and can be written in the format " +
                        "+(XXX)-(XXX)(XXX)(XXX), with or without brackets and dashes.\nTo skip this option, enter \" - \"");
            }
        }
        System.out.println("Enter teacher's email");
        String email = " ";
        while (out) {
            try {
                email = validationFindTrueMethod(emailPattern, scanner);
                if (isEmailUsed(email)){
                    System.out.println("This email is already in use. Type \" - \" to skip email or try another email.");
                } else out = false;
            } catch (ValidationErrorException e) {
                LOGGER.warning("Validation error", e);
                System.out.println("Email entered in incorrect format. Try again, please.\nTo skip this option, enter \" - \"");
            }
        }
        return new Person (Role.TEACHER, name, lastName, phone, email);
    }
    PersonRepository personRepository = PersonRepository.getInstance();

    /** Displays persons filtered by their role and grouped by email. If role is null, returns all persons.
     * @param role  role of this person or null.
     */
    public void printGroupedByEmail(Role role){
        LOGGER.info("Persons grouped by email.");
        Predicate <Person> rolePredicate;
        if (role == null) rolePredicate = person -> person.getRole() == Role.STUDENT || person.getRole() == Role.TEACHER;
        else rolePredicate = person -> person.getRole() == role;

        personRepository.getAll().stream().filter(rolePredicate).collect(Collectors.toMap(
                person -> Optional.ofNullable(person.getEmail()).orElse("-"), person -> person.getLastName() +
                        " " + person.getName(), (str1, str2) -> str1 + ", " + str2))
                .forEach((key, value) -> System.out.println(key + ":  " + value));
    }

    public void writeStudentsEmails() {
        File file = new File("src/main/java/com/academy/services/Students Email.txt");
        TreeSet<String> treeSet = personRepository.getAll().stream().collect(Collectors.filtering(person ->
                        person.getRole() == Role.STUDENT && !Optional.ofNullable(person.getEmail()).orElse("-").equals("-"),
                Collectors.mapping(Person::getEmail, Collectors.toCollection(TreeSet<String>::new))));

        try (FileWriter writer = new FileWriter(file)){
            for (String email : treeSet) {
                writer.write(email + "\n");
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
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

    public static void studentMenuTitle() {
        System.out.println("You have choose the category \"Student\"");
        System.out.println("""
                                Do you want to print short info about students? Type "yes" to confirm. Type "no" to choose another category.
                                Enter "1" to create new student. Enter "2" to get student by their ID. Enter "3" to print full info about students.
                                Enter "4" to show the emails and students to whom these emails belong.
                                Type anything else to continue creating lectures.""");
    }

    public static void teacherMenuTitle() {
        System.out.println("You have choose the category \"Teacher\"");
        System.out.println("""
                                Do you want to print short info about teachers? Type "yes" to confirm. Type "no" to choose another category.
                                Enter "1" to create new teacher. Enter "2" to get teacher by their ID. Enter "3" to print full info about teachers.
                                Enter "4" to show the emails and teachers to whom these emails belong.
                                Type anything else to continue creating lectures.""");
    }
}
