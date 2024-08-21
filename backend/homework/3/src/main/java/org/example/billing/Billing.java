package org.example.billing;
import org.example.insurance.insurancePlan.HealthInsurancePlan;
import org.example.management.Patient;
public class Billing {
    public static double[] computePaymentAmount(Patient patient, double amount) {
        double[] payments = new double[2];

        HealthInsurancePlan patientInsurancePlan = patient.getInsurancePlan();

        if (patientInsurancePlan != null) {
            double insuranceCoverage = patientInsurancePlan.getCoverage();
            double insurancePayment = amount * (1 - insuranceCoverage);
            double patientPayment = amount * insuranceCoverage;


            patientPayment = DiscountCalculator.applyDiscount(patientInsurancePlan, patientPayment);

            payments[0] = insurancePayment;
            payments[1] = patientPayment;
        } else {

            payments[1] = amount - 20;
        }

        return payments;
    }
}
