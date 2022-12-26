package com.academy.repository.lectures;

import com.academy.exceptions.EntityNotFoundException;
import com.academy.models.Models;
import com.academy.models.lectures.Homework;
import com.academy.repository.Repository;
import com.academy.services.RepositoryService;
import com.academy.services.SimpleIterator;

public class HomeworkRepository implements Repository {
    private static int capacity = 10;
    private static Homework [] homeworks = new Homework[capacity];
    private static RepositoryService <Homework> homeworkRepositoryService = new RepositoryService<>(homeworks);

    @Override
    public int size(){
        return homeworkRepositoryService.size();
    }
    @Override
    public boolean isEmpty(){
        return homeworkRepositoryService.isEmpty();
    }

    @Override
    public void add(Models model) {
        if (getAll()[capacity-1] != null) expandArray();
        homeworkRepositoryService.add((Homework) model);
    }

    @Override
    public void add(int index, Models model) {
        if (getAll()[capacity-1] != null) expandArray();
        homeworkRepositoryService.add(index, (Homework) model);
    }

    @Override
    public Homework get (int index){
        return homeworkRepositoryService.get(index);
    }

    @Override
    public void remove (int index) {
        homeworkRepositoryService.remove(index);
    }

    @Override
    public void findAll() {
        System.out.println("======================\nFull homeworks info:");
        SimpleIterator<Homework> iterator = iterator();
        int i = 0;
        while (iterator.hasNext()) {
                Homework homework = iterator.next();
                if (homework == null) {i++; continue;}
                System.out.println(homework);
        }
        if (i == size()) System.out.println("Array is empty.");
    }

    @Override
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
    public Homework getById (int ID) throws EntityNotFoundException {
        for (Homework homework : getAll()){
            if (homework == null) continue;
            if (homework.getID() == ID) return homework;
        }
        throw new EntityNotFoundException("There's no homework with such ID");
    }
    @Override
    public void deleteById(int ID){
        for (int i = 0; i < size(); i++){
            if (getAll()[i] == null) continue;
            if (getAll()[i].getID() == ID) getAll()[i] = null;
        }
    }

    @Override
    public SimpleIterator<Homework> iterator() {
        return new SimpleIterator<>(getAll());
    }
}
