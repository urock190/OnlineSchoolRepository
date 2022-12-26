package com.academy.repository;

import com.academy.exceptions.EntityNotFoundException;
import com.academy.models.Lecture;
import com.academy.models.Models;
import com.academy.services.RepositoryService;
import com.academy.services.SimpleIterator;

public class LectureRepository implements Repository {
    private static int capacity = 3;
    private static Lecture[] lectures = new Lecture[capacity];
    private static RepositoryService<Lecture> lectureRepositoryService = new RepositoryService<>(lectures);

    @Override
    public int size(){
        return lectureRepositoryService.size();
    }
    @Override
    public boolean isEmpty(){
        return lectureRepositoryService.isEmpty();
    }

    @Override
    public void add (Models model) {
        if (getAll()[capacity-1] != null) expandArray();
        lectureRepositoryService.add((Lecture) model);
    }

    @Override
    public void add(int index, Models model) {
        if (getAll()[capacity-1] != null) expandArray();
        lectureRepositoryService.add(index, (Lecture) model);
    }
    @Override
    public Lecture get (int index){
        return lectureRepositoryService.get(index);
    }

    @Override
    public void remove (int index) {
        lectureRepositoryService.remove(index);
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
    public Lecture[] getAll() {
    return lectureRepositoryService.getElements();
}

    private void expandArray(){
        int newCapacity = capacity;
        capacity = capacity*3/2 + 1;
        Lecture[] tmpArray = new Lecture[capacity];
        System.arraycopy(getAll(), 0, tmpArray, 0, newCapacity);
        lectureRepositoryService.setElements(tmpArray);
    }

    @Override
    public Lecture getById (int ID) throws EntityNotFoundException {
        for (Lecture lecture : getAll()){
            if (lecture == null) continue;
            if (lecture.getID() == ID) return lecture;
        }
        throw new EntityNotFoundException("There's no lecture with such ID");
    }

    @Override
    public void deleteById(int ID) {
        for (int i = 0; i < size(); i++){
            if (getAll()[i] == null) continue;
            if (getAll()[i].getID() == ID) getAll()[i] = null;
        }
    }
    public SimpleIterator<Lecture> iterator(){
        return new SimpleIterator<>(getAll());
    }
}
