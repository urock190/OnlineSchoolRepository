package controllers;

import dao.LectureRepositoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Lecture;

import java.io.IOException;

@WebServlet("/earliest-lecture-with-most-homeworks")
public class EarliestLectureWithMostHomeworksController extends HttpServlet {
    private LectureRepositoryDAO lectureRepositoryDAO;

    @Override
    public void init() throws ServletException {
        lectureRepositoryDAO = LectureRepositoryDAO.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Lecture lecture = lectureRepositoryDAO.getEarliestLectureWithMostHomeworks();
        req.setAttribute("lecture", lecture);

        req.getRequestDispatcher("/WEB-INF/views/earliest-lecture-with-most-homeworks.jsp").forward(req, resp);
    }
}
