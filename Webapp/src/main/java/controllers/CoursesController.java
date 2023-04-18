package controllers;

import beans.Config;
import dao.CourseRepositoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Course;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CoursesController", value = "/courses")
public class CoursesController extends HttpServlet {
    private CourseRepositoryDAO courseRepositoryDAO;

    @Override
    public void init() {
        courseRepositoryDAO = new AnnotationConfigApplicationContext(Config.class).
                getBean(CourseRepositoryDAO.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Course> coursesList = courseRepositoryDAO.getAll();
        req.setAttribute("coursesList", coursesList);

        req.getRequestDispatcher("/WEB-INF/views/courses.jsp")
                .forward(req, resp);
    }
}
