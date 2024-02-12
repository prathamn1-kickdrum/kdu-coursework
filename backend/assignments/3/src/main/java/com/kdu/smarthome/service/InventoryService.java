package com.kdu.smarthome.service;

import com.kdu.smarthome.dto.request.InventoryRequest;
import com.kdu.smarthome.entity.Device;

import java.util.List;

public interface InventoryService {
    Device addItemToInventory(InventoryRequest myDto);

    List<Device> displayInventory();
}
