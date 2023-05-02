package controllers;

import beans.Config;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Lecture;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.LectureService;

import java.io.IOException;

@WebServlet("/earliest-lecture-with-most-homeworks")
public class EarliestLectureWithMostHomeworksController extends HttpServlet {
    private LectureService lectureService;

    @Override
    public void init() {
        lectureService = new AnnotationConfigApplicationContext(Config.class).
                getBean(LectureService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Lecture lecture = lectureService.getEarliestLectureWithMostHomeworks();
        req.setAttribute("lecture", lecture);

        req.getRequestDispatcher("/WEB-INF/views/earliest-lecture-with-most-homeworks.jsp").forward(req, resp);
    }
}
