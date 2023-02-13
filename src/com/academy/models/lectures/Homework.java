package com.academy.models.lectures;

import com.academy.exceptions.EntityNotFoundException;
import com.academy.models.Models;
import com.academy.myDateTimeFormats.DateTimeFormats;
import com.academy.repository.LectureRepository;
import com.academy.util.Logger;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.academy.models.Lecture.getCounterOfLectures;

public class Homework extends Models implements Serializable {
    private final static Logger LOGGER = new Logger(Homework.class.getName());
    @Serial
    private static final long serialVersionUID = 123454867704L;
    private int lectureID;
    private String task;
    private int numberOfTasks;
    private static int counterOfHomework;
    private String deadline;

    @Override
    public String toString() {
        return "Homework (" +
                "name = '" + getName() + '\'' +
                ", numberOfTasks = " + numberOfTasks +
                ", task = \"" + task + "\", ID = " + getID() +
                ", lectureID = " + lectureID + ", deadline = " + deadline +
                ')';
    }
    public Homework(String name, int numberOfTasks, String task) {
        this.setName(name);
        this.numberOfTasks = numberOfTasks;
        this.task = task;
        setID(++counterOfHomework);
        lectureID = getCounterOfLectures();
        try {
            LocalDateTime lectureDate = LectureRepository.getInstance().getById(lectureID).getLectureDate();
            LocalDate date = lectureDate.toLocalDate().plusDays(1);
            this.deadline = DateTimeFormats.hwDeadlineFormat(LocalDateTime.of(date, LocalTime.NOON));
        } catch (EntityNotFoundException e) {
            LOGGER.warning("There's no lecture with such ID, so deadline will be null.", e);
        }
    }
    public Homework(){
        setID(++counterOfHomework);
        lectureID = getCounterOfLectures();
        try {
            LocalDateTime lectureDate = LectureRepository.getInstance().getById(lectureID).getLectureDate();
            LocalDate date = lectureDate.toLocalDate().plusDays(1);
            this.deadline = DateTimeFormats.hwDeadlineFormat(LocalDateTime.of(date, LocalTime.NOON));
        } catch (EntityNotFoundException e) {
            LOGGER.warning("There's no lecture with such ID, so deadline will be null.", e);
        }
    }

    public void setNumberOfTasks(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
    }

    public static void setCounterOfHomework(int counterOfHomework) {
        Homework.counterOfHomework = counterOfHomework;
    }

    public int getNumberOfTasks() {
        return numberOfTasks;
    }
    public static int getCounterOfHomework() {
        return counterOfHomework;
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
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = DateTimeFormats.hwDeadlineFormat(deadline);
    }
}