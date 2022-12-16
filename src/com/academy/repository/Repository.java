package com.academy.repository;

import com.academy.models.Models;

public interface Repository{
    Models[] getAll();

    int size();

    boolean isEmpty();

    Models get (int index);

    void add (Models model);

    void add(int index, Models model);

    void remove(int index);

    default Models getById(int ID){
        for (Models lecture : getAll()){
            if (lecture == null) continue;
            if (lecture.getID() == ID) return lecture;
        }
        return null;
    }

    default void deleteById(int ID){
        for (int i = 0; i < getAll().length; i++){
            if (getAll()[i] == null) continue;
            if (getAll()[i].getID() == ID) getAll()[i] = null;
        }
    }
}
