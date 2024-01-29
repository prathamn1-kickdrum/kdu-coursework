package spring.handson.homework9.service;

import spring.handson.homework9.dto.VehicleDto;

public interface VehicleFactoryInterface {
    void addVehicle(VehicleDto myDto);
    void deleteVehicle(int index);
    void updateVehicle(int index, VehicleDto myDto);
    VehicleDto getVehicle(int index);

    }
