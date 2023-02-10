package com.academy.ServerClient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import static com.academy.ServerClient.Server.PORT;

public class Client implements Runnable {

    @Override
    public void run() {
        try (Socket socket = new Socket("127.0.0.1", PORT); Scanner console = new Scanner(System.in);
        Scanner in = new Scanner(socket.getInputStream());
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true)){
            String toServer = " ";
            String access = in.nextLine();
            System.out.println(access);
            if (access.equals("Welcome")) {
                System.out.println("Type \"exit\" to exit the client. Enter the message:");
                while(true){
                    toServer = console.nextLine();
                    out.println(toServer);
                    if(toServer.equals("exit")) break;
                    System.out.println("DATA FROM SERVER: " + in.nextLine());
                }
            }
            out.println(toServer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Exit.");
        }
    }
}
