package webapi.controllers;

import webapi.models.Homework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import webapi.service.HomeworkService;

import java.util.List;

@Controller
public class HomeworkController {
    private final HomeworkService homeworkService;

    @Autowired
    public HomeworkController(HomeworkService homeworkService) {
        this.homeworkService = homeworkService;
    }

    @GetMapping("/homeworks")
    public String getHomeworks(Model model) {
        List<Homework> list = homeworkService.getAllAsList();
        model.addAttribute("list", list);
        return "homeworks";
    }
}
