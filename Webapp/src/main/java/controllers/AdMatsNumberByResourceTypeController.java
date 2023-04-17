package controllers;

import dao.AddMatRepositoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.ResourceType;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Map;

@WebServlet("/number-of-ad-mats-by-resource-type")
public class AdMatsNumberByResourceTypeController extends HttpServlet {
    private AddMatRepositoryDAO matRepositoryDAO;

    @Override
    public void init() throws ServletException {
        matRepositoryDAO = new ClassPathXmlApplicationContext("context.xml").
                getBean("addMatRepositoryDAO", AddMatRepositoryDAO.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<ResourceType, Integer> map = matRepositoryDAO.numberOfAdMatsByResourceType();
        req.setAttribute("map", map);

        req.getRequestDispatcher("/WEB-INF/views/number-of-ad-mats-by-resource-type.jsp").forward(req, resp);
    }
}
