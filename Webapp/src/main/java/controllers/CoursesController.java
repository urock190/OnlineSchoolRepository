package controllers;

import beans.Config;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Course;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.CourseService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CoursesController", value = "/courses")
public class CoursesController extends HttpServlet {
    private CourseService courseService;

    @Override
    public void init() {
        courseService = new AnnotationConfigApplicationContext(Config.class).
                getBean(CourseService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Course> coursesList = courseService.getAll();
        req.setAttribute("coursesList", coursesList);

        req.getRequestDispatcher("/WEB-INF/views/courses.jsp")
                .forward(req, resp);
    }
}
