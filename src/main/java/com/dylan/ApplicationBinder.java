package com.dylan;

import com.dylan.database.CommentsDatabase;
import com.dylan.database.CommentsDatabaseFactory;
import com.dylan.service.CommentService;
import com.dylan.service.CommentServiceFactory;
import org.glassfish.jersey.internal.inject.AbstractBinder;

/**
 * The {@link AbstractBinder} that allows us to automagically create our key services. This works
 * through a concept called dependency injection. It's not as complicated as it sounds. Basically
 * this sets it up so we don't have to manually call the constructor to create each service, they'll
 * all be created and linked together for us.
 */
public class ApplicationBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bindFactory(CommentsDatabaseFactory::new).to(CommentsDatabase.class).proxy(true);
        bindFactory(CommentServiceFactory::new).to(CommentService.class).proxy(true);
    }

}
