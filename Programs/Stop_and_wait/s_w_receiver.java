/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.socket_programming.Stop_and_Wait;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class s_w_receiver{
    private static final int PACKET_SIZE = 5;
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6565);
            System.out.println("Receiver waiting for Sender...");
            Socket socket = serverSocket.accept();
            System.out.println("Receiver connected to Sender.");
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            StringBuilder receivedMessage = new StringBuilder();
            while (true) {
                String packet = in.nextLine();
                if (packet == null || packet.isEmpty()) {
                    break;
                }
                receivedMessage.append(packet);
                System.out.println("Received: " + packet);
                out.println("ACK");
            }
            System.out.println("Complete Message Received: " + receivedMessage.toString());
            serverSocket.close();
            socket.close();
            System.out.println("Receiver closed.");
        } catch (Exception e) {
            System.out.println("Receiver Error: " + e.getMessage());
        }
    }
}
