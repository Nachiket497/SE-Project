package com.sismics.rest.exception;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.sismics.rest.DTO.ErrorResponse;
import com.sismics.reader.core.constant.Constants;


/**
 * Jersey exception encapsulating an error from the client (BAD_REQUEST).
 *
 * @author jtremeaux
 */
public class ClientException extends WebApplicationException {
	
    /**
     * Serial UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(ClientException.class);

 // Old Code
    
//    /**
//     * Constructor of ClientException.
//     * 
//     * @param type Error type (e.g. AlreadyExistingEmail, ValidationError)
//     * @param message Human readable error message
//     * @param e Readable error message
//     */
//    public ClientException(String type, String message, Exception e) throws JSONException {
//        this(type, message);
//        log.error(type + ": " + message, e);
//    }
//
//    /**
//     * Constructor of ClientException.
//     * 
//     * @param type Error type (e.g. AlreadyExistingEmail, ValidationError)
//     * @param message Human readable error message
//     */
//    public ClientException(String type, String message) throws JSONException {
//        super(Response.status(Status.BAD_REQUEST).entity(new JSONObject()
//            .put("type", type)
//            .put("message", message)).build());
//    }
    
	
	// New Code

    public ClientException(String type, String message, Exception e) {
        this(type, message);
        log.error(type + ": " + message, e);
    }

    public ClientException(String type, String message) {
        super(Response.status(Status.BAD_REQUEST)
            .entity(new ErrorResponse(type, message))
            .build());
    }
}
