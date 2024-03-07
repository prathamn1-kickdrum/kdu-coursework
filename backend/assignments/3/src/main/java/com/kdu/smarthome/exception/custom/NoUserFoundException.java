package com.kdu.smarthome.exception.custom;

public class NoUserFoundException extends RuntimeException{
    public NoUserFoundException(String msg) {
        super(msg);
    }
}
