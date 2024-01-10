package org.example.insurance.insuranceBrand;
import org.example.insurance.insurancePlan.HealthInsurancePlan;

public interface InsuranceBrand {
    double computeMonthlyPremium(HealthInsurancePlan insurancePlan, int age, boolean smoking);
}
