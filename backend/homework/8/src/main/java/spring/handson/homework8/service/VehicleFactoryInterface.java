package spring.handson.homework8.service;

import spring.handson.homework8.dto.VehicleDto;

public interface VehicleFactoryInterface {
    void addVehicle(VehicleDto myDto);
    void deleteVehicle(int index);
    void updateVehicle(int index, VehicleDto myDto);
    VehicleDto getVehicle(int index);

    }
