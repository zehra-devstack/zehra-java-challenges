package com.zehra.java.challenges.calendar;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zehra.java.challenges.calendar.domain.Room;

public class RoomConfig {

    private static Map<String, Room> rooms;
    private static List<String> workHours = Arrays.asList("0001", "0102", "0203", "0304", "0405", "0506", "0607",
        "0708", "0809", "0910", "1011", "1112", "1213", "1314", "1415", "1516", "1617", "1718", "1819", "1920", "2021",
        "2122", "2223", "2324");

    static {
        rooms = new HashMap<>();
        rooms.put("R1", new Room("R1", 2, workHours));
    }

    private RoomConfig() {
    }

    public static Room getRoom(String roomName) {
        return rooms.get(roomName);
    }

    public static boolean existRoom(String roomName) {
        return rooms.containsKey(roomName);
    }

}
