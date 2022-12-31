package com.academy.repository.lectures;

import com.academy.exceptions.EntityNotFoundException;
import com.academy.models.Models;
import com.academy.models.lectures.Homework;
import com.academy.repository.Repository;
import com.academy.services.SimpleIterator;

import java.util.ArrayList;
import java.util.List;

public class HomeworkRepository implements Repository {
    private static HomeworkRepository instance;
    private List<Homework> homeworks;

    private HomeworkRepository() {
        homeworks = new ArrayList<>();
    }

    public static HomeworkRepository getInstance(){
        if (instance == null) instance = new HomeworkRepository();
        return instance;
    }

    @Override
    public int size(){
        return homeworks.size();
    }
    @Override
    public boolean isEmpty(){
        return homeworks.isEmpty();
    }

    @Override
    public void add(Models model) {
        homeworks.add((Homework) model);
    }

    @Override
    public void add(int index, Models model) {
        homeworks.add(index, (Homework) model);
    }

    @Override
    public Homework get (int index){
        return homeworks.get(index);
    }

    @Override
    public void remove (int index) {
        homeworks.remove(index);
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
    public List<Homework> getAll() {
        return homeworks;
    }

    @Override
    public Homework getById (int ID) throws EntityNotFoundException {
        for (Homework homework : homeworks){
            if (homework == null) continue;
            if (homework.getID() == ID) return homework;
        }
        throw new EntityNotFoundException("There's no homework with such ID");
    }
    @Override
    public void deleteById(int ID){
        for (int i = 0; i < size(); i++){
            if (homeworks.get(i) == null) continue;
            if (homeworks.get(i).getID() == ID) homeworks.remove(i);
        }
    }

    @Override
    public SimpleIterator<Homework> iterator() {
        return new SimpleIterator<>(homeworks);
    }
}
