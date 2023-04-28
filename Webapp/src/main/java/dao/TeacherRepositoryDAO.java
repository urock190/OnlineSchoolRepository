package dao;

import models.Teacher;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class TeacherRepositoryDAO {

    public TeacherRepositoryDAO() {}

    public List<Teacher> getAll(){
        List<Teacher> listFromDB;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Teacher> query = session.createQuery("from Teacher t left join fetch t.lectures", Teacher.class).
                    setHint("org.hibernate.cacheable", true).setReadOnly(true);
            listFromDB = query.list();
        }
        return listFromDB;
    }

    public void insert(Teacher teacher){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(teacher);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null){
                transaction.rollback();
            }
        }
    }

    public void deleteByID(int id){
        String hql = "DELETE FROM Teacher WHERE ID = :ID";

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Teacher> query = session.createQuery(hql, Teacher.class);
            query.setParameter("ID", id);
            query.executeUpdate();
        }
    }

    public Teacher getByID(int ID){
        Teacher teacher;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            teacher = session.get(Teacher.class, ID);
        }
        return teacher;
    }

    //Get teachers filtered by the first letter of their last name. All up to the letter N (or Ukrainian 'H') exclusively.
    public List<Teacher> teachersWithLastNameToTheLetterN(){
        String sql = "SELECT * FROM teachers WHERE last_name regexp '^[A-MА-М]'";
        List <Teacher> list;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            NativeQuery<Teacher> query = session.createNativeQuery(sql, Teacher.class);
            list = query.getResultList();
        }
        return list;
    }
}
