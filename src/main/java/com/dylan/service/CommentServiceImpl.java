package com.dylan.service;

import com.dylan.database.CommentsDatabase;
import com.dylan.model.Comment;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.Collection;

/**
 * A {@link CommentService} that uses an {@link CommentsDatabase} to store and retrieve user
 * comments.
 */
@Path("/comments")
public class CommentServiceImpl implements CommentService {

    private final CommentsDatabase db;

    public CommentServiceImpl(CommentsDatabase db) {
        this.db = db;
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Collection<Comment> get()  {
        try {
            return db.readComments();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("failed to read comments from the database", e);
        }
    }

    @GET
    @Path("{pageId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Collection<Comment> getByPage(int pageId) {
        try {
            return db.readComments(pageId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("failed to read comments for page id %s",
                    pageId), e);
        }
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Comment putComment(Comment comment) {
        try {
            return db.saveComment(comment);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("failed to store comment %s",
                    comment.toString(), e));
        }
    }
}
