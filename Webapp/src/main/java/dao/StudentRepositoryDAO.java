package dao;

import models.Student;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StudentRepositoryDAO {
    private static StudentRepositoryDAO instance;

    private StudentRepositoryDAO(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load class.");
            e.printStackTrace();
        }
    }

    public static StudentRepositoryDAO getInstance(){
        if (instance == null) instance = new StudentRepositoryDAO();
        return instance;
    }

    public List<Student> getAll(){
        String procedure = "{call getDataFromTable(?)}";
        List<Student> listFromDB = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             CallableStatement statement = connection.prepareCall(procedure)) {

            statement.setString(1, "students");

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Student student = createFromResultSet(resultSet);
                listFromDB.add(student);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listFromDB;
    }

    private Student createFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("student_id");
        String name = resultSet.getString("name");
        String lastName = resultSet.getString("last_name");
        String phone = resultSet.getString("phone");
        String email = resultSet.getString("email");

        return new Student(id, name, lastName, phone, email);
    }

    public List<Student> getOrderedByLastName(){
        String query = "SELECT * FROM school_schema.students order by last_name;";
        List<Student> studentList = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection(); Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)){

            while (resultSet.next()){
                Student student = createFromResultSet(resultSet);
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public void insert(Student student){
        String query = "INSERT INTO school_schema.students (student_id, name, last_name, phone, email) " +
                "VALUES (?, ?, ?, ?, ?);";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(query)){
            prepStatement.setInt(1, student.getID());
            prepStatement.setString(2, student.getName());
            prepStatement.setString(3, student.getLastName());
            prepStatement.setString(4, student.getPhone());
            prepStatement.setString(5, student.getEmail());

            prepStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByID(int id){
        String query = "DELETE FROM school_schema.students WHERE (student_id = ?);";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(query)){

            prepStatement.setInt(1, id);
            prepStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Student getByID(int ID){
        String query = "SELECT * FROM school_schema.students WHERE (student_id = ?);";
        Student student = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(query)){

            prepStatement.setInt(1, ID);
            ResultSet resultSet = prepStatement.executeQuery();
            while(resultSet.next()){
                student = createFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public Map<Student, Integer> getGroupedByCoursesNumberAndSortedByLastName(){
        String query = """
        SELECT last_name, name, (SELECT COUNT(*)
        FROM school_schema.student_course_relation
        WHERE student_id = students.student_id) AS courses_number
        FROM school_schema.students
        GROUP BY last_name, name, courses_number
        HAVING courses_number > 0
        ORDER BY last_name;""";
        Map<Student, Integer> map = new LinkedHashMap<>();

        try (Connection connection = DBConnection.getConnection(); Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)){

            while (resultSet.next()){
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("last_name");
                int coursesNumber = resultSet.getInt("courses_number");

                Student student = new Student(0, name, lastName, null, null);
                map.put(student, coursesNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }
}
