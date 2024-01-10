package org.example.main;
import org.example.logging.Logger;
import org.example.management.*;
import org.example.insurance.insuranceBrand.*;
import org.example.insurance.insurancePlan.*;
import java.util.Scanner;


public class Main {

    public static Staff createUser() {
        Staff userObj = new Staff();
        UserInputHandler inputHandler = new UserInputHandler();
        Logger loggerObj = new Logger();
        Scanner reader = new Scanner(System.in);

        loggerObj.userPrompt("Enter first name : ");
        String firstName = reader.nextLine();
        userObj.setFirstName(firstName);
        loggerObj.userPrompt("Enter Last name : ");
        String lastName = reader.nextLine();
        userObj.setLastName(lastName);

        int age = inputHandler.readAge();
        userObj.setAge(age);
        double salary = inputHandler.readSalary();
        userObj.setSalary(salary);
        reader.close();
        return userObj;

    }

    public static void main(String[] args) {


        Staff userObj = createUser();


        // Create a health insurance plan (e.g., PlatinumPlan)
        HealthInsurancePlan insurancePlan = new PlatinumPlan();

        // Set the coverage for the health insurance plan
        insurancePlan.setCoverage(0.9);

        // Create an insurance brand (e.g., BlueCrossBlueShield)
        InsuranceBrand insuranceBrand = new BlueCrossBlueShield();

        // Set the insurance brand for the health insurance plan
        insurancePlan.setOfferedBy(insuranceBrand);

        // Set the health insurance plan for the user
        userObj.setInsurancePlan(insurancePlan);



        // Calculate the monthly premium
        double monthlyPremium = insurancePlan.computeMonthlyPremium(userObj.getSalary());

        // Display the result
        System.out.println("Your monthly premium is: " + monthlyPremium);


    }
}

