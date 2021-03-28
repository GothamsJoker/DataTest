package com.dylan.service;

import com.dylan.database.CommentsDatabase;
import com.dylan.model.Comment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CommentServiceImpl implements CommentService {

    private final CommentsDatabase db;

    public CommentServiceImpl(CommentsDatabase db){
        this.db = db;
    }
    @Override
    public Collection<Comment> findAll()  {

        try {
            return db.readComments();
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }
}
