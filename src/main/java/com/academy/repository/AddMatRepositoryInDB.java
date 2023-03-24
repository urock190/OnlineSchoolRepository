package com.academy.repository;

import com.academy.models.ResourceType;
import com.academy.models.lectures.AdditionalMaterial;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddMatRepositoryInDB {
    private static final String URL = "jdbc:mysql://localhost/school_schema";
    private static final String USER = "root";
    private static final String PASSWORD = "1234567abS";
    private static AddMatRepositoryInDB instance;

    private AddMatRepositoryInDB(){}

    public static AddMatRepositoryInDB getInstance(){
        if (instance == null) instance = new AddMatRepositoryInDB();
        return instance;
    }

        public Map<Integer, List<AdditionalMaterial>> getAll(){
            String query = "Select * from additional_materials";
            Map<Integer, List<AdditionalMaterial>> mapFromDB = new HashMap<>();

            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                 Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

                while(resultSet.next()){
                    int id = resultSet.getInt("material_id");
                    String name = resultSet.getString("name");
                    ResourceType resourceType = ResourceType.valueOf(resultSet.getString("resource_type"));
                    int lectureID = resultSet.getInt("lecture_id");

                    AdditionalMaterial newAddMat = new AdditionalMaterial(name, resourceType);
                    newAddMat.setID(id); newAddMat.setLectureID(lectureID);

                    mapFromDB.putIfAbsent(lectureID, new ArrayList<>());
                    mapFromDB.get(lectureID).add(newAddMat);
                }
            } catch (SQLException e) {
                e.printStackTrace();
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
            String query = "INSERT INTO additional_materials (material_id, name, resource_type, lecture_id) " +
                "VALUES (" + material.getID() + ", '" +  material.getName() +"', '"+ material.getResourceType() +"', "+ material.getLectureID() +");";
            updateExecution(query);
        }

        public void deleteByID(int id){
            String query = "DELETE FROM additional_materials WHERE (material_id = " + id + ");";
            updateExecution(query);
        }

        public void deleteByLectureID(int lectureID){
            String query = "DELETE FROM additional_materials WHERE (lecture_id = " + lectureID + ");";
            updateExecution(query);
        }

        private void updateExecution(String query){
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                 Statement statement = connection.createStatement()){
                statement.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}
