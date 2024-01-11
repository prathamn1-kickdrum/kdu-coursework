package org.example;

/**
 * The Passenger class represents information about an airline passenger.
 * It encapsulates details such as name, age, gender, travel class, and confirmation number.
 * This class is designed to be immutable.
 */
public class Passenger {
    private final String firstName;
    private final String lastName;
    private final int age;
    private final String gender;
    private final String travelClass;
    private final String confirmationNumber;

    /**
     * Constructs a Passenger object with the specified details.
     */
    public Passenger(String firstName, String lastName, int age, String gender,
                     String travelClass, String confirmationNumber) {
        // Add validation if needed
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.travelClass = travelClass;
        this.confirmationNumber = confirmationNumber;
    }

    /**
     * Gets the first name of the passenger.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name of the passenger.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the age of the passenger.
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets the gender of the passenger.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Gets the travel class of the passenger.
     */
    public String getTravelClass() {
        return travelClass;
    }

    /**
     * Gets the confirmation number of the passenger.
     */
    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    /**
     * Returns a string representation of the Passenger object.
     */
    @Override
    public String toString() {
        return "Passenger{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", travelClass='" + travelClass + '\'' +
                ", confirmationNumber='" + confirmationNumber + '\'' +
                '}';
    }
}
