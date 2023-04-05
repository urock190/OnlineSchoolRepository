package controllers;

import dao.LectureRepositoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Lecture;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "LecturesController", value = "/lectures")
public class LecturesController extends HttpServlet {
    private LectureRepositoryDAO lectureRepositoryDAO;

    @Override
    public void init() {
        lectureRepositoryDAO = LectureRepositoryDAO.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Lecture> list = lectureRepositoryDAO.getAll();
        req.setAttribute("list", list);

        req.getRequestDispatcher("/WEB-INF/views/lectures.jsp")
                .forward(req, resp);
    }
}