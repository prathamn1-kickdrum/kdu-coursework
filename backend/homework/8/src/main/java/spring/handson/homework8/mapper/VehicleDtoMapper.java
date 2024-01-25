package spring.handson.homework8.mapper;

import org.springframework.stereotype.Service;
import spring.handson.homework8.dto.VehicleDto;
import spring.handson.homework8.model.Speaker;
import spring.handson.homework8.model.Tyre;
import spring.handson.homework8.model.Vehicle;

@Service
public class VehicleDtoMapper {
    public VehicleDto vehicleToDto(Vehicle myVehicle) {
        return new VehicleDto(myVehicle.getPrice()
                ,myVehicle.getTyre().getName()
                ,myVehicle.getTyre().getPrice()
                ,myVehicle.getSpeaker().getName()
                ,myVehicle.getSpeaker().getPrice());
    }

    public Vehicle dtoToVehicle(VehicleDto myDto) {
        return new Vehicle(new Speaker(myDto.getSpeakerPrice(),myDto.getSpeakerBrand()),
                new Tyre(myDto.getTyrePrice(),myDto.getTyreBrand()),
                myDto.getBasePrice());
    }
}
