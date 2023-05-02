package controllers;

import beans.Config;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Homework;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.HomeworkService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeworksController", value = "/homeworks")
public class HomeworksController extends HttpServlet {
    private HomeworkService homeworkService;

    @Override
    public void init() {
        homeworkService = new AnnotationConfigApplicationContext(Config.class).
                getBean(HomeworkService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Homework> list = homeworkService.getAllAsList();
        req.setAttribute("list", list);

        req.getRequestDispatcher("/WEB-INF/views/homeworks.jsp")
                .forward(req, resp);
    }
}
