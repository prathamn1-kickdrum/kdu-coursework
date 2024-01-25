package spring.handson.model.speaker;


/**
 * Abstract class for speaker
 */
public abstract class AbstractSpeaker {
    double price;
    String brandName;

    public double getPrice() {
        return price;
    }

    public String getBrandName() {
        return brandName;
    }

    protected AbstractSpeaker(double price, String brandName) {
        this.price = price;
        this.brandName = brandName;
    }
}
