package dao;

import models.Teacher;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepositoryDAO {
    private static TeacherRepositoryDAO instance;

    private TeacherRepositoryDAO(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load class.");
            e.printStackTrace();
        }
    }

    public static TeacherRepositoryDAO getInstance(){
        if (instance == null) instance = new TeacherRepositoryDAO();
        return instance;
    }

    public List<Teacher> getAll(){
        String procedure = "{call getDataFromTable(?)}";
        List<Teacher> listFromDB = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             CallableStatement statement = connection.prepareCall(procedure)) {

            statement.setString(1, "teachers");

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Teacher teacher = createFromResultSet(resultSet);
                listFromDB.add(teacher);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listFromDB;
    }

    private Teacher createFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("teacher_id");
        String name = resultSet.getString("name");
        String lastName = resultSet.getString("last_name");
        String phone = resultSet.getString("phone");
        String email = resultSet.getString("email");
        int courseID = resultSet.getInt("course_id");

        return new Teacher(id, name, lastName, phone, email, courseID);
    }

    public void insert(Teacher teacher){
        String query = "INSERT INTO school_schema.teachers (teacher_id, name, last_name, phone, email, course_id) " +
                "VALUES (?, ?, ?, ?, ?, ?);";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(query)){
            prepStatement.setInt(1, teacher.getID());
            prepStatement.setString(2, teacher.getName());
            prepStatement.setString(3, teacher.getLastName());
            prepStatement.setString(4, teacher.getPhone());
            prepStatement.setString(5, teacher.getEmail());
            prepStatement.setInt(6, teacher.getCourseID());

            prepStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByID(int id){
        String query = "DELETE FROM school_schema.teachers WHERE (teacher_id = ?);";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(query)){

            prepStatement.setInt(1, id);
            prepStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Teacher getByID(int ID){
        String query = "SELECT * FROM school_schema.teachers WHERE (teacher_id = ?);";
        Teacher teacher = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(query)){

            prepStatement.setInt(1, ID);
            ResultSet resultSet = prepStatement.executeQuery();
            while(resultSet.next()){
                teacher = createFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacher;
    }

    //Get teachers filtered by the first letter of their last name. All up to the letter N (or Ukrainian 'H') exclusively.
    public List<Teacher> teachersWithLastNameToTheLetterN(){
        String query = "SELECT * FROM school_schema.teachers WHERE last_name REGEXP '^[A-MА-М]';";
        List <Teacher> list = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection(); Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)){

            while(resultSet.next()){
                Teacher teacher = createFromResultSet(resultSet);
                list.add(teacher);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
