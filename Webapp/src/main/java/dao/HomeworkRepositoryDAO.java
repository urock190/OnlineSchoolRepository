package dao;

import models.Homework;
import util.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeworkRepositoryDAO {
    private static HomeworkRepositoryDAO instance;

    private HomeworkRepositoryDAO(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load class.");
            e.printStackTrace();
        }
    }

    public static HomeworkRepositoryDAO getInstance(){
        if (instance == null) instance = new HomeworkRepositoryDAO();
        return instance;
    }

    public Map<Integer, List<Homework>> getAll(){
        String procedure = "{call getDataFromTable(?)}";
        Map<Integer, List<Homework>> mapFromDB = new HashMap<>();

        try (Connection connection = DBConnection.getConnection();
             CallableStatement statement = connection.prepareCall(procedure)) {

            statement.setString(1, "homeworks");

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Homework homework = createFromResultSet(resultSet);

                mapFromDB.putIfAbsent(homework.getLectureID(), new ArrayList<>());
                mapFromDB.get(homework.getLectureID()).add(homework);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mapFromDB;
    }

    private Homework createFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("homework_id");
        String name = resultSet.getString("name");
        String task = resultSet.getString("task");
        int numberOfTasks = resultSet.getInt("number_of_tasks");
        LocalDateTime deadline = resultSet.getTimestamp("deadline").toLocalDateTime();
        int lectureID = resultSet.getInt("lecture_id");

        return new Homework(id, name, lectureID, task, numberOfTasks, deadline);
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
        String query = "INSERT INTO school_schema.homeworks (homework_id, name, task, number_of_tasks, deadline, lecture_id) " +
                "VALUES (?, ?, ?, ?, ?, ?);";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(query)){

            prepStatement.setInt(1, homework.getID());
            prepStatement.setString(2, homework.getName());
            prepStatement.setString(3, homework.getTask());
            prepStatement.setInt(4, homework.getNumberOfTasks());
            prepStatement.setTimestamp(5, Timestamp.valueOf(homework.getDeadline()));
            prepStatement.setInt(6, homework.getLectureID());

            prepStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByID(int id){
        String query = "DELETE FROM school_schema.homeworks WHERE (homework_id = ?);";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(query)){

            prepStatement.setInt(1, id);
            prepStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByLectureID(int lectureID){
        String query = "DELETE FROM school_schema.homeworks WHERE (lecture_id = ?);";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(query)){

            prepStatement.setInt(1, lectureID);
            prepStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Homework getByID(int ID){
        String query = "SELECT * FROM school_schema.homeworks WHERE (homework_id = ?);";
        Homework homework = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(query)){

            prepStatement.setInt(1, ID);
            ResultSet resultSet = prepStatement.executeQuery();
            while(resultSet.next()){
                homework = createFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return homework;
    }

    public List<Homework> getAllAsList(){
        String procedure = "{call getDataFromTable(?)}";
        List<Homework> listFromDB = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             CallableStatement statement = connection.prepareCall(procedure)) {

            statement.setString(1, "homeworks");

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Homework homework = createFromResultSet(resultSet);

                listFromDB.add(homework);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listFromDB;
    }
}
