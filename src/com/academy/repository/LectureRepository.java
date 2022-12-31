package com.academy.repository;

import com.academy.exceptions.EntityNotFoundException;
import com.academy.models.Lecture;
import com.academy.models.Models;
import com.academy.services.SimpleIterator;

import java.util.ArrayList;
import java.util.List;

public class LectureRepository implements Repository {
    private static LectureRepository instance;
    private List<Lecture> lectures;

    private LectureRepository() {
        lectures = new ArrayList<>();
    }

    public static LectureRepository getInstance(){
        if (instance == null) instance = new LectureRepository();
        return instance;
    }

    @Override
    public int size(){
        return lectures.size();
    }
    @Override
    public boolean isEmpty(){
        return lectures.isEmpty();
    }

    @Override
    public void add (Models model) {
        lectures.add((Lecture) model);
    }

    @Override
    public void add(int index, Models model) {
        lectures.add(index, (Lecture) model);
    }
    @Override
    public Lecture get (int index){
        return lectures.get(index);
    }

    @Override
    public void remove (int index) {
        lectures.remove(index);
    }

    @Override
    public void findAll() {
        System.out.println("======================\nFull lectures info:");
        SimpleIterator<Lecture> iterator = iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Lecture lecture = iterator.next();
            if (lecture == null) {i++; continue;}
            System.out.println(lecture);
        }
        if (i == size()) System.out.println("Array is empty.");
    }

    @Override
    public List<Lecture> getAll() {
    return lectures;
}

    @Override
    public Lecture getById (int ID) throws EntityNotFoundException {
        for (Lecture lecture : lectures){
            if (lecture == null) continue;
            if (lecture.getID() == ID) return lecture;
        }
        throw new EntityNotFoundException("There's no lecture with such ID");
    }

    @Override
    public void deleteById(int ID) {
        for (int i = 0; i < size(); i++){
            if (lectures.get(i) == null) continue;
            if (lectures.get(i).getID() == ID) lectures.remove(i);
        }
    }
    public SimpleIterator<Lecture> iterator(){
        return new SimpleIterator<>(lectures);
    }
}
