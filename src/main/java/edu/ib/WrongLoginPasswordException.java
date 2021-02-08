package edu.ib;

/**
 * Custom error class, thrown when login operation failed, because of wrong login or password
 */

public class WrongLoginPasswordException extends Exception {

    /**
     * Basic constructor
     *
     * @param message Message of error
     */
    public WrongLoginPasswordException(String message) {
        super(message);
    }

}
