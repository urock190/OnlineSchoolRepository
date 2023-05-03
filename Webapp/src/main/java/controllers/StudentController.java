package controllers;

import models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import service.StudentService;

import java.util.List;
import java.util.Map;

@Controller
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public String getStudents(Model model) {
        List<Student> list = studentService.getAll();
        model.addAttribute("list", list);
        return "students";
    }

    @GetMapping("/students-by-courses-number-sorted-by-last-name")
    public String studentsByCoursesNumberAndSortedByLastName(Model model) {
        Map<Student, Long> map = studentService.getGroupedByCoursesNumberAndSortedByLastName();
        model.addAttribute("map", map);
        return "students-by-courses-number-sorted-by-last-name";
    }

    @GetMapping("/students-by-last-name-all-info")
    public String studentsSortedByLastName(Model model) {
        List<Student> list = studentService.getOrderedByLastName();
        model.addAttribute("list", list);
        return "students-by-last-name-all-info";
    }
}
