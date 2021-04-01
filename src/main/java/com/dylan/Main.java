package com.dylan;

import com.dylan.database.CommentsDatabase;
import com.dylan.exception.RootExceptionMapper;
import com.dylan.service.CommentService;
import com.dylan.service.CommentServiceImpl;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Main entrypoint into the web server.
 */
@ApplicationPath("/app")
public class Main extends Application {

    public static final String BASE_PATH = "/app";
    public static final String BASE_URI = "http://localhost:8080" + BASE_PATH;

    @Override
    public Set<Class<?>> getClasses() {
        return Stream.of(
                CommentsDatabase.class,
                CommentService.class,
                CommentServiceImpl.class,
                RootExceptionMapper.class).collect(Collectors.toSet());
    }

    @Override
    public Set<Object> getSingletons() {
        return Stream.of(new CommentServiceImpl(new CommentsDatabase())).collect(Collectors.toSet());
    }
}
