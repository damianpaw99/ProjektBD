package edu.ib;

public class WrongLoginPasswordException extends Exception {

    public WrongLoginPasswordException(String message){
        super(message);
    }

    public WrongLoginPasswordException(String message, Throwable cause){
        super(message,cause);
    }
}
