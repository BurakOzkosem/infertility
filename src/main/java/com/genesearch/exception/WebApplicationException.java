package com.genesearch.exception;

/**
 * Created with IntelliJ IDEA.
 * User: skiselev
 * Date: 28.10.13
 * Time: 17:10
 * To change this template use File | Settings | File Templates.
 */
public class WebApplicationException extends RuntimeException {
    private final int status;
    private final String message;

    public WebApplicationException() {
        this.status = 0;
        this.message = "";
    }


    public WebApplicationException(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public WebApplicationException(Throwable cause, int status, String message) {
        super(cause);
        this.status = status;
        this.message = message;
    }

    public WebApplicationException(Throwable cause, int status) {
        this(cause, status, "");
    }

    public WebApplicationException(int status) {
        this(status, "");
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
