package controllers;

import beans.Config;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.LectureService;

import java.io.IOException;
import java.util.Map;

@WebServlet(name = "LecturesSortedByDateController", value = "/lectures-sorted-by-date")
public class LecturesSortedByDateController extends HttpServlet {
    private LectureService lectureService;

    @Override
    public void init() {
        lectureService = new AnnotationConfigApplicationContext(Config.class).
                getBean(LectureService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Long> map = lectureService.getUntil2024sortedByLectureDate();
        req.setAttribute("map", map);

        req.getRequestDispatcher("/WEB-INF/views/lectures-sorted-by-date.jsp").forward(req, resp);
    }
}
