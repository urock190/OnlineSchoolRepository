package com.academy.services.lectures;

import com.academy.models.lectures.Homework;
import com.academy.repository.lectures.HomeworkRepository;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomeworkService {
    private static Pattern taskPattern = Pattern.compile("^\\s$|.{201,}");
    public static void printCounter(){
        System.out.println(Homework.getCounterOfHomework());
    }
    public static Homework createHomework() {
        return new Homework();
    }

    public static Homework createHomeworkFromConsole() {
        Scanner scanner = new Scanner(System.in);
        boolean out = false;
        System.out.println("==========================\nCreate new homework. \nEnter the name of this homework");
        String name = scanner.next() + scanner.nextLine();
        System.out.println("Enter number of tasks");
        int numberOfTasks = scanner.nextInt();
        System.out.println("Enter the task, please.");
        String task  = " ";
        while (!out) {
            task = scanner.next() + scanner.nextLine();
            Matcher matcher = taskPattern.matcher(task);
            if (matcher.find() == false) out = true;
            else System.out.println("The task must contain a maximum of 200 characters. You have entered " +
                    task.length() + " characters. Please enter a shorter description of the task.");}
        return new Homework(name, numberOfTasks, task);
    }
    HomeworkRepository homeworkRepository = new HomeworkRepository();
    public void printID(){
        System.out.println("======================\nShort homeworks info:");
        for (Homework homework : homeworkRepository.getAll()) {
            if (homework == null) continue;
            System.out.println("{Homework \"" + homework.getName() + "\" ID = " + homework.getID() + '}');
        }
        System.out.println();
    }
}
