package spring.handson.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.handson.config.AppConfig;
import spring.handson.exceptions.vehicle.VehicleListEmptyException;
import spring.handson.logging.Logger;
import spring.handson.service.inventory.InventoryService;

/**
 * entry point
 */
public class Main {
    /**
     * Creates an application context
     * creates inventory service object in context and prints most priced and least priced vehicle
     * use service.printVehicleStore(); to print all the vehicles in store
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Logger loggerObj = Logger.getLoggerObject();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        InventoryService service = context.getBean(InventoryService.class);
        try {
            service.printLeastPricedVehicle();
            service.printMostPricedVehicle();
        } catch (VehicleListEmptyException e) {
            loggerObj.errorLog("Error! vehicleList empty: No vehicles generated");
        }
        context.close();
    }
}