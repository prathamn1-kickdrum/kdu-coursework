package spring.handson.model.vehicle;

import spring.handson.model.speaker.AbstractSpeaker;
import spring.handson.model.tyre.AbstractTyre;

/**
 * class for vehicle model
 */
public class Vehicle {
    private AbstractTyre tyre;
    private AbstractSpeaker speaker;
    private double basePrice;

    public AbstractTyre getTyre() {
        return tyre;
    }

    public void setTyre(AbstractTyre tyre) {
        this.tyre = tyre;
    }

    public AbstractSpeaker getSpeaker() {
        return speaker;
    }

    public void setSpeaker(AbstractSpeaker speaker) {
        this.speaker = speaker;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    /**
     *
     * @param tyre can be mrf or bridgestone object
     * @param speaker can be bose or sony speaker
     * @param basePrice base price of vehicle
     */
    public Vehicle(AbstractTyre tyre, AbstractSpeaker speaker, double basePrice) {
        this.tyre = tyre;
        this.speaker = speaker;
        this.basePrice = basePrice;
    }

    /**
     *
     * @return total price of vehicle
     */
    public double getTotalPrice() {
        return basePrice+ speaker.getPrice()+tyre.getPrice();
    }
}
