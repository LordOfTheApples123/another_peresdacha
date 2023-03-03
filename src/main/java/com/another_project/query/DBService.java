package com.another_project.query;

import com.another_project.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBService {
    private final Connection connection;
    private DBService(){
        this.connection = ConnectionFactory.createConnection();
    }

    private static class LazyHolder{
        public static final DBService INSTANCE = new DBService();
    }

    public static DBService getInstance() {
        return LazyHolder.INSTANCE;
    }

    public ResultSet get(String table) throws SQLException {
        PreparedStatement st = connection.prepareStatement(SQLBuilder.getAll(table));
        ResultSet rs = st.executeQuery();

        return rs;
    }

    public ResultSet find(String table, long id) throws SQLException{
        PreparedStatement st = connection.prepareStatement(SQLBuilder.findById(table, id));
        ResultSet rs = st.executeQuery();

        return rs;
    }

    public void delete(String table, long id) throws SQLException{
        PreparedStatement st = connection.prepareStatement(SQLBuilder.deleteById(table, id));
        st.executeUpdate();
        st.close();
    }

    public void insertClient(Client client) throws SQLException {
        PreparedStatement st = connection.prepareStatement(SQLBuilder.insert("client"));
        st.setString(1, client.getName());
        st.executeUpdate();
        st.close();
    }

    public void insertAuto(Auto auto) throws SQLException {
        PreparedStatement st = connection.prepareStatement(SQLBuilder.insert("auto"));// INSERT INTO auto (model, number) values (?,?)
        st.setString(1, auto.getModel()); //DROP auto
        st.setString(2, auto.getNumber());
        st.executeUpdate();
        st.close();
    }

    public void insertRent(Rent rent) throws SQLException{
        PreparedStatement st = connection.prepareStatement((SQLBuilder.insert("rent")));
        st.setLong(1, rent.getClient_id());
        st.setLong(2, rent.getAuto_id());
        st.executeUpdate();
        st.close();
    }

    public void insertViolation(Violation violation) throws  SQLException{
        PreparedStatement st = connection.prepareStatement(SQLBuilder.insert("violation"));
        st.setLong(1, violation.getClient_id());
        st.setLong(2, violation.getAuto_id());
        st.setString(3, violation.getDescr());

        st.executeUpdate();

    }

    public void updateClient(Client client) throws SQLException{
        PreparedStatement st = connection.prepareStatement(SQLBuilder.update("client"));
        st.setString(1, client.getName());
        st.setLong(2, client.getId());
        st.executeUpdate();
        st.close();
    }

    public void updateAuto(Auto auto) throws SQLException{
        PreparedStatement st = connection.prepareStatement(SQLBuilder.update("auto"));
        st.setString(1, auto.getModel());
        st.setString(2, auto.getNumber());
        st.setLong(3, auto.getId());
        st.executeUpdate();
        st.close();
    }

    public ResultSet findClientByName(String name) throws SQLException{
        PreparedStatement st = connection.prepareStatement(SQLBuilder.findClientByName(name));
        ResultSet rs = st.executeQuery();

        return rs;
    }

    public ResultSet findAutoByModel(String model) throws SQLException{
        PreparedStatement st = connection.prepareStatement(SQLBuilder.findAutoByModel(model));
        ResultSet rs = st.executeQuery();

        return rs;
    }

    public ResultSet findAutoByNumber(String number) throws SQLException{
        PreparedStatement st = connection.prepareStatement(SQLBuilder.findAutoByNumber(number));
        ResultSet rs = st.executeQuery();

        return rs;
    }

    public ResultSet findClientViolations(long id) throws SQLException {
        PreparedStatement st = connection.prepareStatement(SQLBuilder.findClientViolations(id));
        ResultSet rs = st.executeQuery();

        return rs;
    }

    public ResultSet findClientRentedAutos(long id) throws SQLException{
        PreparedStatement st = connection.prepareStatement(SQLBuilder.findClientRentedAutos(id));
        ResultSet rs = st.executeQuery();

        return rs;
    }

    public void deleteFromRent(long auto_id, long client_id) throws SQLException {
        PreparedStatement st = connection.prepareStatement(SQLBuilder.deleteFromRent(auto_id, client_id));
        st.executeUpdate();
    }
}
