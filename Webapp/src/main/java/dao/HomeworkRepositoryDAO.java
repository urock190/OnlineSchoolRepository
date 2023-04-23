package dao;

import models.Homework;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeworkRepositoryDAO {

    public HomeworkRepositoryDAO() {}

    public Map<Integer, List<Homework>> getAll(){
        Map<Integer, List<Homework>> mapFromDB = new HashMap<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Query<Homework> query = session.createQuery("from Homework", Homework.class).setReadOnly(true);
            ScrollableResults<Homework> results = query.scroll();
            while (results.next()){
                Homework homework = results.get();
                mapFromDB.putIfAbsent(homework.getLectureID(), new ArrayList<>());
                mapFromDB.get(homework.getLectureID()).add(homework);
            }
            results.close();
        }
        return mapFromDB;
    }

    public void put(int lectureID, List<Homework> homeworksList){
        deleteByLectureID(lectureID);
        if(!homeworksList.isEmpty()) {
            for (Homework h : homeworksList) {
                insert(h);
            }
        }
    }

    public void insert(Homework homework){
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(homework);
            transaction.commit();
        }
    }

    public void deleteByID(int id){
        String hql = "DELETE FROM Homework WHERE ID = :ID";
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Homework> query = session.createQuery(hql, Homework.class);
            query.setParameter("ID", id);
            query.executeUpdate();
        }
    }

    public void deleteByLectureID(int lectureID){
        String hql = "DELETE FROM Homework WHERE lectureID = :lectureID";
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Homework> query = session.createQuery(hql, Homework.class);
            query.setParameter("lectureID", lectureID);
            query.executeUpdate();
        }
    }

    public Homework getByID(int ID){
        Homework homework;

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            homework = session.get(Homework.class, ID);
        }
        return homework;
    }

    public List<Homework> getAllAsList(){
        List<Homework> listFromDB;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Homework> query = session.createQuery("from Homework", Homework.class);
            listFromDB = query.list();
        }
        return listFromDB;
    }
}
