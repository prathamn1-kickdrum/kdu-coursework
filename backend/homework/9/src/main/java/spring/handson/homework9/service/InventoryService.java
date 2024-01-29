package spring.handson.homework9.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.handson.homework9.dto.VehicleDto;
import spring.handson.homework9.exceptions.custom.VehicleListEmptyException;
import spring.handson.homework9.mapper.VehicleDtoMapper;
import spring.handson.homework9.model.Vehicle;
import spring.handson.homework9.repo.VehicleInventoryStore;


import java.util.Comparator;

/**
 * contains functions related to inventory
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SuppressWarnings("all")
public class InventoryService {
    private final VehicleInventoryStore store;
    private final VehicleDtoMapper mapper;

    /**
     * Uses Java streams and max function to get the most priced vehicle
     */
    public VehicleDto getMostPricedVehicle() throws VehicleListEmptyException {
        if (store.getAllVehiclesList().isEmpty()) {
            throw new VehicleListEmptyException("Error! No vehicle in store.");
        }
        Vehicle miniPriced = store.getAllVehiclesList().stream().max(Comparator.comparingDouble(Vehicle::getTotalPrice))
                .orElse(null);
        return mapper.vehicleToDto(miniPriced);
    }
    /**
     * Uses Java streams and min function to get the least priced vehicle
     */
    public VehicleDto getLeastPricedVehicle() throws VehicleListEmptyException {
        if (store.getAllVehiclesList().isEmpty()) {
            throw new VehicleListEmptyException("Error! No vehicle in store.");
        }
        Vehicle miniPriced = store.getAllVehiclesList().stream().min(Comparator.comparingDouble(Vehicle::getTotalPrice))
                .orElse(null);
        return mapper.vehicleToDto(miniPriced);

    }

}

