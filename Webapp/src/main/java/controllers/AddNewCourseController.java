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

@WebServlet(name = "AddNewCourseController", value = "/add-new-course")
public class AddNewCourseController extends HttpServlet {
    private CourseService courseService;

    @Override
    public void init() {
        courseService = new AnnotationConfigApplicationContext(Config.class).
                getBean(CourseService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/add-new-course.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int ID = Integer.parseInt(req.getParameter("ID"));
        String name = req.getParameter("name");

        Course course = new Course(ID, name);
        courseService.insert(course);
        resp.sendRedirect(req.getContextPath()+"/courses");
    }
}
