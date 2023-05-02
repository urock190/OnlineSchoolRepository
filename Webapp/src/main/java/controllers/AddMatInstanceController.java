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

@WebServlet(name = "MatInstanceController", urlPatterns = {"/mat-instance"})
public class AddMatInstanceController extends HttpServlet {
    private AddMatService matService;

    @Override
    public void init(){
        matService = new AnnotationConfigApplicationContext(Config.class).getBean(AddMatService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int ID = Integer.parseInt(req.getParameter("ID"));
        AdditionalMaterial material = matService.getByID(ID);

        req.setAttribute("material", material);
        req.getRequestDispatcher("/WEB-INF/views/material-instance.jsp").forward(req, resp);
    }
}
