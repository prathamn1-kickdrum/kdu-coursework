package spring.assignment.assignment2.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RequestReverseGeoCodingDto {
    private String latitude;
    private String longitude;
}
