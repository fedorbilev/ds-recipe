package com.bilev.common.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionHandler implements ExceptionMapper<ApplicationException> {

    @Override
    public Response toResponse(final ApplicationException exception) {
        return Response.status(Status.BAD_REQUEST)
                .entity(ExceptionDto.builder()
                        .exceptionCode(exception.getExceptionCode())
                        .message(exception.getMessage())
                        .build()).build();
    }
}
