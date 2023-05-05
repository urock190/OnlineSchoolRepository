package webapi.service;

import webapi.dao.StudentRepositoryDAO;
import jakarta.persistence.Tuple;
import webapi.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {
    private final StudentRepositoryDAO studentRepositoryDAO;

    @Autowired
    public StudentService(StudentRepositoryDAO studentRepositoryDAO) {
        this.studentRepositoryDAO = studentRepositoryDAO;
    }

    public List<Student> getAll(){
        return studentRepositoryDAO.findAll();
    }

    @Transactional
    public void insert(Student student){
        studentRepositoryDAO.save(student);
    }

    public void deleteByID(int id){
        studentRepositoryDAO.deleteById(id);
    }

    public Student getByID(int ID){
        return studentRepositoryDAO.findById(ID).orElse(null);
    }

    public List<Student> getOrderedByLastName(){
        Sort sort = Sort.by("lastName").ascending();
        return studentRepositoryDAO.findAll(sort);
    }

    public Map<Student, Long> getGroupedByCoursesNumberAndSortedByLastName(){
        Map<Student, Long> map = new LinkedHashMap<>();

        List<Tuple> list = studentRepositoryDAO.getGroupedByCoursesNumberAndSortedByLastName();
            for (Tuple t: list){
                Student student = new Student(); student.setLastName(t.get(0, String.class));
                student.setName(t.get(1, String.class));
                map.put(student, t.get(2, Long.class));
            }
        return map;
    }
}
