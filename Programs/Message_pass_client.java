/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.socket_programming;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Message_pass_client {
    public static void main(String[] args) {
        try {
            System.out.println("Client Started");
            Socket socket = new Socket("localhost", 9111);

            // Get input from user
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the String in Lower Case:");
            String msg = scanner.nextLine();

            // Send message to server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(msg);

            // Receive response from server
            InputStream inputStream = socket.getInputStream();
            Scanner serverScanner = new Scanner(inputStream);
            if (serverScanner.hasNextLine()) {
                String msg2 = serverScanner.nextLine();
                System.out.println("Message in UpperCase from Server: " + msg2);
            } else {
                System.out.println("No response from server.");
            }

            // Close resources
            scanner.close();
            serverScanner.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}
