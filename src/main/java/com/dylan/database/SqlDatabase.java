package com.dylan.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class SqlDatabase implements Database {
    private final Connection connection;

    public SqlDatabase(String driver) throws SQLException{
        // get connection
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\dylan\\test.db");
    }


    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public PreparedStatement prepareStatement(String query) throws SQLException {
        return connection.prepareStatement(query);

    }
}