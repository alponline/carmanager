package org.carmanager.veiculo.configuration;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class VeiculoNotFoundEx extends Exception implements ExceptionMapper<VeiculoNotFoundEx> {
    public VeiculoNotFoundEx() {
    }

    public VeiculoNotFoundEx(String message) {
        super(message);
    }

    public VeiculoNotFoundEx(String message, Throwable cause) {
        super(message, cause);
    }


    public Response toResponse(VeiculoNotFoundEx ex) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(ex.getMessage())
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
