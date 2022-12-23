package com.academy.repository;

import com.academy.exceptions.EntityNotFoundException;
import com.academy.models.Models;

public interface Repository{
    Models[] getAll();

    int size();

    boolean isEmpty();

    Models get (int index);

    void add (Models model);

    void add(int index, Models model);

    void remove(int index);

    default Models getById(int ID) throws EntityNotFoundException {
        for (Models model : getAll()){
            if (model == null) continue;
            if (model.getID() == ID) return model;
        }
        throw new EntityNotFoundException();
    }

    default void deleteById(int ID){
        for (int i = 0; i < getAll().length; i++){
            if (getAll()[i] == null) continue;
            if (getAll()[i].getID() == ID) getAll()[i] = null;
        }
    }
}
