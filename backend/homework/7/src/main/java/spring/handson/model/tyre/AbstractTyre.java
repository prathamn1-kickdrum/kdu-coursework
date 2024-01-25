package spring.handson.model.tyre;

public class AbstractTyre {
    double price;
    String brandName;

    public double getPrice() {
        return price;
    }

    public String getBrandName() {
        return brandName;
    }

    public void updatePrice(double price) {
        this.price=price;
    }

    protected AbstractTyre(double price, String brandName) {
        this.price = price;
        this.brandName = brandName;
    }
}
