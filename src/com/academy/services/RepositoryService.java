package com.academy.services;

import com.academy.models.Models;

public class RepositoryService <E extends Models>{
    private E[] elements;

    public RepositoryService(E[] elements) {
        this.elements = elements;
    }

    public int size(){
        return elements.length;
    }

    public boolean isEmpty(){
        for (E element : elements){
            if (element != null) return false;
        } return true;
    }

    public E get (int index){
        return elements[index];
    }

    public void add (E element){
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == null) {
                elements[i] = element;
                break;
            }
        }
    }

    public void add(int index, E element){
        elements[index] = element;
    }

    public void remove(int index){
        elements[index] = null;
    }

    public E[] getElements() {
        return elements;
    }
    public void setElements(E[] elements){
     this.elements = elements;
    }
}
