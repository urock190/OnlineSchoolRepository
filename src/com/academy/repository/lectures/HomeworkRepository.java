package com.academy.repository.lectures;

import com.academy.Main;
import com.academy.exceptions.EntityNotFoundException;
import com.academy.models.Lecture;
import com.academy.models.lectures.Homework;
import com.academy.repository.LectureRepository;
import com.academy.util.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeworkRepository {
    private static final Logger LOGGER = new Logger(HomeworkRepository.class.getName());
    private static HomeworkRepository instance;
    private Map <Integer, List<Homework>> homeworks;

    private HomeworkRepository() {
        homeworks = new HashMap<>();
    }

    public static HomeworkRepository getInstance(){
        if (instance == null) instance = new HomeworkRepository();
        return instance;
    }

    public int size(){
        return homeworks.size();
    }
    public boolean isEmpty(){
        return homeworks.isEmpty();
    }
    public void put (int lectureID, List<Homework> homeworkList) {
        homeworks.put(lectureID, homeworkList);
    }
    public void putIfAbsent (int lectureID, List<Homework> homeworkList) {
        homeworks.putIfAbsent(lectureID, homeworkList);
    }
    public void remove (int lectureID) {
        homeworks.remove(lectureID);
    }
    public Map<Integer, List<Homework>> getAll() {
        return homeworks;
    }
    public List<Homework> get (int lectureID){
        return homeworks.get(lectureID);
    }

    public void deleteById(int lectureID, int ID){
        get(lectureID);
        boolean success = false;
        for (int i = 0; i < get(lectureID).size(); i++){
            if (get(lectureID).get(i) == null) continue;
            if (get(lectureID).get(i).getID() == ID) {get(lectureID).remove(i); success = true;}
        }
        if (success) System.out.println("Homework ID = " + ID + " has been successfully removed from lecture with ID = " + lectureID + '.');
        else System.out.println("Homework not found.");
    }

    public Homework getById (int ID) throws EntityNotFoundException {
        for (List<Homework> list : homeworks.values()) {
            if (list == null) continue;
            for (Homework homework : list){
                if (homework == null) continue;
                if (homework.getID() == ID) return homework;
            }
        }
        throw new EntityNotFoundException("There's no homework with such ID");
    }

    public List<Homework> getByLectureId (int lectureID) throws EntityNotFoundException {
        List<Homework> homeworksOfThisLecture = new ArrayList<>();
        for (List<Homework> list : homeworks.values()) {
            if (list == null) continue;
            for (Homework homework : list){
                if (homework == null) continue;
                if (homework.getLectureID() == lectureID) homeworksOfThisLecture.add(homework);
            }
        }
        if(homeworksOfThisLecture.isEmpty()) throw new EntityNotFoundException("There's no homework with such lecture ID");
        else return homeworksOfThisLecture;
    }

    public List<Homework> getByCourseId (int courseID) throws EntityNotFoundException {
        List<Homework> homeworksOfThisCourse = new ArrayList<>();
        for (Lecture lecture : LectureRepository.getInstance().getByCourseId(courseID)){
            if (lecture == null) continue;
            try{List<Homework> homeworksOfThisLecture = getByLectureId(lecture.getID());
                homeworksOfThisCourse.addAll(homeworksOfThisLecture);
            }catch (EntityNotFoundException e) {
                LOGGER.warning("There's no homeworks with such lecture ID.", e);
            }
        }
        if(homeworksOfThisCourse.isEmpty()) throw new EntityNotFoundException("There's no homeworks with such course ID");
        else return homeworksOfThisCourse;
    }

    public void addById (int lectureID, int ID) throws EntityNotFoundException {
        get(lectureID).add(getById(ID));
        System.out.println("Homework with ID = " + ID + " has been successfully added to the lecture with ID = " + lectureID + '.');
        deleteById(getById(ID).getLectureID(), ID);
        getById(ID).setLectureID(lectureID);
    }

    public void findAll() {
        System.out.println("======================\nFull homeworks info:");
        if (isEmpty()) System.out.println("Array is empty.");
        for (List<Homework> list : homeworks.values()) {
            if (list == null) continue;
            for (Homework homework : list) {
                if(homework == null) continue;
                System.out.println(homework);
            }
        }
    }
}
