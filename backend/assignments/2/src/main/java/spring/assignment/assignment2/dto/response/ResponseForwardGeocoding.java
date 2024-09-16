package spring.assignment.assignment2.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponseForwardGeocoding {
    private double latitude;
    private double longitude;
}
