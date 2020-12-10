package com.zehra.java.challenges.calendar.domain;

import java.util.List;

public class Booking {

    private String roomName;
    private String date;
    private String time;
    private int seats;
    private List<String> personNames;

    public Booking() {

    }

    public Booking(String roomName, String date, String time, int seats, List<String> personNames) {
        this.roomName = roomName;
        this.date = date;
        this.time = time;
        this.seats = seats;
        this.personNames = personNames;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getSeats() {
        return seats;
    }

    public List<String> getPersonNames() {
        return personNames;
    }

    public String toString() {
        return roomName + ", " + date + ", " + time + ", " + seats + ", " + personNames;
    }

}
