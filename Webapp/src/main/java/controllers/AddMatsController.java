package controllers;

import beans.Config;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.AdditionalMaterial;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.AddMatService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "MatsController", value = "/ad-materials")
public class AddMatsController extends HttpServlet {
    private AddMatService matService;

    @Override
    public void init() {
        matService = new AnnotationConfigApplicationContext(Config.class).getBean(AddMatService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<AdditionalMaterial> list = matService.getAllAsList();
        req.setAttribute("list", list);

        req.getRequestDispatcher("/WEB-INF/views/additional-materials.jsp")
                .forward(req, resp);
    }
}
