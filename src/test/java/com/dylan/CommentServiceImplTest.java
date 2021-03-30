package com.dylan;

import com.dylan.model.Comment;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.dylan.Main.BASE_PATH;
import static org.junit.Assert.assertEquals;

public class CommentServiceImplTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig().packages("java.dylan").register(new ApplicationBinder());
    }

    @Test
    public void it_posts_comment() {
        Comment c = new Comment("com/dylan", "eat my ass", 1, System.currentTimeMillis());
        Response response = target(BASE_PATH + "/comments").request().post(Entity.entity(c,
                MediaType.APPLICATION_JSON));
        Comment posted = response.readEntity(Comment.class);

        assertEquals("HTTP Response code should be a 201, indicating the request completed" +
                " with no error.", 201, response.getStatus());
        assertEquals("Posted service should match what we submitted", c, posted);
    }
}
