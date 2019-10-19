package org.poem.exception;

/**
 * 404 not found
 * @author poem
 */
public class ApiCenterProviderRouteException extends Exception {
    public ApiCenterProviderRouteException(String message) {
        super(message);
    }



    public ApiCenterProviderRouteException(String message, Throwable cause) {
        super(message, cause);
    }
}
