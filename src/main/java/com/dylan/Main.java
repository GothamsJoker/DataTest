package com.dylan;

import com.dylan.database.CommentsDatabase;
import com.dylan.service.CommentService;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import java.io.IOException;
import java.net.URI;

/**
 * Main entrypoint into the web server.
 */
@ApplicationPath("/app")
public class Main {

    public static final String BASE_PATH = "/app";
    private static final String BASE_URI = "http://localhost:8080" + BASE_PATH;

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     *
     * @return The Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in com.example.rest package
        final ResourceConfig rc = new ResourceConfig().register(CommentsDatabase.class,
                CommentService.class);

        // setup the binder that creates our key services
        rc.register(new ApplicationBinder());

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started at %s\nHit enter to stop it...",
                BASE_URI));
        System.in.read();
        server.shutdown();
    }
}
