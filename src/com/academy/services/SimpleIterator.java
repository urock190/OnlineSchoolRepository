package com.academy.services;

import com.academy.models.Models;

import java.util.List;
import java.util.NoSuchElementException;

public class SimpleIterator <E extends Models> {
    private int cursor;
    private List<E> elements;
    private int lastRet = -1;

    public SimpleIterator (List<E> elements) {
        this.elements = elements;
    }

    public boolean hasNext(){
        return cursor < elements.size();
    }

    public E next(){
        int i = cursor;
        if (i >= elements.size()) {
            throw new NoSuchElementException();
        }
        cursor = i + 1;
        lastRet = i;
        return elements.get(i);
    }

    public void remove(){
        if (lastRet < 0) throw new IllegalStateException();
        elements.set(lastRet, null);
        cursor = lastRet;
        lastRet = -1;
    }
}
