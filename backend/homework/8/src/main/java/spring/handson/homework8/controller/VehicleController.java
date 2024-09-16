package spring.handson.homework8.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.handson.homework8.dto.VehicleDto;
import spring.handson.homework8.model.Vehicle;
import spring.handson.homework8.service.InventoryService;
import spring.handson.homework8.service.VehicleFactoryInterface;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/v1")
public class VehicleController {

    private final InventoryService inventoryService;
    private final VehicleFactoryInterface factoryService;
    @PostMapping("/add-vehicle")
    public ResponseEntity<String> addVehicleController(@RequestBody VehicleDto myDto) {
        factoryService.addVehicle(myDto);
        return ResponseEntity.status(200).body("Vehicle added successfully");
    }

    @GetMapping("/get-vehicle/{index}")
    public ResponseEntity<VehicleDto> getVehicleController(@PathVariable int index) {
        VehicleDto myDto = factoryService.getVehicle(index);
        return ResponseEntity.status(200).body(myDto);
    }

    @DeleteMapping("/delete-vehicle/{index}")
    public ResponseEntity<String> deleteVehicleController(@PathVariable int index) {
        factoryService.deleteVehicle(index);
        return ResponseEntity.status(200).body("Vehicle deleted successfully");
    }

    @PutMapping("/update-vehicle/{index}")
    public ResponseEntity<String> updateVehicleController(@PathVariable int index, @RequestBody VehicleDto myDto) {
        factoryService.updateVehicle(index,myDto);
        return ResponseEntity.status(200).body("Vehicle updated Successfully");
    }

    @SneakyThrows
    @GetMapping("/least-priced")
    public ResponseEntity<VehicleDto> getLeastPricedController() {
        VehicleDto leastDto = inventoryService.getLeastPricedVehicle();
        return ResponseEntity.status(200).body(leastDto);
    }

    @SneakyThrows
    @GetMapping("/most-priced")
    public ResponseEntity<VehicleDto> getMostPricedController() {
        VehicleDto mostDto = inventoryService.getMostPricedVehicle();
        return ResponseEntity.status(200).body(mostDto);
    }
}
