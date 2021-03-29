package com.dylan.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * An implementation of {@link Database} that connects to an SQLite database
 */
public abstract class SqlDatabase implements Database {

    private final String createTable;
    private volatile Connection connection;

    public SqlDatabase(String createTable) {
        this.createTable = createTable;
    }

    private void init(String createTable) throws SQLException {
        // get connection
        connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\dylan\\test.db");
        prepareStatement(createTable);
    }

    @Override
    public synchronized Connection getConnection() throws SQLException {
        if (connection == null) {
            init(createTable);
        }

        return connection;
    }

    @Override
    public PreparedStatement prepareStatement(String query) throws SQLException {
        return connection.prepareStatement(query);
    }

    @Override
    public void shutdown() throws SQLException {
        connection.close();
    }
}