package dao;

import jakarta.persistence.Tuple;
import models.AdditionalMaterial;
import models.ResourceType;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.*;

public class AddMatRepositoryDAO {

    public AddMatRepositoryDAO() {}

        public Map<Integer, List<AdditionalMaterial>> getAll(){
            Map<Integer, List<AdditionalMaterial>> mapFromDB = new HashMap<>();

            try (Session session = HibernateUtil.getSessionFactory().openSession()) {

                Query<AdditionalMaterial> query = session.createQuery("from AdditionalMaterial",
                                AdditionalMaterial.class).setReadOnly(true);
                ScrollableResults<AdditionalMaterial> results = query.scroll();
                while (results.next()){
                    AdditionalMaterial newAddMat = results.get();
                    int lectureID = newAddMat.getLectureID();
                    mapFromDB.putIfAbsent(lectureID, new ArrayList<>());
                    mapFromDB.get(lectureID).add(newAddMat);
                }
                results.close();
            }
            return mapFromDB;
        }

        public void put(int lectureID, List<AdditionalMaterial> materialsList){
            deleteByLectureID(lectureID);
            if(!materialsList.isEmpty()) {
                for (AdditionalMaterial am : materialsList) {
                    insert(am);
                }
            }
        }

        public void insert(AdditionalMaterial material){
            try (Session session = HibernateUtil.getSessionFactory().openSession()){
                Transaction transaction = session.beginTransaction();
                session.persist(material);
                transaction.commit();
            }
        }

        public void deleteByID(int id){
            String hql = "DELETE FROM AdditionalMaterial WHERE ID = :ID";

            try (Session session = HibernateUtil.getSessionFactory().openSession()){
                Query<AdditionalMaterial> query = session.createQuery(hql, AdditionalMaterial.class);
                query.setParameter("ID", id);
                query.executeUpdate();
            }
        }

        public void deleteByLectureID(int lectureID){
            String hql = "DELETE FROM AdditionalMaterial am WHERE am.lectureID = :lectureID";

            try (Session session = HibernateUtil.getSessionFactory().openSession()){
                Query<AdditionalMaterial> query = session.createQuery(hql, AdditionalMaterial.class);
                query.setParameter("lectureID", lectureID);
                query.executeUpdate();
            }
        }

        public AdditionalMaterial getByID(int ID){
            AdditionalMaterial newAddMat;

            try (Session session = HibernateUtil.getSessionFactory().openSession()){
                newAddMat = session.get(AdditionalMaterial.class, ID);
            }
            return newAddMat;
        }

    public List<AdditionalMaterial> getAllAsList(){
        List<AdditionalMaterial> listFromDB;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<AdditionalMaterial> query = session.createQuery("from AdditionalMaterial", AdditionalMaterial.class).
                    setReadOnly(true);
            listFromDB = query.list();
        }
        return listFromDB;
    }

    public Map<ResourceType, Long> numberOfAdMatsByResourceType(){
        String hql = "SELECT resourceType, COUNT(resourceType) AS quantity FROM AdditionalMaterial " +
                "GROUP BY resourceType";
        Map<ResourceType, Long> map = new EnumMap<>(ResourceType.class);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Tuple> list = session.createQuery(hql, Tuple.class).getResultList();
            for (Tuple t: list) {
                    map.put(t.get(0, ResourceType.class), t.get(1, Long.class));
            }
        }
        return map;
    }
}