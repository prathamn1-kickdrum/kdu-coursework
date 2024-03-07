package com.kdu.smarthome.exception.custom;

public class BadLoginCredentialsException extends RuntimeException{
    public BadLoginCredentialsException(String message) {
        super(message);
    }
}
