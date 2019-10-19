package org.poem.exception;

/**
 * @author poem
 */
public class ApiCenterProviderException extends Exception{

    public ApiCenterProviderException(String message) {
        super(message);
    }



    public ApiCenterProviderException(String message, Throwable cause) {
        super(message, cause);
    }
}
