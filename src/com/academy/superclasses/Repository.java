package com.academy.superclasses;

import com.academy.models.Lecture;
import com.academy.repository.LectureRepository;

public class Repository {
    private static int capacity = 3;
    private static Models[] array = new Models[capacity];

    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Models[] getAll() {
        return array;
    }

    public void addLecture (int ID){
        LectureRepository lectureRepository = new LectureRepository();

        for (int i = 0; i < array.length; i++) {
            if (array[i] == null){
                for (Lecture lecture : lectureRepository.getAll()){
                    if (lecture.getID() == ID) array[i] = lecture;
                }if (array[i] == null) System.out.println("There's no lecture with ID = " + ID);
                break;
            }else if (i == capacity-1){
                expandArray();
            }
        }
    }
    private void expandArray(){
        int newCapacity = capacity;
        capacity = capacity*3/2 + 1;
        Models[] tmpArray = new Models[capacity];
        System.arraycopy(array, 0, tmpArray, 0, newCapacity);
        array = tmpArray;
    }
    public Models getById (int ID){
        for (Models lecture : getAll()){
            if (lecture == null) continue;
            if (lecture.getID() == ID) return lecture;
        }
        return null;
    }

    public void deleteById(int ID){
        for (int i = 0; i < array.length; i++){
        if (array[i].getID() == ID) array[i] = null;
    }
    }
}
