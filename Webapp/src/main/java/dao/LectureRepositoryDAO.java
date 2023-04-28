package dao;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.Tuple;
import models.Lecture;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LectureRepositoryDAO {

    public LectureRepositoryDAO() {}

    public List<Lecture> getAll(){
        List<Lecture> listFromDB;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Lecture> query = session.createQuery("from Lecture", Lecture.class).
                    setHint("org.hibernate.cacheable", true).setReadOnly(true);

            EntityGraph<Lecture> entityGraph = session.createEntityGraph(Lecture.class);
            entityGraph.addAttributeNodes("materials", "homeworks");
            query.setHint("jakarta.persistence.fetchgraph", entityGraph);
            listFromDB = query.list();
        }
        return listFromDB;
    }

    public void insert(Lecture lecture){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(lecture);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null){
                transaction.rollback();
            }
        }
    }

    public void deleteByID(int id){
        String hql = "DELETE FROM Lecture WHERE ID = :ID";

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Lecture> query = session.createQuery(hql, Lecture.class);
            query.setParameter("ID", id);
            query.executeUpdate();
        }
    }

    public Lecture getByID(int ID){
        Lecture lecture;

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            lecture = session.get(Lecture.class, ID);
        }
        return lecture;
    }

    public Map<String, Long> getUntil2024sortedByLectureDate(){
        String hql = """
        SELECT name, (select count(*) from AdditionalMaterial am
        where am.lectureID = l.ID) as materials_number
        FROM Lecture l
        where date(lectureDate) < {d '2024-01-01'}
        order by lectureDate""";
        Map<String, Long> map = new LinkedHashMap<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Tuple> list = session.createQuery(hql, Tuple.class).getResultList();
            for (Tuple t: list) {
                map.put(t.get(0, String.class), t.get(1, Long.class));
            }
        }
        return map;
    }

    //Get the lecture created earlier than all others, with the largest amount of homeworks.
    public Lecture getEarliestLectureWithMostHomeworks(){
        String hql = """
        FROM Lecture l
        where cast(creationDate as biginteger) = (select min(cast(creationDate as biginteger)) from Lecture)
        order by (select count(*) from Homework h where h.lectureID = l.ID) desc
        limit 1""";
        Lecture lecture;

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Lecture> query = session.createQuery(hql, Lecture.class);
            lecture = query.getSingleResult();
        }
        return lecture;
    }
}
