package com.another_project.query;

import com.another_project.entity.Auto;
import com.another_project.entity.Client;
import com.another_project.entity.Rent;
import com.another_project.entity.Violation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RSParser {
    public static List<Auto> parseAuto(ResultSet rs) throws SQLException {
        List<Auto> autos = new ArrayList<>();
        while(rs.next()){
            autos.add(new Auto(rs.getLong("id"),
                    rs.getString("model"),
                    rs.getString("number")));
        }
        return autos;
    }

    public static List<Client> parseClient(ResultSet rs) throws SQLException{
        List<Client> clients = new ArrayList<>();
        while(rs.next()){
            clients.add(new Client(rs.getLong("id"),
                    rs.getString("name")));
        }
        return clients;
    }

    public static List<Rent> parseRent(ResultSet rs) throws SQLException{
        List<Rent> rents = new ArrayList<>();
        while (rs.next()){
            rents.add(new Rent(rs.getLong("id"),
                    rs.getLong("client_id"),
                    rs.getLong("auto_id")));
        }
        return rents;
    }

    public static List<Violation> parseViolation(ResultSet rs) throws SQLException{
        List<Violation> violations = new ArrayList<>();
        while(rs.next()){
            violations.add(new Violation(rs.getLong("id"),
                    rs.getLong("client_id"),
                    rs.getLong("auto_id"),
                    rs.getString("descr")));
        }
        return violations;
    }
}
