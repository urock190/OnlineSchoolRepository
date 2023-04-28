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
import java.util.List;

@WebServlet("/students-by-last-name-all-info")
public class StudentsSortedByLastNameController extends HttpServlet {
    private StudentRepositoryDAO studentRepositoryDAO;

    @Override
    public void init() throws ServletException {
        studentRepositoryDAO = new AnnotationConfigApplicationContext(Config.class).
                getBean(StudentRepositoryDAO.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> list = studentRepositoryDAO.getOrderedByLastName();
        req.setAttribute("list", list);

        req.getRequestDispatcher("/WEB-INF/views/students-by-last-name-all-info.jsp").forward(req, resp);
    }
}