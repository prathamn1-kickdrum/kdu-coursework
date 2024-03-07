package com.kdu.smarthome.service.impl;

import com.kdu.smarthome.dto.request.InventoryRequest;
import com.kdu.smarthome.entity.Device;
import com.kdu.smarthome.repository.DeviceRepository;
import com.kdu.smarthome.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the InventoryService interface providing methods for managing inventory.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final DeviceRepository deviceRepository;

    /**
     * Adds an item to the inventory.
     *
     * @param myDto The InventoryRequest containing item details.
     * @return The added Device object.
     */
    public Device addItemToInventory(InventoryRequest myDto) {
        Device myDevice = Device.builder()
                .kickstonId(myDto.getKickstonId())
                .devicePassword(myDto.getDevicePassword())
                .deviceUsername(myDto.getDeviceUsername())
                .manufactureFactoryPlace(myDto.getManufactureFactoryPlace())
                .registered(false)
                .manufactureDateTime(myDto.getManufactureDateTime())
                .build();
        return deviceRepository.save(myDevice);
    }

    /**
     * Displays the entire inventory.
     *
     * @return A list of Device objects representing the inventory.
     */
    public List<Device> displayInventory() {
        return deviceRepository.findAll();
    }

}
