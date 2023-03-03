package com.another_project.query;

public class SQLBuilder {
    private static final String SELECT = "SELECT * FROM ";
    private static final String INSERT = "INSERT INTO ";
    private static final String CLIENT = "client (name) values (?)";
    private static final String AUTO = "auto (model, number) values (?,?)";
    private static final String RENT = "rent(client_id, auto_id) values (?,?)";
    private static final String VIOLATION = "violation (client_id, auto_id, descr) values (?, ?, ?)";


    public static String getAll(String table) {
        return SELECT + table;
    }

    public static String findById(String table, long id) {
        return SELECT + table + " WHERE id = " + id;
    }

    public static String deleteFromRent(long auto_id, long client_id){
        return "DELETE FROM rent WHERE (auto_id = " + auto_id + ") AND (client_id = " + client_id + ")";
    }

    public static String insert(String table) {
        switch (table) {
            case "auto":
                return INSERT + AUTO;
            case "client":
                return INSERT + CLIENT;

            case "rent":
                return INSERT + RENT;

            case "violation":
                return INSERT + VIOLATION;

        }
        return "non existent table";
    }

    public static String deleteById(String table, long id) {
        return "DELETE FROM " + table + " WHERE id = " + id;
    }

    public static String findClientByName(String name) {
        return SELECT + "client WHERE name = " + name;
    }

    public static String findAutoByModel(String model) {
        return SELECT + "auto WHERE model = '" + model + "'";
    }

    public static String findAutoByNumber(String number) {
        return SELECT + "auto WHERE number = '" + number + "'";
    }

    public static String findClientViolations(long id) {
        return SELECT + "violation WHERE client_id = " + id;
    }

    public static String findClientRentedAutos(long id) {
        return SELECT + "auto WHERE id IN (SELECT auto_id from rent WHERE client_id = " + id + " )";
    }

    public static String update(String table) {
        switch (table) {
            case "auto":
                return "UPDATE auto SET model = ?, number = ? WHERE id = ?";
            case "client":
                return "UPDATE client SET name = ? WHERE id = ?";

        }
        return "non existent table";
    }
}
