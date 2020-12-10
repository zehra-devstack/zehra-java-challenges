** Requirements

Java 13 installed (JRE and JDK)

** Run application to create a file

java -classpath booking.jar com.zehra.java.challenges.calendar.util.BookingRequestGenerator bookingGenerated.txt 300

** Run application to process the file

java -classpath booking.jar com.zehra.java.challenges.calendar.engine.FrontDesk bookingGenerated.txt