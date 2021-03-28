package com.dylan.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Provides an interface to an SQL database
 */
public interface Database {

    /**
     * @return a {@link Connection} to the database
     */
    Connection getConnection();

    /**
     * Prepare a statement to send to the database.
     * @param query The SQL query
     * @return A {@link PreparedStatement} consisting of the query.
     *
     * @throws SQLException Upon failing to connect to the database
     */
    PreparedStatement prepareStatement(String query) throws SQLException;

}