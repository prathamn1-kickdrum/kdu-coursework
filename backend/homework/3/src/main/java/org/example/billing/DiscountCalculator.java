package org.example.billing;
import org.example.insurance.insurancePlan.*;

public class DiscountCalculator {
    public static double applyDiscount(HealthInsurancePlan insurancePlan, double patientPayment) {
        if (insurancePlan instanceof PlatinumPlan) {
            return patientPayment - 50;
        } else if (insurancePlan instanceof GoldPlan) {
            return patientPayment - 40;
        } else if (insurancePlan instanceof SilverPlan) {
            return patientPayment - 30;
        } else if (insurancePlan instanceof BronzePlan) {
            return patientPayment - 25;
        } else {
            return patientPayment-20;
        }
    }
}
