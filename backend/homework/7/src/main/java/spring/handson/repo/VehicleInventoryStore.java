package spring.handson.repo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import spring.handson.logging.Logger;
import spring.handson.model.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

/**
 * class for storing all the vehicles created from factory 1 and factory 2
 */
@Repository
@Scope("singleton")
public class VehicleInventoryStore {
    Logger loggerObj;
    private final List<Vehicle> allVehiclesList;

    public VehicleInventoryStore() {
        loggerObj= Logger.getLoggerObject();
        this.allVehiclesList = new ArrayList<>();
    }

    public void addVehicles(List<Vehicle> vehicles) {
        allVehiclesList.addAll(vehicles);
    }
    public List<Vehicle> getAllVehicles() {
        return new ArrayList<>(allVehiclesList);
    }
}

