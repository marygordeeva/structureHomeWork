package com.company.patterns.proxyDb;

import java.sql.Connection;
import java.sql.SQLException;

public interface DbServiceOperations {
    Connection getConnection(String user, String password) throws SQLException;

    String getAllClient(String request);

    void closeConnect(Connection connection) throws SQLException;
}
