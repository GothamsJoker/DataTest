package com.dylan.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Provides an interface to an SQL database
 */
public interface Database {

    /**
     * Get a {@link Connection} to the database. Prefer {@link Database#prepareStatement(String)}
     * over directly getting a connection yourself.
     *
     * @return The connection.
     *
     * @throws SQLException Upon failing to get the connection
     */
    Connection getConnection() throws SQLException;

    /**
     * Prepare a statement to send to the database.
     * @param query The SQL query
     * @return A {@link PreparedStatement} consisting of the query.
     *
     * @throws SQLException Upon failing to connect to the database
     */
    PreparedStatement prepareStatement(String query) throws SQLException;

    /**
     * Closes the {@link Connection} and shuts down the database.
     */
    void shutdown() throws SQLException;

}