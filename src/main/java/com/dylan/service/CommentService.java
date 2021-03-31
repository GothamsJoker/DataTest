package com.dylan.service;

import com.dylan.model.Comment;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * Publshes a REST API for user comments.
 */
@Path("/comments")
public interface CommentService {

     /**
      * @return all of the user comments across all pages.
      */
     @GET
     @Produces(MediaType.APPLICATION_JSON)
     Collection<Comment> get();

     /**
      * GET all comments on a given page.
      * @param pageId The id of the web page.
      *
      * @return A {@link Collection} of comments.
      */
     @GET
     @Path("/{pageId}")
     @Produces(MediaType.APPLICATION_JSON)
     Collection<Comment> getByPage(@PathParam("pageId") int pageId);

     /**
      * PUT a user comment to be stored.
      * @param comment The comment
      */
     @POST
     @Consumes(MediaType.APPLICATION_JSON)
     Comment putComment(Comment comment);
}
