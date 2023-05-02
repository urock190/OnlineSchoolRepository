package service;

import dao.HomeworkRepositoryDAO;
import models.Homework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HomeworkService {
    private final HomeworkRepositoryDAO homeworkRepositoryDAO;

    @Autowired
    public HomeworkService(HomeworkRepositoryDAO homeworkRepositoryDAO) {
        this.homeworkRepositoryDAO = homeworkRepositoryDAO;
    }

    @Transactional
    public void insert(Homework material) {
        homeworkRepositoryDAO.save(material);
    }

    public void deleteByID(int id) {
        homeworkRepositoryDAO.deleteById(id);
    }

    public void deleteByLectureID(int lectureID) {
        homeworkRepositoryDAO.deleteByLectureID(lectureID);
    }

    public Homework getByID(int ID){
        return homeworkRepositoryDAO.findById(ID).orElse(null);
    }

    public List<Homework> getAllAsList(){
        return homeworkRepositoryDAO.findAll();
    }
}
