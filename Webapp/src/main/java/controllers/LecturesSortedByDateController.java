package controllers;

import dao.LectureRepositoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet(name = "LecturesSortedByDateController", value = "/lectures-sorted-by-date")
public class LecturesSortedByDateController extends HttpServlet {
    private LectureRepositoryDAO lectureRepositoryDAO;

    @Override
    public void init() throws ServletException {
        lectureRepositoryDAO = LectureRepositoryDAO.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Integer> map = lectureRepositoryDAO.getUntil2024sortedByLectureDate();
        req.setAttribute("map", map);

        req.getRequestDispatcher("/WEB-INF/views/lectures-sorted-by-date.jsp").forward(req, resp);
    }
}
