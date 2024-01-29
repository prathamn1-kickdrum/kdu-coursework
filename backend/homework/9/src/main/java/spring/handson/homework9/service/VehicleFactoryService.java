package spring.handson.homework9.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.handson.homework9.dto.VehicleDto;
import spring.handson.homework9.mapper.VehicleDtoMapper;
import spring.handson.homework9.model.Vehicle;
import spring.handson.homework9.repo.VehicleInventoryStore;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VehicleFactoryService implements VehicleFactoryInterface{

    private final VehicleInventoryStore store;
    private final VehicleDtoMapper mapper;
    @Override
    public void addVehicle(VehicleDto myDto) {
        store.addVehicle(mapper.dtoToVehicle(myDto));
    }
    @Override
    public void deleteVehicle(int index) {
        store.deleteVehicle(index);
    }
    @Override
    public void updateVehicle(int index,VehicleDto myDto) {
        Vehicle myVehicle = mapper.dtoToVehicle(myDto);
        store.getAllVehiclesList().set(index,myVehicle);
    }
    @Override
    public VehicleDto getVehicle(int index) {
        Vehicle myVehicle = store.getAllVehiclesList().get(index);
        return mapper.vehicleToDto(myVehicle);
    }
}
