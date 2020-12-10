package com.zehra.java.challenges.calendar.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BookingRequestGenerator {

    private static List<String> hours = Arrays.asList("0001", "0102", "0203", "0304", "0405", "0506", "0607",
        "0708", "0809", "0910", "1011", "1112", "1213", "1314", "1415", "1516", "1617", "1718", "1819", "1920", "2021",
        "2122", "2223", "2324");
    private static List<String> dates = Arrays.asList("15/12/2020", "16/12/2020", "17/12/2020", "18/12/2020",
        "19/12/2020");
    private static List<String> names = Arrays.asList("Gisela", "Juan", "Cesar", "Ramon");

    private Random r = new Random();

    public void createFile(String fileName, int rows) throws IOException {
        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            for (int i = 0; i < rows; i++) {
                writer.write("R1|" + randomDate() + "|" + randomHour() + "|1|" + randomName(i) + "\n");
            }
            writer.flush();
        }
    }

    private String randomHour() {
        return hours.get(r.nextInt(hours.size() - 1));
    }

    private String randomDate() {
        return dates.get(r.nextInt(dates.size() - 1));
    }

    private String randomName(int iteration) {
        return names.get(r.nextInt(names.size() - 1)) + iteration;
    }

    public static void main(String[] args) {
        if (args == null || args.length == 0 || args[0] == null) {
            System.out.println(
                "Use: java -classpath booking.jar com.zehra.java.challenges.calendar.util.BookingRequestGenerator bookingGenerated.txt 300");
            System.err.println("Must specify file to process and iterations");
            System.exit(1);
        }
        try {
            new BookingRequestGenerator().createFile(args[0], Integer.parseInt(args[1]));
        } catch (IOException e) {
            System.err.println("Error in processing file: " + e);
        }
    }
}
