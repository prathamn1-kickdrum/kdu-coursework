package org.example.insurance.insurancePlan;

public class SilverPlan extends HealthInsurancePlan {
    public SilverPlan() {
        setCoverage(0.9);
    }
    @Override
    public double computeMonthlyPremium(double salary) {
        return 0.06 * salary;
    }
    @Override
    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        // Custom logic for PlatinumPlan
        return 0.06 * salary + getOfferedBy().computeMonthlyPremium(this, age, smoking);
    }
}
