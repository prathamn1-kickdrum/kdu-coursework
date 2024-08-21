package spring.handson.service.vehicle;

import org.springframework.stereotype.Service;
import spring.handson.logging.Logger;
import spring.handson.model.vehicle.Vehicle;
import spring.handson.service.speaker.SpeakerService;
import spring.handson.service.tyre.TyreService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public abstract class AbstractVehicleServiceFactory implements VehicleServiceFactoryInterface {
    Logger loggerObj;
    List<Vehicle> vehicleList;
    private final TyreService tyreService;
    private final SpeakerService speakerService;
    private final Random rand;

    protected AbstractVehicleServiceFactory(TyreService tyreService, SpeakerService speakerService) {
        this.tyreService = tyreService;
        this.speakerService = speakerService;
        rand = new Random();
        loggerObj = Logger.getLoggerObject();
        vehicleList= new ArrayList<>();
    }

    /**
     * function implemented by factory classes to set the location factory for price calculation
     */
    protected abstract double getTyreLocationFactor();

    /**
     * creates list of vehicles currently 10
     * @return list<Vehicle>
     */
    @Override
    public List<Vehicle> createVehicleList() {
        int numOfVehicles = 10;
        for (int i = 0; i < numOfVehicles; i++) {
            Vehicle myVehicle = new Vehicle(
                    tyreService.generateRandomTyre(),
                    speakerService.generateRandomSpeaker(),
                    rand.nextInt(100000)
            );
            modifyPrice(myVehicle);
            vehicleList.add(myVehicle);
        }
        return vehicleList;
    }

    /**
     * modifying price of original vehicle generated based on location
     * @param vehicle Vehicle object
     */
    @Override
    public void modifyPrice(Vehicle vehicle) {
        double originalTyrePrice = vehicle.getTyre().getPrice();
        double locationFactor = getTyreLocationFactor();
        vehicle.getTyre().updatePrice(originalTyrePrice * (1 + locationFactor / 100));
    }


}
