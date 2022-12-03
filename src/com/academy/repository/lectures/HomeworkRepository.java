package com.academy.repository.lectures;

import com.academy.models.lectures.Homework;
import com.academy.repository.Repository;

public class HomeworkRepository extends Repository {
    private static int capacity = 10;
    private static Homework [] homeworks = new Homework[capacity];

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public Homework[] getAll() {
        return homeworks;
    }

    public static void addHomework (Homework homework){
        for (int i = 0; i < homeworks.length; i++) {
            if (homeworks[i] == null){
                homeworks[i] = homework;
                break;
            }else if (i == capacity-1){
                expandArray();
            }
        }
    }
    private static void expandArray(){
        int newCapacity = capacity;
        capacity = capacity*3/2 + 1;
        Homework[] tmpArray = new Homework[capacity];
        System.arraycopy(homeworks, 0, tmpArray, 0, newCapacity);
        homeworks = tmpArray;
    }

    @Override
    public Homework getById (int ID){
        for (Homework homework : getAll()){
            if (homework == null) continue;
            if (homework.getID() == ID) return homework;
        }
        return null;
    }

    public void deleteById(int ID){
        for (int i = 0; i < homeworks.length; i++){
            if (homeworks[i].getID() == ID) homeworks[i] = null;
        }
    }
}
