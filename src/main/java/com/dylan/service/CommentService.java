package com.dylan.service;

import com.dylan.model.Comment;

import java.util.Collection;
import java.util.List;

public interface CommentService {
     Collection<Comment> findAll();
}
