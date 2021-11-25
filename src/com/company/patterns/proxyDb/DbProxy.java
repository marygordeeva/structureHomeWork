package com.company.patterns.proxyDb;

import java.sql.Connection;
import java.sql.SQLException;

public class DbProxy implements DbServiceOperations {

    private DbService service;
    private Connection connection;

    public DbProxy() {
        service = new DbService();
    }

    @Override
    public Connection getConnection(String user, String password) {
        try {
            if(!validConnection()){
                return null;
            }
            if (user.isEmpty()) {
                System.out.println("User field is empty");
                return null;
            }

            if (password.isEmpty()) {
                System.out.println("Password field is empty");
                return null;
            }

            if (connection.isClosed()) {
                service.getConnection(user, password);
            }

            return connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String getAllClient(String request) {
        try {
            if (!validConnection()) {
                return null;
            }
            System.out.println("Run getAllClient");

            if (request.isEmpty()) {
                System.out.println("Password field is empty");
                return null;
            }

            String result = service.getAllClient(request);

            System.out.println("Stop getAllClient");
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void closeConnect(Connection connection) {
        try {
           if (validConnection()){
               service.closeConnect(connection);
           }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean validConnection() throws SQLException {
        if (connection == null){
            System.out.println("Connection is null");
            return false;
        }

        if (connection.isClosed()){
            System.out.println("Connection is closed");
            return false;
        }

        return true;
    }
}
