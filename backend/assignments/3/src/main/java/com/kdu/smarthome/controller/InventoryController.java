package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.InventoryRequest;
import com.kdu.smarthome.dto.response.GeneralResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {
    @GetMapping("/")
    public ResponseEntity<GeneralResponseDto> getItemsList() {
        return null;
    }

    @PostMapping("/")
    public ResponseEntity<GeneralResponseDto> addItemToInventory(@RequestBody InventoryRequest myDto) {
        return null;
    }
}
