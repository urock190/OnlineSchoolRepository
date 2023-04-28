package controllers;

import beans.Config;
import dao.AddMatRepositoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.ResourceType;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.Map;

@WebServlet("/number-of-ad-mats-by-resource-type")
public class AdMatsNumberByResourceTypeController extends HttpServlet {
    private AddMatRepositoryDAO matRepositoryDAO;

    @Override
    public void init() {
        matRepositoryDAO = new AnnotationConfigApplicationContext(Config.class).
                getBean(AddMatRepositoryDAO.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<ResourceType, Long> map = matRepositoryDAO.numberOfAdMatsByResourceType();
        req.setAttribute("map", map);

        req.getRequestDispatcher("/WEB-INF/views/number-of-ad-mats-by-resource-type.jsp").forward(req, resp);
    }
}