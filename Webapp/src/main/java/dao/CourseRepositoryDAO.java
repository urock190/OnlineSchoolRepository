package dao;

import models.Course;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseRepositoryDAO {

    private CourseRepositoryDAO(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load class.");
            e.printStackTrace();
        }
    }

    public List<Course> getAll(){
        String procedure = "{call getDataFromTable(?)}";
        List<Course> listFromDB = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             CallableStatement statement = connection.prepareCall(procedure)) {

            statement.setString(1, "courses");

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("course_id");
                String name = resultSet.getString("name");

                Course newAddMat = new Course(id, name);
                listFromDB.add(newAddMat);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listFromDB;
    }

    public void insert(Course course){
        String query = "INSERT INTO school_schema.courses (course_id, name) VALUES (?, ?);";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(query)){
            prepStatement.setInt(1, course.getID());
            prepStatement.setString(2, course.getName());

            prepStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByID(int id){
        String query = "DELETE FROM school_schema.courses WHERE (course_id = ?);";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(query)){

            prepStatement.setInt(1, id);
            prepStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Course getByID(int ID){
        String query = "SELECT * FROM school_schema.courses WHERE (course_id = ?);";
        Course course = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(query)){

            prepStatement.setInt(1, ID);
            ResultSet resultSet = prepStatement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("course_id");
                String name = resultSet.getString("name");

                course = new Course(id, name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }
}
