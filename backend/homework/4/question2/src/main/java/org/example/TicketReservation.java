package org.example;

import java.util.*;

/**
 * The TicketReservation class manages the booking and cancellation of flight reservations.
 * It maintains lists of confirmed passengers and a waiting list when the confirmed list is full.
 */
public class TicketReservation {

    // Maximum number of passengers allowed in the confirmed list
    private static final int CONFIRMED_LIST_LIMIT = 10;

    // Maximum number of passengers allowed in the waiting list
    private static final int WAITING_LIST_LIMIT = 3;

    // List of confirmed passengers
    private final List<Passenger> confirmedList = new ArrayList<>();

    // Queue of passengers on the waiting list
    private final Deque<Passenger> waitingList = new ArrayDeque<>();

    /**
     * Attempts to book a flight for a passenger with the given details.
     */
    public boolean bookFlight(String firstName, String lastName, int age, String gender,
                              String travelClass, String confirmationNumber) {
        Passenger passenger = new Passenger(firstName, lastName, age, gender, travelClass, confirmationNumber);

        if (confirmedList.size() < CONFIRMED_LIST_LIMIT) {
            // If confirmedList has space, add the passenger directly
            confirmedList.add(passenger);
            return true;
        } else if (waitingList.size() < WAITING_LIST_LIMIT) {
            // If confirmedList is full but waitingList has space, add the passenger to waitingList
            waitingList.addLast(passenger);
            return true;
        } else {
            // Both confirmedList and waitingList are full, booking failed
            return false;
        }
    }

    /**
     * Cancels a flight reservation based on the provided confirmation number.
     */
    public boolean cancel(String confirmationNumber) {
        // Remove the passenger from confirmedList or waitingList
        boolean removed = removePassenger(confirmedList.iterator(), confirmationNumber);
        if (!removed) {
            removed = removePassenger(waitingList.iterator(), confirmationNumber);
        }

        // If removed from confirmedList, move from waitingList to confirmedList
        if (removed && confirmedList.size() < CONFIRMED_LIST_LIMIT && !waitingList.isEmpty()) {
            confirmedList.add(waitingList.pollFirst());
        }

        return removed;
    }

    /**
     * Removes a passenger with a specified confirmation number from the given list.
     */
    public boolean removePassenger(Iterator<Passenger> iterator, String confirmationNumber) {
        while (iterator.hasNext()) {
            Passenger passenger = iterator.next();
            if (passenger.getConfirmationNumber().equals(confirmationNumber)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * Gets a copy of the confirmed passenger list.
     */
    public Collection<Passenger> getConfirmedList() {
        return new ArrayList<>(confirmedList);
    }

    /**
     * Gets a copy of the waiting list.
     */
    public Collection<Passenger> getWaitingList() {
        return new ArrayList<>(waitingList);
    }
}
