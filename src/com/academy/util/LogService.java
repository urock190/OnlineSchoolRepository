package com.academy.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

public class LogService {
    private static final String logFilePath = "src/com/academy/util/Log.txt";
    private static final String settingFilePath = "src/com/academy/util/setting.txt";
    public static void writeLog(Log log) {
        String string = log.toString();
        File file = new File(logFilePath);
        try (FileWriter writer = new FileWriter(file, true)){
            writer.write(string + "\n");
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String readLog() {
        File file = new File(logFilePath);
        char[] buffer = null;
        try (FileReader reader = new FileReader(file)){
            buffer = new char[(int) file.length()];
            reader.read(buffer);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        final Optional<char[]> buf = Optional.ofNullable(buffer);
        return buf.map(String::new).orElse("There are no logs in the file. No log file found.");
    }

    public static void readMessagesOnly(){
        System.out.println("==========================\nAll messages from the log file:");
        try {
            Files.lines(Path.of(logFilePath)).forEach(line -> Arrays.stream(line.split(";")).
                    filter(part -> part.contains("message - ")).forEach(message ->
                                    System.out.println(message.substring(message.indexOf('\"'), message.lastIndexOf('\"')+1))
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println();
    }

    public static void readInfoFromTheMiddle(){
        try {
            int linesNumber = (int) Files.lines(Path.of(logFilePath)).count();
            int infosFromTheMiddle = (int) Files.lines(Path.of(logFilePath)).skip(linesNumber/2).filter(line ->
                    line.contains("level - INFO")).count();
            System.out.println("The number of INFO-logs starting from the middle of the file - " + infosFromTheMiddle);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    public static void writeLevelSetting (Level level) {
        String string = level.name();
        File file = new File(settingFilePath);
        try (FileWriter writer = new FileWriter(file)){
            writer.write("level = " + string);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Level readLevelSetting() {
        Path path = Path.of(settingFilePath);
        Level level = Level.OFF;
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String lvl = br.readLine();
            if (lvl.startsWith("level = "))
                level = Level.valueOf(lvl.substring(8));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return level;
    }

    public static void logMenuTitle() {
        System.out.println("You have choose the category \"Log\"");
        System.out.println("""
                                Do you want to print log in console? Type "yes" to confirm. Type "no" to choose another category.
                                Enter "1" to display messages only.
                                Type anything else to continue creating lectures.""");
    }
}
