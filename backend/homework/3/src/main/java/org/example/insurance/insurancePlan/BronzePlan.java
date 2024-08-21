package org.example.insurance.insurancePlan;

public class BronzePlan extends HealthInsurancePlan {
    public BronzePlan() {
        setCoverage(0.9);
    }
    @Override
    public double computeMonthlyPremium(double salary) {
        return 0.05 * salary;
    }
    @Override
    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        return 0.05 * salary + getOfferedBy().computeMonthlyPremium(this, age, smoking);
    }
}
