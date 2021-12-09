package me.krymz0n.itemremover.util;

import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static me.krymz0n.itemremover.Main.instance;

public class Logging {

    public static void log(String s) { // Logs string into .log file
        try {
            DateTimeFormatter current = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
            LocalDateTime time = LocalDateTime.now();
            FileWriter fileWriter = new FileWriter(instance.thing, true);

            fileWriter.write("" + current.format(time) + ": " + s);
            fileWriter.write("\n");
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
