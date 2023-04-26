package controllers;

import beans.Config;
import dao.StudentRepositoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.Map;

@WebServlet("/students-by-courses-number-sorted-by-last-name")
public class StudentsByCoursesNumberAndSortedByLastName extends HttpServlet {
    private StudentRepositoryDAO studentRepositoryDAO;

    @Override
    public void init() throws ServletException {
        studentRepositoryDAO = new AnnotationConfigApplicationContext(Config.class).
                getBean(StudentRepositoryDAO.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<Student, Long> map = studentRepositoryDAO.getGroupedByCoursesNumberAndSortedByLastName();
        req.setAttribute("map", map);

        req.getRequestDispatcher("/WEB-INF/views/students-by-courses-number-sorted-by-last-name.jsp").
                forward(req, resp);
    }
}
