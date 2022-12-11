package com.academy.repository;

import com.academy.models.Lecture;
import com.academy.models.Models;
import com.academy.services.RepositoryService;

public class Repository {
    private static int capacity = 3;
    private static Models[] array = new Models[capacity];
private static RepositoryService <Models> modelsRepositoryService = new RepositoryService<>(array);

    public Models[] getAll() {
        return modelsRepositoryService.getElements();
    }
    public int size(){
        return modelsRepositoryService.size();
    }

    public boolean isEmpty(){
        return modelsRepositoryService.isEmpty();
    }

    public Models get (int index){
        return modelsRepositoryService.get(index);
    }
    public void add (Models model){
        if (getAll()[capacity-1] != null) expandArray();
        modelsRepositoryService.add(model);
    }

    public void add(int index, Models model) {
        if (getAll()[capacity-1] != null) expandArray();
        modelsRepositoryService.add(index, model);
    }
    public void remove (int index) {
        modelsRepositoryService.remove(index);
    }

    public void addLecture (int ID){
        LectureRepository lectureRepository = new LectureRepository();

        for (int i = 0; i < getAll().length; i++) {
            if (getAll()[i] == null){
                for (Lecture lecture : lectureRepository.getAll()){
                    if (lecture.getID() == ID) getAll()[i] = lecture;
                }if (getAll()[i] == null) System.out.println("There's no lecture with ID = " + ID);
                break;
            }else if (i == capacity-1){
                expandArray();
            }
        }
    }
    private void expandArray(){
        int newCapacity = capacity;
        capacity = capacity*3/2 + 1;
        Models[] tmpArray = new Models[capacity];
        System.arraycopy(getAll(), 0, tmpArray, 0, newCapacity);
        modelsRepositoryService.setElements(tmpArray);
    }
    public Models getById (int ID){
        for (Models lecture : getAll()){
            if (lecture == null) continue;
            if (lecture.getID() == ID) return lecture;
        }
        return null;
    }

    public void deleteById(int ID){
        for (int i = 0; i < getAll().length; i++){
            if (getAll()[i] == null) continue;
            if (getAll()[i].getID() == ID) getAll()[i] = null;
        }
    }
}
