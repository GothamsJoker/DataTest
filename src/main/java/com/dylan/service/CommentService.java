package com.dylan.service;

import com.dylan.model.Comment;

import java.util.Collection;

/**
 * Publshes a REST API for user comments.
 */
public interface CommentService {

     /**
      * @return all of the user comments across all pages.
      */
     Collection<Comment> get();

     /**
      * GET all comments on a given page.
      * @param pageId The id of the web page.
      *
      * @return A {@link Collection} of comments.
      */
     Collection<Comment> getByPage(int pageId);

     /**
      * PUT a user comment to be stored.
      * @param comment The comment
      */
     void putComment(Comment comment);
}
