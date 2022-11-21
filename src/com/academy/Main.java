package com.academy;

import com.academy.repository.LectureRepository;
import com.academy.services.LectureService;
import com.academy.services.MainService;
import com.academy.superclasses.Repository;

public class Main {
    public static void main(String[] args) {
        Repository repository = new Repository();
        LectureRepository lectureRepository = new LectureRepository();
        LectureService lectureService = new LectureService();
        MainService.init();   // Creating 1 course & 3 lectures
        //MainService.chooseCategoryAndCreateLecture();
        lectureService.printID();
        LectureService.printCounter();
        repository.addLecture(2);
        repository.addLecture(4);
        repository.addLecture(3);
        repository.addLecture(1);
        
        System.out.println(repository.getById(1));
        repository.deleteById(3);
        lectureRepository.deleteById(1);
        lectureService.printID();
        System.out.println(lectureRepository.getById(3));
    }
}