package spring.handson.homework8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDto {
    private double basePrice;
    private String tyreBrand;
    private double tyrePrice;
    private String speakerBrand;
    private double speakerPrice;
}
