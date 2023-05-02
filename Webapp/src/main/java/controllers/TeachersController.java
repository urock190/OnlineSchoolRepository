package controllers;

import beans.Config;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Teacher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.TeacherService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "TeachersController", value = "/teachers")
public class TeachersController extends HttpServlet {
    private TeacherService teacherService;

    @Override
    public void init() {
        teacherService = new AnnotationConfigApplicationContext(Config.class).getBean(TeacherService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Teacher> list = teacherService.getAll();
        req.setAttribute("list", list);

        req.getRequestDispatcher("/WEB-INF/views/teachers.jsp")
                .forward(req, resp);
    }
}
