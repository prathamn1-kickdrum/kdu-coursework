package org.example.insurance.insurancePlan;

public class PlatinumPlan extends HealthInsurancePlan {
    public PlatinumPlan() {
        setCoverage(0.9);
    }
    @Override
    public double computeMonthlyPremium(double salary) {
        return 0.08 * salary;
    }
    @Override
    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        return 0.08 * salary + getOfferedBy().computeMonthlyPremium(this, age, smoking);
    }
}
