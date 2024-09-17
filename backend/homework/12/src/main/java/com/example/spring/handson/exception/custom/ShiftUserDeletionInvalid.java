package com.example.spring.handson.exception.custom;

import com.sun.jdi.request.InvalidRequestStateException;

public class ShiftUserDeletionInvalid extends InvalidRequestStateException {
    public ShiftUserDeletionInvalid(String message){
        super(message);
    }
}
