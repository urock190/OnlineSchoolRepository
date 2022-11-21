package com.academy.repository;

import com.academy.models.Lecture;
import com.academy.superclasses.Repository;

public class LectureRepository extends Repository {
    private static int capacity = 3;
    private static Lecture[] lectures = new Lecture[capacity];
    @Override
    public int getCapacity() {
        return capacity;
    }
    @Override
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public Lecture[] getAll() {
        return lectures;
    }
    public void addLecture (Lecture lecture){
        for (int i = 0; i < lectures.length; i++) {
            if (lectures[i] == null){
                lectures[i] = lecture;
                break;
            }else if (i == capacity-1){
                expandArray();
            }
        }
    }
    private void expandArray(){
        int newCapacity = capacity;
        capacity = capacity*3/2 + 1;
        Lecture[] tmpArray = new Lecture[capacity];
        System.arraycopy(lectures, 0, tmpArray, 0, newCapacity);
        lectures = tmpArray;
    }

    @Override
    public Lecture getById (int ID){
        for (Lecture lecture : getAll()){
            if (lecture == null) continue;
            if (lecture.getID() == ID) return lecture;
        }
        return null;
    }

    @Override
    public void deleteById(int ID) {
        for (int i = 0; i < lectures.length; i++){
            if (lectures[i].getID() == ID) lectures[i] = null;
        }
    }
}
