package com.another_project.servlet;

import com.another_project.entity.Auto;
import com.another_project.query.DBService;
import com.another_project.query.RSParser;
import com.another_project.query.SQLBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
@WebServlet
public class UpdateAutoServlet extends HttpServlet {
    DBService dbService = DBService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("update"));
        try {
            List<Auto> autos = RSParser.parseAuto(dbService.find("auto", id));
            req.setAttribute("auto", autos.get(0));
            req.getRequestDispatcher("/autoForm.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        String model = req.getParameter("model");
        String number = req.getParameter("number");
        try {
            dbService.updateAuto(new Auto(id, model, number));
            resp.sendRedirect(req.getContextPath()+"/auto");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
