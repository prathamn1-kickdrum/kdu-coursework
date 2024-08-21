package spring.handson.service.vehicle;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.handson.model.vehicle.Vehicle;
import spring.handson.repo.VehicleInventoryStore;
import spring.handson.service.speaker.SpeakerService;
import spring.handson.service.tyre.TyreService;

import java.util.List;

/**
 * class representing factory 1.
 */
@Service
public class VehicleServiceFactory1 extends AbstractVehicleServiceFactory {

    private final VehicleInventoryStore store;
    @Autowired
    public VehicleServiceFactory1(TyreService tyreService, SpeakerService speakerService, VehicleInventoryStore store) {
        super(tyreService, speakerService);
        this.store=store;
    }

    @Override
    protected double getTyreLocationFactor() {
        // 10% increase in price for Factory 1
        return 10;
    }

    @PostConstruct
    @Override
    public List<Vehicle> createVehicleList() {
        List<Vehicle> vehicles = super.createVehicleList();
        store.addVehicles(vehicles);
        return vehicles;
    }
}