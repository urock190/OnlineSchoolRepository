package controllers;

import models.Lecture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import service.LectureService;

import java.util.List;
import java.util.Map;

@Controller
public class LectureController {
    private final LectureService lectureService;

    @Autowired
    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @GetMapping("/lectures")
    public String getLectures(Model model) {
        List<Lecture> list = lectureService.getAll();
        model.addAttribute("list", list);
        return "lectures";
    }

    @GetMapping("/earliest-lecture-with-most-homeworks")
    public String getEarliestLectureWithMostHomeworks(Model model) {
        Lecture lecture = lectureService.getEarliestLectureWithMostHomeworks();
        model.addAttribute("lecture", lecture);
        return "earliest-lecture-with-most-homeworks";
    }

    @GetMapping("/lectures-sorted-by-date")
    public String getUntil2024sortedByLectureDate(Model model) {
        Map<String, Long> map = lectureService.getUntil2024sortedByLectureDate();
        model.addAttribute("map", map);
        return "lectures-sorted-by-date";
    }
}
