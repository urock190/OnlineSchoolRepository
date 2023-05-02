package service;

import dao.LectureRepositoryDAO;
import jakarta.persistence.Tuple;
import models.Lecture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class LectureService {
    private final LectureRepositoryDAO lectureRepositoryDAO;

    @Autowired
    public LectureService(LectureRepositoryDAO lectureRepositoryDAO) {
        this.lectureRepositoryDAO = lectureRepositoryDAO;
    }

    public List<Lecture> getAll(){
        return lectureRepositoryDAO.findAll();
    }

    @Transactional
    public void insert(Lecture lecture){
        lectureRepositoryDAO.save(lecture);
    }

    public void deleteByID(int id){
        lectureRepositoryDAO.deleteById(id);
    }

    public Lecture getByID(int ID){
        return lectureRepositoryDAO.findById(ID).orElse(null);
    }

    public Map<String, Long> getUntil2024sortedByLectureDate(){
        Map<String, Long> map = new LinkedHashMap<>();

        List<Tuple> list = lectureRepositoryDAO.getUntil2024sortedByLectureDate();
        for (Tuple t: list) {
            map.put(t.get(0, String.class), t.get(1, Long.class));
        }
        return map;
    }

    //Get the lecture created earlier than all others, with the largest amount of homeworks.
    public Lecture getEarliestLectureWithMostHomeworks(){
        return lectureRepositoryDAO.getEarliestLectureWithMostHomeworks();
    }
}
