package org.example.customexceptions;

public class InvalidDataException extends RuntimeException {

    // chaining-aware constructor
    public InvalidDataException(String message) {
        super(message);
    }
}

