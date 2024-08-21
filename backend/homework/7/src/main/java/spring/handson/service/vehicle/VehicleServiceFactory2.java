package spring.handson.service.vehicle;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.handson.model.vehicle.Vehicle;
import spring.handson.repo.VehicleInventoryStore;
import spring.handson.service.speaker.SpeakerService;
import spring.handson.service.tyre.TyreService;

import java.util.List;

@Service
public class VehicleServiceFactory2 extends AbstractVehicleServiceFactory {

    private final VehicleInventoryStore store;
    @Autowired
    public VehicleServiceFactory2(TyreService tyreService, SpeakerService speakerService, VehicleInventoryStore store) {
        super(tyreService, speakerService);
        this.store=store;
    }

    @Override
    protected double getTyreLocationFactor() {
        // no increase in price for Factory 2
        return 0;
    }

    @PostConstruct
    @Override
    public List<Vehicle> createVehicleList() {
        List<Vehicle> vehicles = super.createVehicleList();
        store.addVehicles(vehicles);
        return vehicles;
    }
}