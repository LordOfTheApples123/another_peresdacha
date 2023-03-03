package com.another_project.servlet;

import com.another_project.entity.Auto;
import com.another_project.entity.Client;
import com.another_project.entity.Rent;
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
import java.util.List;

@WebServlet
public class ClientProfile extends HttpServlet {
    DBService dbService = DBService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("update"));
        try {
            List<Client> client = RSParser.parseClient(dbService.find("client", id));
            Client client1 = client.get(0);

                client1.setViolations(RSParser.parseViolation(dbService.findClientViolations(client1.getId())));
                client1.setRentedAutos(RSParser.parseAuto(dbService.findClientRentedAutos(client1.getId())));

            req.setAttribute("client", client.get(0));
            req.getRequestDispatcher("/clientProfile.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getParameter("action").equals("add_violation")) {
                long id = Long.parseLong(req.getParameter("violation_client_id"));
                String descr = req.getParameter("descr");
                dbService.insertViolation(new Violation(id, 1, descr));
            } else if (req.getParameter("action").equals("add_rent")) {
                long id = Long.parseLong(req.getParameter("rent_client_id"));
                long auto_id = Long.parseLong(req.getParameter("auto_id"));
                dbService.insertRent(new Rent(id, auto_id));
            } else if (req.getParameter("action").equals("delete_violation")) {
                long id = Long.parseLong(req.getParameter("id"));
                dbService.delete("violation", id);
            } else if (req.getParameter("action").equals("delete_rent")) {
                long auto_id = Long.parseLong(req.getParameter("auto_id"));
                long client_id = Long.parseLong(req.getParameter("client_id"));
                dbService.deleteFromRent(auto_id, client_id);
            } else {
                long id = Long.parseLong(req.getParameter("id"));
                String name = req.getParameter("name");
                dbService.updateClient(new Client(id, name));
            }
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
        resp.sendRedirect(req.getContextPath()+"/clientprofile?update=" + req.getParameter("prev_id"));
    }
}
