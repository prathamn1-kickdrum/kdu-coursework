package spring.assignment.assignment2.exceptions.custom;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LocationNotFoundException extends RuntimeException{
    private final String message;
}
