package org.example.insurance.insurancePlan;

import org.example.insurance.insuranceBrand.BlueCrossBlueShield;
import org.example.insurance.insuranceBrand.InsuranceBrand;

public abstract class HealthInsurancePlan {
    private double coverage;
    private InsuranceBrand offeredBy;

    public double getCoverage() {
        return coverage;
    }
    public void setCoverage(double coverage) {
        this.coverage = coverage;
    }
    public InsuranceBrand getOfferedBy() {
        return offeredBy;
    }

    public void setOfferedBy(InsuranceBrand offeredBy) {
        this.offeredBy = offeredBy;
    }
    public abstract double computeMonthlyPremium(double salary);
    public abstract double computeMonthlyPremium(double salary, int age, boolean smoking);
}
