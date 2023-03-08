package com.academy.repository;

import com.academy.exceptions.EntityNotFoundException;
import com.academy.models.Models;
import com.academy.services.SimpleIterator;

import java.util.List;

public interface Repository{
    List<? extends Models> getAll();

    int size();

    boolean isEmpty();

    Models get (int index);

    void add (Models model);

    void add(int index, Models model);

    void remove(int index);
    void findAll();

    default Models getById(int ID) throws EntityNotFoundException {
        for (Models model : getAll()){
            if (model == null) continue;
            if (model.getID() == ID) return model;
        }
        throw new EntityNotFoundException();
    }

    default void deleteById(int ID){
        for (int i = 0; i < size(); i++){
            if (getAll().get(i) == null) continue;
            if (getAll().get(i).getID() == ID) getAll().remove(i);
        }
    }
    SimpleIterator<? extends Models> iterator();
}
