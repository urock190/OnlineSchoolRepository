package dao;

import models.Lecture;
import util.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class LectureRepositoryDAO {
    private static LectureRepositoryDAO instance;

    private LectureRepositoryDAO(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load class.");
            e.printStackTrace();
        }
    }

    public static LectureRepositoryDAO getInstance(){
        if (instance == null) instance = new LectureRepositoryDAO();
        return instance;
    }

    public List<Lecture> getAll(){
        String procedure = "{call getDataFromTable(?)}";
        List<Lecture> listFromDB = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             CallableStatement statement = connection.prepareCall(procedure)) {

            statement.setString(1, "lectures");

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Lecture lecture = createFromResultSet(resultSet);
                listFromDB.add(lecture);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listFromDB;
    }

    private Lecture createFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("lecture_id");
        String name = resultSet.getString("name");
        int amount = resultSet.getInt("amount");
        String description = resultSet.getString("description");
        LocalDateTime creationDate = resultSet.getTimestamp("creation_date").toLocalDateTime();
        LocalDateTime lectureDate = resultSet.getTimestamp("lecture_date").toLocalDateTime();
        int teacherID = resultSet.getInt("teacher_id");
        int courseID = resultSet.getInt("course_id");

        return new Lecture(id, name, amount, description, creationDate, lectureDate,
                teacherID, courseID);
    }

    public void insert(Lecture lecture){
        String query = "INSERT INTO school_schema.lectures (lecture_id, name, amount, description, creation_date, " +
                "lecture_date, teacher_id, course_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(query)){
            prepStatement.setInt(1, lecture.getID());
            prepStatement.setString(2, lecture.getName());
            prepStatement.setInt(3, lecture.getAmount());
            prepStatement.setString(4, lecture.getDescription());
            prepStatement.setTimestamp(5, Timestamp.valueOf(lecture.getCreationDate()));
            prepStatement.setTimestamp(6, Timestamp.valueOf(lecture.getLectureDate()));
            prepStatement.setInt(7, lecture.getTeacherID());
            prepStatement.setInt(8, lecture.getCourseID());

            prepStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByID(int id){
        String query = "DELETE FROM school_schema.lectures WHERE (lecture_id = ?);";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(query)){

            prepStatement.setInt(1, id);
            prepStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Lecture getByID(int ID){
        String query = "SELECT * FROM school_schema.lectures WHERE (lecture_id = ?);";
        Lecture lecture = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(query)){

            prepStatement.setInt(1, ID);
            ResultSet resultSet = prepStatement.executeQuery();
            while(resultSet.next()){
                lecture = createFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lecture;
    }

    public Map<String, Integer> getUntil2024sortedByLectureDate(){
        String query = """
        SELECT name, (select count(*) from school_schema.additional_materials
        where additional_materials.lecture_id = lectures.lecture_id) as 'materials_number'
        FROM school_schema.lectures
        where lecture_date < '2024-01-01'
        order by lecture_date;""";
        Map<String, Integer> map = new LinkedHashMap<>();

        try (Connection connection = DBConnection.getConnection(); Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)){

            while (resultSet.next()){
                String lectureName = resultSet.getString("name");
                int materialsNumber = resultSet.getInt("materials_number");

                map.put(lectureName, materialsNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    //Get the lecture created earlier than all others, with the largest amount of homeworks.
    public Lecture getEarliestLectureWithMostHomeworks(){
        String query = """
        SELECT * FROM school_schema.lectures
        where cast(creation_date as unsigned) = (select min(cast(creation_date as unsigned)) from lectures)
        order by (select count(*) from school_schema.homeworks where homeworks.lecture_id = lectures.lecture_id) desc
        limit 1;""";
        Lecture lecture = null;

        try (Connection connection = DBConnection.getConnection(); Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)){

            while (resultSet.next()){
                lecture = createFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lecture;
    }
}
