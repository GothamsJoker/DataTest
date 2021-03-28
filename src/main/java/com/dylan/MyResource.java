package com.dylan;

import com.dylan.model.Comment;
import com.dylan.service.CommentService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;

@Path("comments")
public class MyResource {

    @Inject
    private CommentService commentService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response message(){

        Collection<Comment> comments = commentService.findAll();
        GenericEntity<Collection<Comment>> myEntity = new GenericEntity<Collection<Comment>>(comments){};

        return Response.status(200).entity(myEntity).build();
    }
}
