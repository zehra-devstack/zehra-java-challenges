package com.zehra.java.challenges.calendar.engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import com.zehra.java.challenges.calendar.domain.Booking;
import com.zehra.java.challenges.calendar.domain.NotAvailabilityException;

public class FrontDesk {

    public void processBookingRequests(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        RoomScheduler scheduler = RoomScheduler.getInstance();
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            reader.lines().forEach(line -> {
                Booking request = create(line.split("[|]"));
                try {
                    scheduler.schedule(request);
                    System.out.println("Room available for request: " + request);
                } catch (NotAvailabilityException e) {
                    System.out.println("Not availability for request: " + request);
                }
            });

        }
    }

    private Booking create(String[] values) {
        return new Booking(values[0], values[1], values[2], Integer.parseInt(values[3]), Arrays.asList(values[4]));
    }

    public static void main(String args[]) {
        if (args == null || args.length == 0 || args[0] == null) {
            System.out.println(
                "Use: java -classpath booking.jar com.zehra.java.challenges.calendar.engine.FrontDesk booking.txt");
            System.err.println("Must specify file to process");
            System.exit(1);
        }
        try {
            new FrontDesk().processBookingRequests(args[0]);
        } catch (IOException e) {
            System.err.println("Error in processing file: " + e);
        }

    }
}
