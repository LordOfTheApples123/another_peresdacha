package com.another_project.servlet;

import com.another_project.entity.Auto;
import com.another_project.entity.Client;
import com.another_project.entity.Violation;
import com.another_project.query.DBService;
import com.another_project.query.RSParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@WebServlet
public class ClientServlet extends HttpServlet {
    DBService dbService = DBService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 50;


        if (req.getParameter("page") != null)
            page = Integer.parseInt(req.getParameter("page"));

        try {

            List<Client> clients = new ArrayList<>();
            if (req.getParameter("action") != null) {
                if (req.getParameter("action").equals("find_id")) {
                    clients.addAll(RSParser.parseClient(dbService.find("client", Long.parseLong(req.getParameter("find_id")))));
                } else if (req.getParameter("action").equals("find_name")) {
                    clients.addAll(RSParser.parseClient(dbService.findClientByName(req.getParameter("find_name"))));
                }
            } else {
                clients.addAll(RSParser.parseClient(dbService.get("client")));
                for(Client client: clients){
                    client.setViolations(RSParser.parseViolation(dbService.findClientViolations(client.getId())));
                    client.setRentedAutos(RSParser.parseAuto(dbService.findClientRentedAutos(client.getId())));
                }
            }
            int noOfRecords = clients.size();
            int noOfPages = noOfRecords / recordsPerPage;
            if (noOfRecords % recordsPerPage != 0) {
                noOfPages++;
            }
            req.setAttribute("noOfPages", noOfPages);
            req.setAttribute("page", page);
            req.setAttribute("recordsPerPage", recordsPerPage);
            req.setAttribute("clients", clients);
            req.getRequestDispatcher("/getClients.jsp").forward(req, resp);
        } catch (SQLException | ServletException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("action").equals("create")){
            String name = req.getParameter("name");
            try {
                dbService.insertClient(new Client(name));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (req.getParameter("action").equals("delete")) {
            long id = Long.parseLong(req.getParameter("delete"));
            try {
                Client client = RSParser.parseClient(dbService.find("client", id)).get(0);
                List<Violation> violations = RSParser.parseViolation(dbService.findClientViolations(client.getId()));
                client.setViolations(violations);
                List<Auto> rentedAutos = RSParser.parseAuto(dbService.findClientRentedAutos(client.getId()));
                client.setRentedAutos(rentedAutos);

                if(!violations.isEmpty()){
                    for(Violation violation: violations){
                        dbService.delete("violation", violation.getId());
                    }
                }

                if(!rentedAutos.isEmpty()){
                    for(Auto auto: rentedAutos){
                        dbService.deleteFromRent(auto.getId(), client.getId());
                    }
                }

                dbService.delete("client", id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        resp.sendRedirect(req.getContextPath()+"/client");
    }
}
