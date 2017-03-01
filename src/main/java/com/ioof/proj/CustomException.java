package com.ioof.proj;

/**
 * CustomException used to point error cases
 * 
 * @author ratha
 */
public class CustomException extends Exception {

    private static final long serialVersionUID = 1L;

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable e) {
        super(message, e);
    }

}
