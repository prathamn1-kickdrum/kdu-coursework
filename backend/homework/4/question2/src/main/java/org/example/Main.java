package org.example;

import java.util.Collection;

/**
 * The Main class contains the main method to demonstrate the TicketReservation functionality.
 */
public class Main {

    /**
     * The main method demonstrates the booking, cancellation, and display of flight reservations.
     */
    public static void main(String[] args) {
        TicketReservation ticketReservation = new TicketReservation();
        Logger loggerObj = Logger.getLoggerObject();

        // Book flights
        loggerObj.debugLog("Booking Flights:");
        loggerObj.debugLog(ticketReservation.bookFlight("John", "Doe", 30, "Male", "economy", "C1"));
        loggerObj.debugLog(ticketReservation.bookFlight("Jane", "Doe", 25, "Female", "business", "C2"));
        loggerObj.debugLog(ticketReservation.bookFlight("Alice", "Smith", 22, "Female", "economy", "C3"));
        loggerObj.debugLog(ticketReservation.bookFlight("Bob", "Johnson", 45, "Male", "economy", "C4"));
        loggerObj.debugLog(ticketReservation.bookFlight("Charlie", "Brown", 55, "Male", "economy", "C5"));
        loggerObj.debugLog(ticketReservation.bookFlight("Eva", "Williams", 28, "Female", "economy", "C6"));
        loggerObj.debugLog(ticketReservation.bookFlight("Frank", "Miller", 40, "Male", "economy", "C7"));
        loggerObj.debugLog(ticketReservation.bookFlight("Grace", "Davis", 32, "Female", "economy", "C8"));
        loggerObj.debugLog(ticketReservation.bookFlight("Henry", "Clark", 38, "Male", "economy", "C9"));
        loggerObj.debugLog(ticketReservation.bookFlight("Ivy", "Moore", 26, "Female", "economy", "C10"));
        loggerObj.debugLog(ticketReservation.bookFlight("John", "Smith", 50, "Male", "economy", "C11"));

        // Display confirmedList and waitingList
        loggerObj.debugLog("\nConfirmed List:");
        displayPassengerList(ticketReservation.getConfirmedList());

        loggerObj.debugLog("\nWaiting List:");
        displayPassengerList(ticketReservation.getWaitingList());

        // Cancel a flight
        loggerObj.debugLog("\nCancelling Flight with Confirmation Number C3:");
        loggerObj.debugLog(ticketReservation.cancel("C3"));

        // Display confirmedList and waitingList after cancellation
        loggerObj.debugLog("\nConfirmed List:");
        displayPassengerList(ticketReservation.getConfirmedList());

        loggerObj.debugLog("\nWaiting List:");
        displayPassengerList(ticketReservation.getWaitingList());
    }

    /**
     * Displays the details of passengers in a collection.
     */
    private static void displayPassengerList(Collection<Passenger> passengers) {
        Logger loggerObj = Logger.getLoggerObject();
        for (Passenger passenger : passengers) {
            loggerObj.debugLog(passenger.toString());
        }
    }
}
