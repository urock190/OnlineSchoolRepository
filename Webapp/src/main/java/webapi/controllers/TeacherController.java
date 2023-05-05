package webapi.controllers;

import webapi.models.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import webapi.service.TeacherService;

import java.util.List;

@Controller
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/teachers")
    public String getTeachers(Model model) {
        List<Teacher> list = teacherService.getAll();
        model.addAttribute("list", list);
        return "teachers";
    }

    @GetMapping("/teachers-with-last-name-to-the-letter-N")
    public String teachersWithLastNameToTheLetterN(Model model) {
        List<Teacher> list = teacherService.teachersWithLastNameToTheLetterN();
        model.addAttribute("list", list);
        return "teachers-with-last-name-to-the-letter-N";
    }
}
