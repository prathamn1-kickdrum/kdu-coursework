package spring.handson1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.handson1.config.AppConfig;
import spring.handson1.logging.Logger;
import spring.handson1.services.VehicleService;

/**
 * Entry point of program
 */
public class Main {
    /**
     * Creates spring context for full application and then prints the most expensive vehicle.
     * Also catches the NUll exception thrown if no vehicle is present in the list.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Logger loggerObj = Logger.getLoggerObject();
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        VehicleService service = context.getBean(VehicleService.class);
        try {
            service.printMostExpensiveVehicle();
        } catch (NullPointerException e) {
            loggerObj.errorLog(e.getMessage(),e);
        }


    }
}