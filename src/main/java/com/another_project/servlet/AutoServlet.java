package com.another_project.servlet;

import com.another_project.entity.Auto;
import com.another_project.entity.Client;
import com.another_project.query.DBService;
import com.another_project.query.RSParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutoServlet extends HttpServlet {
    DBService dbService = DBService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 50;


        if (req.getParameter("page") != null)
            page = Integer.parseInt(req.getParameter("page"));

        try {

            List<Auto> autos = new ArrayList<>();
            if (req.getParameter("action") != null) {
                if (req.getParameter("action").equals("find_model")) {
                    autos.addAll(RSParser.parseAuto(dbService.findAutoByModel(req.getParameter("find_model"))));
                } else if (req.getParameter("action").equals("find_number")) {
                    autos.addAll(RSParser.parseAuto(dbService.findAutoByNumber(req.getParameter("find_number"))));
                }
            } else {
                autos.addAll(RSParser.parseAuto(dbService.get("auto")));
            }
            int noOfRecords = autos.size();
            int noOfPages = noOfRecords / recordsPerPage;
            if (noOfRecords % recordsPerPage != 0) {
                noOfPages++;
            }
            req.setAttribute("noOfPages", noOfPages);
            req.setAttribute("page", page);
            req.setAttribute("recordsPerPage", recordsPerPage);
            req.setAttribute("autos", autos);
            req.getRequestDispatcher("/getAutos.jsp").forward(req, resp);
        } catch (SQLException | ServletException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("action").equals("create")){
            String model = req.getParameter("model");
            String number = req.getParameter("number");
            try {
                dbService.insertAuto(new Auto(model, number));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (req.getParameter("action").equals("delete")) {
            long id = Long.parseLong(req.getParameter("delete"));
            try {
                dbService.delete("auto", id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
