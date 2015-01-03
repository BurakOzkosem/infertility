package com.genesearch.exception;

/**
 * Created with IntelliJ IDEA.
 * User: skiselev
 * Date: 28.10.13
 * Time: 17:12
 * To change this template use File | Settings | File Templates.
 */
public class NotFoundException extends WebApplicationException {

    public NotFoundException() {
        super(404);
    }

    public NotFoundException(String message) {
        super(404, message);
    }

}
