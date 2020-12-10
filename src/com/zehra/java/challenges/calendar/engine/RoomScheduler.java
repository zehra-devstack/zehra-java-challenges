package com.zehra.java.challenges.calendar.engine;

import java.util.HashMap;
import java.util.Map;

import com.zehra.java.challenges.calendar.RoomConfig;
import com.zehra.java.challenges.calendar.domain.Booking;
import com.zehra.java.challenges.calendar.domain.NotAvailabilityException;

public class RoomScheduler {

    private static RoomScheduler instance;

    private static Map<String, Map<String, Map<String, Integer>>> calendar = new HashMap<>();

    private RoomScheduler() {

    }

    public static RoomScheduler getInstance() {
        if (instance == null) {
            instance = new RoomScheduler();
        }
        return instance;
    }

    public void schedule(Booking booking) {
        // Validates if there is availability
        if (!RoomConfig.existRoom(booking.getRoomName())
            || !RoomConfig.getRoom(booking.getRoomName()).isGoodToTake(booking.getTime(), booking.getSeats())) {
            throw new NotAvailabilityException();
        }

        // Date slot's not been occupied
        if (!calendar.containsKey(booking.getDate())) {
            // Create date slot
            addDateSlot(booking);
        } else {
            // Time slot's not been occupied
            if (!calendar.get(booking.getDate()).containsKey(booking.getTime())) {
                // Create time slot into existing date slot
                addTimeSlot(booking, calendar.get(booking.getDate()), new HashMap<>());
            } else {
                // Room slot's not been occupied
                if (!calendar.get(booking.getDate()).get(booking.getTime()).containsKey(booking.getRoomName())) {
                    addRoomSlot(booking, calendar.get(booking.getDate()).get(booking.getTime()),
                        RoomConfig.getRoom(booking.getRoomName()).getSeats());
                } else {
                    int availableSeatsInRoom = calendar.get(booking.getDate()).get(booking.getTime())
                        .get(booking.getRoomName());
                    if (availableSeatsInRoom < booking.getSeats()) {
                        throw new NotAvailabilityException();
                    }
                    updateSeats(booking, calendar.get(booking.getDate()).get(booking.getTime()),
                        availableSeatsInRoom - booking.getSeats());
                }
            }
        }
    }

    private void addDateSlot(Booking booking) {
        Map<String, Map<String, Integer>> timeSlot = new HashMap<>();
        Map<String, Integer> roomSlot = new HashMap<>();
        addTimeSlot(booking, timeSlot, roomSlot);
        calendar.put(booking.getDate(), timeSlot);
    }

    private void addTimeSlot(Booking booking, Map<String, Map<String, Integer>> timeSlot,
        Map<String, Integer> roomSlot) {
        addRoomSlot(booking, roomSlot, RoomConfig.getRoom(booking.getRoomName()).getSeats());
        timeSlot.put(booking.getTime(), roomSlot);
    }

    private void addRoomSlot(Booking booking, Map<String, Integer> roomSlot, int roomSeats) {
        roomSlot.put(booking.getRoomName(), roomSeats - booking.getSeats());
    }

    private void updateSeats(Booking booking, Map<String, Integer> room, int number) {
        room.put(booking.getRoomName(), number);
    }

}
