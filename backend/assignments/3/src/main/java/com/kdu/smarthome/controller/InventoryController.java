package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.InventoryRequest;
import com.kdu.smarthome.entity.Device;
import com.kdu.smarthome.service.InventoryService;
import com.kdu.smarthome.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller class for handling inventory-related endpoints.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    /**
     * Displays the inventory.
     *
     * @return ResponseEntity containing the response with the inventory details.
     */
    @GetMapping
    public ResponseEntity<String> displayInventory() {
        List<Device> deviceList = inventoryService.displayInventory();
        return ResponseEntity.ok(ResponseBuilder.displayInventoryResponse(deviceList));
    }

    /**
     * Adds an item to the inventory.
     *
     * @param myDto InventoryRequest instance containing data for adding an item to the inventory.
     * @return ResponseEntity containing the response with added item details.
     */
    @PostMapping
    public ResponseEntity<String> addItemToInventory(@RequestBody @Valid InventoryRequest myDto) {
        Device myDevice = inventoryService.addItemToInventory(myDto);
        return ResponseEntity.ok(ResponseBuilder.addDeviceToInventoryResponse(myDevice));
    }
}
