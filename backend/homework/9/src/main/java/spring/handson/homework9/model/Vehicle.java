package spring.handson.homework9.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * class for vehicle model
 */
@Data
@ToString
@AllArgsConstructor
public class Vehicle {
    private Speaker speaker;
    private Tyre tyre;
    private double price;

    public double getTotalPrice() {
        return this.price+ speaker.getPrice()+tyre.getPrice();
    }


}
