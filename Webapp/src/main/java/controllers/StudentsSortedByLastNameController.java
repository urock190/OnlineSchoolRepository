package controllers;

import dao.StudentRepositoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Student;

import java.io.IOException;
import java.util.List;

@WebServlet("/students-by-last-name-all-info")
public class StudentsSortedByLastNameController extends HttpServlet {
    private StudentRepositoryDAO studentRepositoryDAO;

    @Override
    public void init() throws ServletException {
        studentRepositoryDAO = StudentRepositoryDAO.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> list = studentRepositoryDAO.getOrderedByLastName();
        req.setAttribute("list", list);

        req.getRequestDispatcher("/WEB-INF/views/students-by-last-name-all-info.jsp").forward(req, resp);
    }
}
