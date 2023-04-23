package models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "homeworks", schema = "school_schema")
public class Homework {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "homework_id")
    private Integer ID;
    @Column(name = "name")
    private String name;
    @Column(name = "lecture_id")
    private int lectureID;
    @Column(name = "task")
    private String task;
    @Column(name = "number_of_tasks")
    private int numberOfTasks;
    @Column(name = "deadline")
    private LocalDateTime deadline;

    public Homework() {}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Homework that = (Homework) o;
        return Objects.equals(ID, that.ID) && Objects.equals(name, that.name) && Objects.equals(task, that.task) &&
                Objects.equals(numberOfTasks, that.numberOfTasks) && Objects.equals(deadline, that.deadline) &&
                lectureID == that.lectureID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, task, numberOfTasks, deadline, lectureID);
    }
}
