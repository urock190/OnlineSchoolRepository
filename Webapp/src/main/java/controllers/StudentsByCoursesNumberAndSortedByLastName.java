package controllers;

import beans.Config;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.StudentService;

import java.io.IOException;
import java.util.Map;

@WebServlet("/students-by-courses-number-sorted-by-last-name")
public class StudentsByCoursesNumberAndSortedByLastName extends HttpServlet {
    private StudentService studentService;

    @Override
    public void init() {
        studentService = new AnnotationConfigApplicationContext(Config.class).
                getBean(StudentService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<Student, Long> map = studentService.getGroupedByCoursesNumberAndSortedByLastName();
        req.setAttribute("map", map);

        req.getRequestDispatcher("/WEB-INF/views/students-by-courses-number-sorted-by-last-name.jsp").
                forward(req, resp);
    }
}
