package spring.assignment.assignment2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LocationEntity {
    private String label;
    private double latitude;
    private double longitude;
    private String postalCode;
    private String region;
    private int number;
}
