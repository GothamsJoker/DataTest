package com.dylan;

import com.dylan.model.Comment;
import com.dylan.service.CommentService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("comments")
public class WebApp {

    @Inject
    private CommentService commentService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Comment> get() {
        return commentService.get();
    }

    @GET
    @Path("/{pageId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Comment> getForPage(int pageId){
        return commentService.getByPage(pageId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Comment postComment(Comment comment) {
        return commentService.putComment(comment);
    }
}
