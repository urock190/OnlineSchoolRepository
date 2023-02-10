package com.academy.ServerClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Service {
    public static String readBlacklist() {
        String blacklistPath = "src/com/academy/ServerClient/Blacklist.txt";
        Path path = Path.of(blacklistPath);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String ip;
            while ((ip = br.readLine()) != null) {
                builder.append(ip);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return builder.toString();
    }

    public static void serverClientMenuTitle() {
        System.out.println("You have choose the category \"Server-Client\"");
        System.out.println("""
                                Do you want to start Server-Client? Type "yes" to confirm. Type "no" to choose another category.
                                Type anything else to continue creating lectures.""");
    }
}
