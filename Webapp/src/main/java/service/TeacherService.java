package service;

import dao.TeacherRepositoryDAO;
import models.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepositoryDAO teacherRepositoryDAO;

    @Autowired
    public TeacherService(TeacherRepositoryDAO teacherRepositoryDAO) {
        this.teacherRepositoryDAO = teacherRepositoryDAO;
    }

    public List<Teacher> getAll(){
        return teacherRepositoryDAO.findAll();
    }

    @Transactional
    public void insert(Teacher teacher){
        teacherRepositoryDAO.save(teacher);
    }

    public void deleteByID(int id){
        teacherRepositoryDAO.deleteById(id);
    }

    public Teacher getByID(int ID){
        return teacherRepositoryDAO.findById(ID).orElse(null);
    }

    //Get teachers filtered by the first letter of their last name. All up to the letter N (or Ukrainian 'H') exclusively.
    public List<Teacher> teachersWithLastNameToTheLetterN() {
        return teacherRepositoryDAO.teachersWithLastNameToTheLetterN();
    }
}
