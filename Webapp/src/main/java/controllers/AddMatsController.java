package controllers;

import dao.AddMatRepositoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.AdditionalMaterial;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "MatsController", value = "/ad-materials")
public class AddMatsController extends HttpServlet {
    private AddMatRepositoryDAO matRepositoryDAO;

    @Override
    public void init() {
        matRepositoryDAO = AddMatRepositoryDAO.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<AdditionalMaterial> list = matRepositoryDAO.getAllAsList();
        req.setAttribute("list", list);

        req.getRequestDispatcher("/WEB-INF/views/additional-materials.jsp")
                .forward(req, resp);
    }
}
