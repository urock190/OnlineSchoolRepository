package com.academy.repository;

import com.academy.models.Lecture;
import com.academy.services.RepositoryService;

public class LectureRepository extends Repository {
    private static int capacity = 3;
    private static Lecture[] lectures = new Lecture[capacity];
    private static RepositoryService<Lecture> lectureRepositoryService = new RepositoryService<>(lectures);

    public int size(){
        return lectureRepositoryService.size();
    }

    public boolean isEmpty(){
        return lectureRepositoryService.isEmpty();
    }

    public Lecture get (int index){
        return lectureRepositoryService.get(index);
    }
    public void addLecture (Lecture lecture){
        if (getAll()[capacity-1] != null) expandArray();
        lectureRepositoryService.add(lecture);
    }

    public void addLecture (int index, Lecture lecture) {
        if (getAll()[capacity-1] != null) expandArray();
        lectureRepositoryService.add(index, lecture);
    }
    public void remove (int index) {
        lectureRepositoryService.remove(index);
    }

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
    public Lecture getById (int ID){
        for (Lecture lecture : getAll()){
            if (lecture == null) continue;
            if (lecture.getID() == ID) return lecture;
        }
        return null;
    }

    @Override
    public void deleteById(int ID) {
        for (int i = 0; i < size(); i++){
            if (getAll()[i] == null) continue;
            if (getAll()[i].getID() == ID) getAll()[i] = null;
        }
    }
}
