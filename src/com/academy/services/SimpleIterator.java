package com.academy.services;

import com.academy.models.Models;

import java.util.NoSuchElementException;

public class SimpleIterator <E extends Models> {
    private int cursor;
    private E[] elements;
    private int lastRet = -1;

    public SimpleIterator (E[] elements) {
        this.elements = elements;
    }

    public boolean hasNext(){
        return cursor < elements.length;
    }

    public E next(){
        int i = cursor;
        if (i >= elements.length) {
            throw new NoSuchElementException();
        }
        cursor = i + 1;
        return elements[lastRet = i];
    }

    public void remove(){
        if (lastRet < 0) throw new IllegalStateException();
        elements[lastRet] = null;
        cursor = lastRet;
        lastRet = -1;
    }
}
