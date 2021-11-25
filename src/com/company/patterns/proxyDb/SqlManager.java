package com.company.patterns.proxyDb;

import java.sql.Connection;
import java.sql.SQLException;

public class SqlManager {
    private DbServiceOperations dbServiceOperations;

    public SqlManager(DbServiceOperations dbServiceOperations){
        this.dbServiceOperations = dbServiceOperations;
    }

    public String getAllClients(String user, String password, String request){
        try {
            Connection connection = dbServiceOperations.getConnection(user, password);

            String result = dbServiceOperations.getAllClient(request);

            dbServiceOperations.closeConnect(connection);

            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
