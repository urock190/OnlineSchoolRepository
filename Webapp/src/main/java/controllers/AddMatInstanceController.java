package controllers;

import beans.Config;
import dao.AddMatRepositoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.AdditionalMaterial;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

@WebServlet(name = "MatInstanceController", urlPatterns = {"/mat-instance"})
public class AddMatInstanceController extends HttpServlet {
    private AddMatRepositoryDAO matRepositoryDAO;

    @Override
    public void init(){
        matRepositoryDAO = new AnnotationConfigApplicationContext(Config.class).
                getBean(AddMatRepositoryDAO.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int ID = Integer.parseInt(req.getParameter("ID"));
        AdditionalMaterial material = matRepositoryDAO.getByID(ID);

        req.setAttribute("material", material);
        req.getRequestDispatcher("/WEB-INF/views/material-instance.jsp").forward(req, resp);
    }
}
