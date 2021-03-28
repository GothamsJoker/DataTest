package com.dylan.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

interface Database {

    Connection getConnection();
    PreparedStatement prepareStatement(String query) throws SQLException;

}
