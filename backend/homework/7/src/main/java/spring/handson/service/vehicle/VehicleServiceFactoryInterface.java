package spring.handson.service.vehicle;

import spring.handson.model.vehicle.Vehicle;

import java.util.List;

public interface VehicleServiceFactoryInterface {
    List<Vehicle> createVehicleList();
    void modifyPrice(Vehicle vehicle);
}
