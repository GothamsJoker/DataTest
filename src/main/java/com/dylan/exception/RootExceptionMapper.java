package com.dylan.exception;

import org.glassfish.grizzly.utils.Exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class RootExceptionMapper  implements
        ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception ex) {
        return Response.status(500).entity(Exceptions.getStackTraceAsString(ex)).type("text/plain")
                .build();
    }
}
