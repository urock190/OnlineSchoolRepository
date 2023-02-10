package com.academy.ServerClient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {
    public static final int PORT = 5555;
    private static String blacklist;

    public Server() {
        blacklist = Service.readBlacklist();
    }

    public static String getBlacklist() {
        return blacklist;
    }

    public static void setBlacklist(String blacklist) {
        Server.blacklist = blacklist;
    }

    @Override
    public void run() {
        Thread watcher = new Thread(new BlacklistWatcher(), "blacklistWatcher");
        watcher.start();
        ExecutorService ex = Executors.newFixedThreadPool(10);

        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Server is running on port " + PORT);

            while (!server.isClosed()) {
                Socket socket = server.accept();
                if (blacklist.contains(socket.getInetAddress().getHostAddress())){
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    System.out.println("Client is closed, because IP address " + socket.getInetAddress().getHostAddress() + " is blacklisted.");
                    out.println("IP in blacklist.");
                    socket.close();
                }
                else ex.execute(new MyTask(socket));
            }
            ex.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            watcher.interrupt();
            System.out.println("Server is closed.");
        }
    }

    static class MyTask implements Runnable {
        Socket client;
        public MyTask(Socket socket) {
            this.client = socket;
        }

        @Override
        public void run() {
            try (Scanner in = new Scanner(client.getInputStream());
                 PrintWriter out = new PrintWriter(client.getOutputStream(), true)){
                out.println("Welcome");
                String fromClient;

                while (!(fromClient = in.nextLine()).equals("exit")) {
                    out.println("Client's String as bytes array - " + Arrays.toString(fromClient.getBytes()));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    client.close();
                    System.out.println("Client is closed.");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
