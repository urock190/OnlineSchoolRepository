package controllers;

import beans.Config;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.ResourceType;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.AddMatService;

import java.io.IOException;
import java.util.Map;

@WebServlet("/number-of-ad-mats-by-resource-type")
public class AdMatsNumberByResourceTypeController extends HttpServlet {
    private AddMatService matService;

    @Override
    public void init() {
        matService = new AnnotationConfigApplicationContext(Config.class).getBean(AddMatService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<ResourceType, Long> map = matService.numberOfAdMatsByResourceType();
        req.setAttribute("map", map);

        req.getRequestDispatcher("/WEB-INF/views/number-of-ad-mats-by-resource-type.jsp").forward(req, resp);
    }
}
