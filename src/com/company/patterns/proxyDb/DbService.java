package com.company.patterns.proxyDb;

import java.sql.*;

public class DbService implements DbServiceOperations{
    @Override
    public Connection getConnection(String user, String password) {
        try{
            System.out.println("connect....");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String getAllClient(String request) {
        System.out.println("process request....");
        return null;
    }

    @Override
    public void closeConnect(Connection connection) throws SQLException {
        connection.close();
        System.out.println("close....");
    }
}
