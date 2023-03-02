package com.academy.services.lectures;

import com.academy.exceptions.ValidationErrorException;
import com.academy.models.lectures.Homework;
import com.academy.repository.lectures.HomeworkRepository;
import com.academy.util.Logger;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomeworkService {
    private static final Logger LOGGER = new Logger(HomeworkService.class.getName());
    private static final Pattern TASK_PATTERN = Pattern.compile("^\\s$|.{201,}");
    private static String validationFindFalseMethod (Scanner scanner) throws ValidationErrorException {
        String newString = scanner.next() + scanner.nextLine();
        Matcher matcher = TASK_PATTERN.matcher(newString);
        if (!matcher.find()) return newString;
        else throw new ValidationErrorException();
    }
    public static void printCounter(){
        System.out.println(Homework.getCounterOfHomework());
    }
    public static Homework createHomework() {
        return new Homework();
    }

    public static Homework createHomeworkFromConsole() {
        LOGGER.debug("Creating new homework");
        Scanner scanner = new Scanner(System.in);
        boolean out = false;
        System.out.println("==========================\nCreate new homework. \nEnter the name of this homework");
        String name = scanner.next() + scanner.nextLine();
        System.out.println("Enter number of tasks");
        int numberOfTasks = 0;
        try {
            numberOfTasks = scanner.nextInt();
        } catch (InputMismatchException ex){
            LOGGER.error("Incorrect input. Need to solve the problem", ex);
        }
        System.out.println("Enter the task, please.");
        String task  = " ";
        while (!out) {
            try {
                task = validationFindFalseMethod(scanner);
                out = true;
            } catch (ValidationErrorException e) {
                LOGGER.warning("Validation error", e);
                System.out.println("The task must contain a maximum of 200 characters. Please enter a shorter description of the task.");
            }
        }
        return new Homework(name, numberOfTasks, task);
    }
    HomeworkRepository homeworkRepository = HomeworkRepository.getInstance();
    public void printID(){
        System.out.println("======================\nShort homeworks info:");
        for (List<Homework> list : homeworkRepository.getAll().values()) {
            if (list == null) continue;
            for (Homework homework : list) {
                if (homework == null) continue;
                System.out.println("{Homework \"" + homework.getName() + "\" ID = " + homework.getID() + '}');
            }
        }
        System.out.println();
    }

    public static void homeworkMenuTitle() {
        System.out.println("You have choose the category \"Homework\"");
        System.out.println("""
                                Do you want to print short info about homeworks? Type "yes" to confirm. Type "no" to choose another category.
                                Enter "1" to create new homework. Enter "2" to get homework by it's ID. Enter "3" to print full info about homeworks.
                                Type anything else to continue creating lectures.""");
    }
}
