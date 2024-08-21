package spring.handson1.services;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.handson1.entity.Speaker;
import spring.handson1.entity.Tyre;
import spring.handson1.entity.Vehicle;
import spring.handson1.logging.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;


/**
 * This class creates a list of different Vehicle objects.
 * Has functions to print the most expensive vehicle from the list
 * Managed By spring
 */
@Service
public class VehicleService {
    Random rand;
    Logger loggerObj;
    List<Vehicle> vehicleList;
    VehicleService() {
        loggerObj = Logger.getLoggerObject();
        vehicleList = new ArrayList<>();
        rand = new Random();
    }
    @Autowired
    TyreService tyreService;
    @Autowired
    SpeakerService speakerService;

    /**
     * It creates the vehicle object and returns it
     * @param speaker the speaker of vehicle
     * @param tyre the tyre object of vehicle
     * @param basePrice the base price of vehicle
     * @return Vehicle
     */
    Vehicle generateVehicle(Speaker speaker,Tyre tyre, double basePrice) {
        Vehicle vehicle = new Vehicle();
        vehicle.setBasePrice(basePrice);
        vehicle.setSpeaker(speaker);
        vehicle.setTyre(tyre);
        return vehicle;
    }

    /**
     * It uses random class to create permutations of vehicles with different speakers and tyres.
     */
    @PostConstruct
    public void generateVehiclesList() {
        Speaker speaker;
        Tyre tyre;
        for (int i = 0; i < 5; i++) {
            if (rand.nextInt() % 2 == 0) {
                speaker = speakerService.generateSonySpeaker();
            } else {
                speaker = speakerService.generateBoseSpeaker();
            }
            if (rand.nextInt() % 2 == 0) {
                tyre = tyreService.generateBridgestoneTyre();
            } else {
                tyre = tyreService.generateMrfTyre();
            }
            vehicleList.add(generateVehicle(speaker, tyre, rand.nextInt(10) * 100000));
        }
    }


    /**
     * Utility function which prints the details of most expensive vehicle after fetching it.
     * Also throws the NULL pointer exception if due to some error the vehicle list remains NULL.
     */
    public void printMostExpensiveVehicle() {
        Vehicle expensiveVehicle = getMostExpensiveVehicle();
        if(expensiveVehicle==null) {
            throw new NullPointerException("Error ! expensiveVehicle object found null. No vehicles generated !");
        }
        loggerObj.infoLog("Most Expensive Vehicle Details : ");
        loggerObj.infoLog("Price : "+expensiveVehicle.getTotalPrice());
        loggerObj.infoLog("Tyre : "+expensiveVehicle.getTyre().getBrand());
        loggerObj.infoLog("Speaker : "+expensiveVehicle.getSpeaker().getBrand());


    }

    /**
     * Uses Java streams and max function to get the most expensive vehicle
     * @return Vehicle which is most expensive by price
     */
    private Vehicle getMostExpensiveVehicle() {
        return vehicleList.stream().max(Comparator.comparingDouble(Vehicle::getTotalPrice)).orElse(null);
    }
}
