package service;

import dao.CourseRepositoryDAO;
import models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepositoryDAO courseRepositoryDAO;

    @Autowired
    public CourseService(CourseRepositoryDAO courseRepositoryDAO) {
        this.courseRepositoryDAO = courseRepositoryDAO;
    }

    public List<Course> getAll(){
        return courseRepositoryDAO.findAll();
    }

    @Transactional
    public void insert(Course course){
        courseRepositoryDAO.save(course);
    }

    public void deleteByID(int id){
        courseRepositoryDAO.deleteById(id);
    }

    public Course getByID(int ID){
        return courseRepositoryDAO.findById(ID).orElse(null);
    }
}
