package spring.handson.homework8.repo;

import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import spring.handson.homework8.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

/**
 * class for storing all the vehicles created from factory 1 and factory 2
 */
@Repository
@Getter
@Scope("singleton")
public class VehicleInventoryStore {
    private final List<Vehicle> allVehiclesList;

    public VehicleInventoryStore() {
        this.allVehiclesList = new ArrayList<>();
    }

    public void addVehicle(Vehicle myVehicle) {
        allVehiclesList.add(myVehicle);
    }

    public void deleteVehicle(int index) {
        allVehiclesList.remove(index);
    }
}

