package com.academy.repository;

import com.academy.courses.Lecture;

public class LectureRepository {
    private static int capacity = 3;
    private static Lecture[] lectures = new Lecture[capacity];

    public static void addLecture (Lecture lecture){
        for (int i = 0; i < lectures.length; i++) {
            if (lectures[i] == null){
                lectures[i] = lecture;
                break;
            }else if (i == capacity-1){
                expandArray();
            }
        }
    }
    private static void expandArray(){
        int newCapacity = capacity;
        capacity = capacity*3/2 + 1;
        Lecture[] tmpArray = new Lecture[capacity];
        System.arraycopy(lectures, 0, tmpArray, 0, newCapacity);
        lectures = tmpArray;
    }
    public static Lecture[] getArray(){
        return lectures;
    }
}
