package dao;

import models.AdditionalMaterial;
import models.ResourceType;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddMatRepositoryDAO {
    private static final String URL = "jdbc:mysql://localhost/school_schema";
    private static final String USER = "root";
    private static final String PASSWORD = "1234567abS";
    private static AddMatRepositoryDAO instance;

    private AddMatRepositoryDAO(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load class.");
            e.printStackTrace();
        }
    }

    public static AddMatRepositoryDAO getInstance(){
        if (instance == null) instance = new AddMatRepositoryDAO();
        return instance;
    }

        public Map<Integer, List<AdditionalMaterial>> getAll(){
            String procedure = "{call getDataFromTable(?)}";
            Map<Integer, List<AdditionalMaterial>> mapFromDB = new HashMap<>();

            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                 CallableStatement statement = connection.prepareCall(procedure)) {

                statement.setString(1, "additional_materials");

                ResultSet resultSet = statement.executeQuery();
                while(resultSet.next()){
                    int id = resultSet.getInt("material_id");
                    String name = resultSet.getString("name");
                    ResourceType resourceType = ResourceType.valueOf(resultSet.getString("resource_type"));
                    int lectureID = resultSet.getInt("lecture_id");

                    AdditionalMaterial newAddMat = new AdditionalMaterial(name, id, resourceType, lectureID);

                    mapFromDB.putIfAbsent(lectureID, new ArrayList<>());
                    mapFromDB.get(lectureID).add(newAddMat);
                }
                resultSet.close();
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
            String query = "INSERT INTO school_schema.additional_materials (material_id, name, resource_type, lecture_id) " +
                "VALUES (?, ?, ?, ?);";
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement prepStatement = connection.prepareStatement(query)){

                prepStatement.setInt(1, material.getID());
                prepStatement.setString(2, material.getName());
                prepStatement.setString(3, material.getResourceType().name());
                prepStatement.setInt(4, material.getLectureID());

                prepStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void deleteByID(int id){
            String query = "DELETE FROM school_schema.additional_materials WHERE (material_id = ?);";
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement prepStatement = connection.prepareStatement(query)){

                prepStatement.setInt(1, id);
                prepStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void deleteByLectureID(int lectureID){
            String query = "DELETE FROM school_schema.additional_materials WHERE (lecture_id = ?);";
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement prepStatement = connection.prepareStatement(query)){

                prepStatement.setInt(1, lectureID);
                prepStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public AdditionalMaterial getByID(int ID){
            String query = "SELECT * FROM school_schema.additional_materials WHERE (material_id = ?);";
            AdditionalMaterial newAddMat = null;

            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                     PreparedStatement prepStatement = connection.prepareStatement(query)){

                prepStatement.setInt(1, ID);
                ResultSet resultSet = prepStatement.executeQuery();
                while(resultSet.next()){
                    int id = resultSet.getInt("material_id");
                    String name = resultSet.getString("name");
                    ResourceType resourceType = ResourceType.valueOf(resultSet.getString("resource_type"));
                    int lectureID = resultSet.getInt("lecture_id");

                    newAddMat = new AdditionalMaterial(name, id, resourceType, lectureID);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return newAddMat;
        }

    public List<AdditionalMaterial> getAllAsList(){
        String procedure = "{call getDataFromTable(?)}";
        List<AdditionalMaterial> listFromDB = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             CallableStatement statement = connection.prepareCall(procedure)) {

            statement.setString(1, "additional_materials");

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("material_id");
                String name = resultSet.getString("name");
                ResourceType resourceType = ResourceType.valueOf(resultSet.getString("resource_type"));
                int lectureID = resultSet.getInt("lecture_id");

                AdditionalMaterial newAddMat = new AdditionalMaterial(name, id, resourceType, lectureID);

                listFromDB.add(newAddMat);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listFromDB;
    }
}
