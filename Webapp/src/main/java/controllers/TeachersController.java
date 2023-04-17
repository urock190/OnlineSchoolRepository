package controllers;

import dao.TeacherRepositoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Teacher;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "TeachersController", value = "/teachers")
public class TeachersController extends HttpServlet {
    private TeacherRepositoryDAO teacherRepositoryDAO;

    @Override
    public void init() {
        teacherRepositoryDAO = new ClassPathXmlApplicationContext("context.xml").
                getBean("teacherRepositoryDAO", TeacherRepositoryDAO.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Teacher> list = teacherRepositoryDAO.getAll();
        req.setAttribute("list", list);

        req.getRequestDispatcher("/WEB-INF/views/teachers.jsp")
                .forward(req, resp);
    }
}
