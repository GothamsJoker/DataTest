package com.dylan.database;

import org.glassfish.hk2.api.Factory;

import javax.ws.rs.ext.Provider;
import java.sql.SQLException;

@Provider
public class CommentsDatabaseFactory implements Factory<CommentsDatabase> {

    @Override
    public CommentsDatabase provide() {
        return new CommentsDatabase();
    }

    @Override
    public void dispose(CommentsDatabase db) {
        try {
            db.shutdown();
        } catch (SQLException e) {
            throw new RuntimeException("failed to shut down database", e);
        }
    }
}
