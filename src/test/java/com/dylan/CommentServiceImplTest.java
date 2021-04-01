package com.dylan;

import com.dylan.model.Comment;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

import static com.dylan.Main.BASE_URI;
import static org.junit.Assert.assertEquals;

public class CommentServiceImplTest extends JerseyTest {

    static {
        System.setProperty("jersey.config.test.container.port", "8080");
    }

    @Override
    protected Application configure() {
        return new Main();
    }

    @Override
    protected URI getBaseUri() {
        return URI.create(BASE_URI);
    }

    @Test
    public void it_posts_comment() {
        Comment c = new Comment("com/dylan", "eat my ass", 1, System.currentTimeMillis());
        Response response =
                target("/comments/create/").request(MediaType.APPLICATION_JSON_TYPE).post(Entity.json(c));
        System.out.println(response.readEntity(String.class));

        assertEquals("HTTP Response code should be a 201, indicating the request completed" +
                " with no error.", 201, response.getStatus());
    }

    @Test
    public void it_reads_comments() {
        Response r = target("/comments/all").request().get();

        assertEquals("asdf response", 201, r.getStatus());
    }
}
