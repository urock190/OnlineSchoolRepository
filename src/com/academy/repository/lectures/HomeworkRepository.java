package com.academy.repository.lectures;

import com.academy.models.lectures.Homework;
import com.academy.repository.Repository;
import com.academy.services.RepositoryService;

public class HomeworkRepository extends Repository {
    private static int capacity = 10;
    private static Homework [] homeworks = new Homework[capacity];
    private static RepositoryService <Homework> homeworkRepositoryService = new RepositoryService<>(homeworks);

    public int size(){
        return homeworkRepositoryService.size();
    }

    public boolean isEmpty(){
        return homeworkRepositoryService.isEmpty();
    }

    public Homework get (int index){
        return homeworkRepositoryService.get(index);
    }
    public void addHomework (Homework homework){
        if (getAll()[capacity-1] != null) expandArray();
        homeworkRepositoryService.add(homework);
    }

    public void addHomework (int index, Homework homework) {
        if (getAll()[capacity-1] != null) expandArray();
        homeworkRepositoryService.add(index, homework);
    }
    public void remove (int index) {
        homeworkRepositoryService.remove(index);
    }

    public Homework[] getAll() {
        return homeworkRepositoryService.getElements();
    }

    private void expandArray(){
        int newCapacity = capacity;
        capacity = capacity*3/2 + 1;
        Homework[] tmpArray = new Homework[capacity];
        System.arraycopy(getAll(), 0, tmpArray, 0, newCapacity);
        homeworkRepositoryService.setElements(tmpArray);
    }

    @Override
    public Homework getById (int ID){
        for (Homework homework : getAll()){
            if (homework == null) continue;
            if (homework.getID() == ID) return homework;
        }
        return null;
    }

    public void deleteById(int ID){
        for (int i = 0; i < size(); i++){
            if (getAll()[i] == null) continue;
            if (getAll()[i].getID() == ID) getAll()[i] = null;
        }
    }
}
