package com.zehra.java.challenges.calendar.domain;

import java.util.List;

public class Room {

    private String name;
    private int seats;
    private List<String> availableHours;

    public Room() {

    }

    public Room(String name, int seats, List<String> availableHours) {
        this.name = name;
        this.seats = seats;
        this.availableHours = availableHours;
    }

    public String getName() {
        return name;
    }

    public int getSeats() {
        return seats;
    }

    public List<String> getAvailableHours() {
        return availableHours;
    }

    public boolean isHourAvailable(String time) {
        return this.availableHours.contains(time);
    }

    public boolean hasEnoughSpace(int seats) {
        return this.seats >= seats;
    }

    public boolean isGoodToTake(String time, int seats) {
        return isHourAvailable(time) && hasEnoughSpace(seats);
    }

}
