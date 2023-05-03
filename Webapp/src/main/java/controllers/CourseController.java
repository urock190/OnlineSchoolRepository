package controllers;

import models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import service.CourseService;

import java.util.List;

@Controller
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public String getCourses(Model model) {
        List<Course> coursesList = courseService.getAll();
        model.addAttribute("coursesList", coursesList);
        return "courses";
    }

    @GetMapping("/add-new-course")
    public String courseCreatingPage() {
        return "add-new-course";
    }

    @PostMapping("/add-new-course")
    public String saveCourse(Course course) {
        courseService.insert(course);
        return "redirect:/courses";
    }
}
