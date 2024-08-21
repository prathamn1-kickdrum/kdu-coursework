package spring.handson.service.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.handson.exceptions.vehicle.VehicleListEmptyException;
import spring.handson.exceptions.vehicle.VehicleObjectNullException;
import spring.handson.logging.Logger;
import spring.handson.model.vehicle.Vehicle;
import spring.handson.repo.VehicleInventoryStore;
import spring.handson.service.vehicle.VehicleServiceFactory1;
import spring.handson.service.vehicle.VehicleServiceFactory2;

import java.util.Comparator;

/**
 * contains functions related to inventory
 */
@Service
@SuppressWarnings("all")
public class InventoryService {
    private final VehicleInventoryStore store;
    private final VehicleServiceFactory1 factory1;
    private final VehicleServiceFactory2 factory2;
    Logger loggerObj;

    @Autowired
    public InventoryService(VehicleInventoryStore store,VehicleServiceFactory1 factory1, VehicleServiceFactory2 factory2) {
        this.store=store;
        this.factory1=factory1;
        this.factory2=factory2;
        loggerObj = Logger.getLoggerObject();
    }

    /**
     * prints details of single vehicle
     * @param myVehicle
     * @throws VehicleObjectNullException
     */
    public void printVehicle(Vehicle myVehicle) throws VehicleObjectNullException {
        if(myVehicle==null) {
            throw new VehicleObjectNullException();
        }
        loggerObj.infoLog("Price : " + myVehicle.getTotalPrice());
        loggerObj.infoLog("Tyre : " + myVehicle.getTyre().getBrandName());
        loggerObj.infoLog("Speaker : " + myVehicle.getSpeaker().getBrandName());

    }

    /**
     * prints details of all vehicles present in store
     * @throws VehicleListEmptyException
     */
    public void printVehicleStore() throws VehicleListEmptyException {
        if (store.getAllVehicles().isEmpty()) {
            throw new VehicleListEmptyException("Error! No vehicle in store.");
        }
        for(Vehicle myVehicle : store.getAllVehicles()) {
            loggerObj.infoLog("Price : " + myVehicle.getTotalPrice());
            loggerObj.infoLog("Tyre : " + myVehicle.getTyre().getBrandName());
            loggerObj.infoLog("Speaker : " + myVehicle.getSpeaker().getBrandName());
        }
    }


    /**
     * Uses Java streams and max function to get the most priced vehicle
     */
    public void printMostPricedVehicle() throws VehicleListEmptyException {
        try {
            if (store.getAllVehicles().isEmpty()) {
                throw new VehicleListEmptyException("Error! No vehicle in store.");
            }
            Vehicle miniPriced = store.getAllVehicles().stream().max(Comparator.comparingDouble(Vehicle::getTotalPrice))
                    .orElse(null);
            loggerObj.infoLog("Most Priced Vehicle Details : ");
            printVehicle(miniPriced);
        } catch (VehicleObjectNullException e) {
            loggerObj.errorLog("Most priced vehicle object is null",e);
        }
    }
    /**
     * Uses Java streams and min function to get the least priced vehicle
     */
    public void printLeastPricedVehicle() throws VehicleListEmptyException {
        try {
            if (store.getAllVehicles().isEmpty()) {
                throw new VehicleListEmptyException("Error! No vehicle in store.");
            }
            Vehicle miniPriced = store.getAllVehicles().stream().min(Comparator.comparingDouble(Vehicle::getTotalPrice))
                    .orElse(null);
            loggerObj.infoLog("Least Priced Vehicle Details : ");
            printVehicle(miniPriced);
        } catch (VehicleObjectNullException e) {
            loggerObj.errorLog("Least priced vehicle object is null",e);
        }
    }

}

