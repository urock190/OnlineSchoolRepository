package com.academy.repository;

import com.academy.models.Lecture;
import com.academy.models.lectures.AdditionalMaterial;
import com.academy.models.lectures.Homework;
import com.academy.services.LectureService;

public class MainTest {

    public static void main(String[] args) {
        LectureRepository lectureRepository = new LectureRepository();
        LectureService lectureService =new LectureService();
        System.out.println(lectureRepository.size());
        System.out.println(lectureRepository.isEmpty());
        System.out.println(lectureRepository.get(2));
        Lecture lecture = new Lecture();
        Lecture lecture1 = new Lecture();
        Lecture lecture2 = new Lecture("sf", 3, new Homework(), new AdditionalMaterial());
        Lecture lecture3 = new Lecture("second", 3, new Homework(), new AdditionalMaterial());
        lectureRepository.addLecture(lecture);
        lectureRepository.addLecture(lecture1);
        lectureRepository.addLecture(lecture2);
        lectureRepository.addLecture(lecture3);
        System.out.println(lectureRepository.get(0));
        System.out.println(lectureRepository.get(1));
        System.out.println(lectureRepository.get(2));
        System.out.println(lectureRepository.get(3));
        System.out.println(lectureRepository.size());
        System.out.println(lectureRepository.get(2).getClass());
        System.out.println(lectureRepository.getAll().getClass());
        lectureRepository.addLecture(4, new Lecture("sffd", 4, new Homework(), new AdditionalMaterial()));
        lectureRepository.addLecture(5, new Lecture("Super Lecture", 7, new Homework(), new AdditionalMaterial()));
        System.out.println(lectureRepository.size());
        System.out.println(lectureRepository.get(4));
        System.out.println(lectureRepository.get(5));
        lectureRepository.remove(4);
        System.out.println(lectureRepository.getById(3));
        lectureRepository.deleteById(3);
        System.out.println(lectureRepository.isEmpty());
        lectureService.printID();
    }
}
