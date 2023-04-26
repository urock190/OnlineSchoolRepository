package dao;

import jakarta.persistence.Tuple;
import models.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StudentRepositoryDAO {

    public StudentRepositoryDAO() {}

    public List<Student> getAll(){
        List<Student> listFromDB;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Student> query = session.createQuery("from Student", Student.class).setReadOnly(true);
            listFromDB = query.list();
        }
        return listFromDB;
    }

    public List<Student> getOrderedByLastName(){
        String hql = "FROM Student order by lastName";
        List<Student> studentList;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Student> query = session.createQuery(hql, Student.class).setReadOnly(true);
            studentList = query.list();
        }
        return studentList;
    }

    public void insert(Student student){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(student);
            transaction.commit();
        }
    }

    public void deleteByID(int id){
        String hql = "DELETE FROM Student WHERE ID = :ID";

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Student> query = session.createQuery(hql, Student.class);
            query.setParameter("ID", id);
            query.executeUpdate();
        }
    }

    public Student getByID(int ID){
        Student student;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            student = session.get(Student.class, ID);
        }
        return student;
    }

    public Map<Student, Long> getGroupedByCoursesNumberAndSortedByLastName(){
        String hql = """
        SELECT last_name, name, (SELECT COUNT(*)
        FROM school_schema.student_course_relation
        WHERE student_id = students.student_id) AS courses_number
        FROM school_schema.students
        GROUP BY last_name, name, courses_number
        HAVING courses_number > 0
        ORDER BY last_name;""";
        Map<Student, Long> map = new LinkedHashMap<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Tuple> list = session.createNativeQuery(hql, Tuple.class).getResultList();
            for (Tuple t: list){
                Student student = new Student(); student.setLastName(t.get(0, String.class));
                student.setName(t.get(1, String.class));
                map.put(student, t.get(2, Long.class));
            }
        }
        return map;
    }
}
