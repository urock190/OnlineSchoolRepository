package models;

import java.time.LocalDateTime;

public class Homework {
    private Integer ID;
    private String name;
    private int lectureID;
    private String task;
    private int numberOfTasks;
    private LocalDateTime deadline;

    public Homework(Integer ID, String name, int lectureID, String task, int numberOfTasks, LocalDateTime deadline) {
        this.ID = ID;
        this.name = name;
        this.lectureID = lectureID;
        this.task = task;
        this.numberOfTasks = numberOfTasks;
        this.deadline = deadline;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLectureID() {
        return lectureID;
    }

    public void setLectureID(int lectureID) {
        this.lectureID = lectureID;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    public void setNumberOfTasks(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
}
