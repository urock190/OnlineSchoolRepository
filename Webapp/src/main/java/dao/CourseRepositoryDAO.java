package dao;

import jakarta.persistence.EntityGraph;
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
            Query<Course> query = session.createQuery("from Course", Course.class);

            EntityGraph<Course> entityGraph = session.createEntityGraph(Course.class);
            entityGraph.addAttributeNodes("lectures", "teachers");
            query.setHint("jakarta.persistence.fetchgraph", entityGraph);
            listFromDB = query.list();
        }
        return listFromDB;
    }

    public void insert(Course course){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(course);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null){
                transaction.rollback();
            }
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
