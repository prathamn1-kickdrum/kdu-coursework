package spring.handson.homework9.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDto {
    @Positive
    @NotNull
    private double basePrice;
    @NotNull
    @NotEmpty
    private String tyreBrand;
    @Positive
    @NotNull
    private double tyrePrice;
    @NotNull
    @NotEmpty
    private String speakerBrand;
    @Positive
    @NotNull
    private double speakerPrice;
}
