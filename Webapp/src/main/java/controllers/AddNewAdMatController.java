package controllers;

import beans.Config;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.AdditionalMaterial;
import models.ResourceType;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.AddMatService;

import java.io.IOException;

@WebServlet(name = "AddNewAdMatController", value = "/add-new-material")
public class AddNewAdMatController extends HttpServlet {
    private AddMatService matService;

    @Override
    public void init() {
        matService = new AnnotationConfigApplicationContext(Config.class).getBean(AddMatService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/add-new-material.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int ID = Integer.parseInt(req.getParameter("ID"));
        String name = req.getParameter("name");
        int lectureID = Integer.parseInt(req.getParameter("lectureID"));
        ResourceType resourceType = ResourceType.valueOf(req.getParameter("resourceType"));

        AdditionalMaterial material = new AdditionalMaterial(name, ID, resourceType, lectureID);
        matService.insert(material);
        resp.sendRedirect(req.getContextPath()+"/ad-materials");
    }
}
