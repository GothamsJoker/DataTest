package com.dylan.service;

import com.dylan.model.Comment;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
     Collection<Comment> getByPage(@PathParam("pageId") int pageId);

     /**
      * PUT a user comment to be stored.
      * @param comment The comment
      */
     Comment putComment(Comment comment);
}
