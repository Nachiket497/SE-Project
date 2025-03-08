package com.sismics.rest.resource;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sismics.rest.DTO.ErrorResponse;
import com.sismics.reader.core.constant.Constants;


/**
 * Generic exception mapper that transforms all unknown exception into ServerError.
 *
 * @author jtremeaux 
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {
    /**
     * Logger.
     */
	
	// Old Code 
//    private static final Logger log = LoggerFactory.getLogger(GenericExceptionMapper.class);
//
//    @Override
//    public Response toResponse(Exception e)  {
//        if (e instanceof WebApplicationException) {
//            return ((WebApplicationException) e).getResponse();
//        }
//        
//        log.error("Unknown error", e);
//        
//        JSONObject entity = new JSONObject();
//        try {
//            entity.put("type", "UnknownError");
//            entity.put("message", "Unknown server error");
//        } catch (JSONException e2) {
//            log.error("Error building response", e2);
//        }
//        
//        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
//                .entity(entity)
//                .build();
//    }
	
	// New Code
	private static final Logger log = LoggerFactory.getLogger(GenericExceptionMapper.class);

    @Override
    public Response toResponse(Exception e) {
        if (e instanceof WebApplicationException) {
            return ((WebApplicationException) e).getResponse();
        }
        
        log.error("Unknown error", e);
        ErrorResponse entity = new ErrorResponse("UnknownError", Constants.ERROR_UNKNOWN);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(entity)
                .build();
    }
}
