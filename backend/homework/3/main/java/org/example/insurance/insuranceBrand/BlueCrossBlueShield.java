package org.example.insurance.insuranceBrand;

import org.example.insurance.insurancePlan.HealthInsurancePlan;

public class BlueCrossBlueShield implements InsuranceBrand {
    @Override
    public double computeMonthlyPremium(HealthInsurancePlan insurancePlan, int age, boolean smoking) {

        double premium = 0;

        if (age > 55) {
            premium += 50;
        }

        if (smoking) {
            premium += 30;
        }

        return premium;
    }
}
