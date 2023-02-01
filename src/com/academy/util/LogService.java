package com.academy.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class LogService {
    public static void writeLog(Log log) {
        String string = log.toString();
        File file = new File("src/com/academy/util/Log.txt");
        try (FileWriter writer = new FileWriter(file, true)){
            writer.write(string + "\n");
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String readLog() {
        File file = new File("src/com/academy/util/Log.txt");
        char[] buffer = null;
        try (FileReader reader = new FileReader(file)){
            buffer = new char[(int) file.length()];
            reader.read(buffer);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return new String(buffer);
    }

    public static void writeLevelSetting (Level level) {
        String string = level.name();
        File file = new File("src/com/academy/util/setting.txt");
        try (FileWriter writer = new FileWriter(file)){
            writer.write("level = " + string);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Level readLevelSetting() {
        Path path = Path.of("src/com/academy/util/setting.txt");
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
                                Type anything else to continue creating lectures.""");
    }
}
