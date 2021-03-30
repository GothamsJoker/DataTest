package com.dylan.service;

import org.glassfish.hk2.api.Factory;

import javax.ws.rs.ext.Provider;

/**
 * Provides an instance of our {@link CommentService}
 */
@Provider
public class CommentServiceFactory implements Factory<CommentService> {

    @Override
    public CommentService provide() {
        return new CommentServiceImpl();
    }

    @Override
    public void dispose(CommentService commentService) {
        //
    }
}
