package dao;

import models.Course;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class CourseRepositoryDAO {

    public CourseRepositoryDAO() {}

    public List<Course> getAll(){
        List<Course> listFromDB;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Course> query = session.createQuery("from Course", Course.class).setReadOnly(true);
            listFromDB = query.list();
        }
        return listFromDB;
    }

    public void insert(Course course){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(course);
            transaction.commit();
        }
    }

    public void deleteByID(int id){
        String hql = "DELETE FROM Course WHERE ID = :ID";

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Course> query = session.createQuery(hql, Course.class);
            query.setParameter("ID", id);
            query.executeUpdate();
        }
    }

    public Course getByID(int ID){
        Course course;

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            course = session.get(Course.class, ID);
        }
        return course;
    }
}
