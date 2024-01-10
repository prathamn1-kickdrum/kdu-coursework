package org.example.insurance.insurancePlan;

public class GoldPlan extends HealthInsurancePlan {
    public GoldPlan() {
        setCoverage(0.9);
    }
    @Override
    public double computeMonthlyPremium(double salary) {
        return 0.07 * salary;
    }
    @Override
    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        return 0.07 * salary + getOfferedBy().computeMonthlyPremium(this, age, smoking);
    }
}
