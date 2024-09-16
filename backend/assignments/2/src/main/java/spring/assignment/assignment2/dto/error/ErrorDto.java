package spring.assignment.assignment2.dto.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorDto {
    private String message;
    private int code;
}
