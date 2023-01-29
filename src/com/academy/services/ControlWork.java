package com.academy.services;

import com.academy.models.Person;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ControlWork {
    public static void startControlWork (Person[] studs) {
        Task[] tasks = new Task[10];
        for (int i = 1; i < 11; i++){
            boolean out = false;
            while (!out){
                int taskN = (int)(Math.random()*10 + 1);
                tasks[i-1] = new Task(studs[i-1], "Task " + taskN);
                for (Task task : tasks) {
                    out = true;
                    if (task == null) continue;
                    if (!task.equals(tasks[i-1]) && task.name.equals(tasks[i - 1].name)){out = false; break;}
                }
            } System.out.printf("Student (%d) %s %s gоt the %s\n", i, studs[i-1].getName(), studs[i-1].getLastName(), tasks[i-1].name);
        }

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

        for (Task task : tasks){
            executor.execute(task);
        }
        executor.shutdown();
    }

    static class Task implements Runnable{
        private final Person stud;
        private final String name;

        private Task(Person stud, String name) {
            this.stud = stud;
            this.name = name;
        }

        @Override
        public void run() {
            long time;
            try {
                time = 8000 + (long) (Math.random() * 6000);
                Thread.sleep(time);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (time <= 12000) {
                System.out.printf("Student %s %s completed %s in %.3f seconds.\n", stud.getName(), stud.getLastName(),
                        name, (float) time/1000);
            } else System.out.printf("Student %s %s didn't complete %s on time.\n", stud.getName(), stud.getLastName(), name);
        }
    }

    public static void controlWorkMenuTitle() {
        System.out.println("You have choose the category \"Control Work\"");
        System.out.println("""
                                Do you want to start control work? Type "yes" to confirm. Type "no" to choose another category.
                                Type anything else to continue creating lectures.""");
    }
}
