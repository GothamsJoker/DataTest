package com.dylan.service;

import com.dylan.model.Comment;

import java.sql.SQLException;
import java.util.Collection;

/**
 * A {@link CommentService} that uses an {@link CommentsDatabase} to store and retrieve user
 * comments.
 */
public class CommentServiceImpl implements CommentService {

    private final CommentsDatabase db;

    public CommentServiceImpl(CommentsDatabase db){
        this.db = db;
    }

    @Override
    public Collection<Comment> get()  {
        try {
            return db.readComments();
        } catch (SQLException e) {
            throw new RuntimeException("failed to read comments from the database", e);
        }
    }

    @Override
    public Collection<Comment> getByPage(int pageId) {
        try {
            return db.readComments(pageId);
        } catch (SQLException e) {
            throw new RuntimeException(String.format("failed to read comments for page id %s",
                    pageId), e);
        }
    }

    @Override
    public Comment putComment(Comment comment) {
        try {
            return db.saveComment(comment);
        } catch (SQLException e) {
            throw new RuntimeException(String.format("failed to store comment %s",
                    comment.toString(), e));
        }
    }
}
