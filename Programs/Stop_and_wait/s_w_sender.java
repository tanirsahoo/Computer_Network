/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.socket_programming.Stop_and_Wait;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class s_w_sender{
    private static final int PACKET_SIZE = 5;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 6565);
            System.out.println("Sender connected to Receiver.");
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            String message;
            System.out.print("Enter your message: ");
            message = userInput.readLine();
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner in = new Scanner(socket.getInputStream());
            int index = 0;
            int length = message.length();
            while (index < length) {
                StringBuilder packet = new StringBuilder();
                for (int i = index; i < Math.min(index + PACKET_SIZE, length); i++) {
                    packet.append(message.charAt(i));
                }
                out.println(packet.toString());
                System.out.println("Sent: " + packet.toString());
                String ack = in.nextLine();
                if (ack.equals("ACK")) {
                    System.out.println("Received ACK for packet.");
                    index += PACKET_SIZE;
                }
                Thread.sleep(2000);
            }
            socket.close();
            System.out.println("Sender closed.");
        } catch (Exception e) {
            System.out.println("Sender Error: " + e.getMessage());
        }
    }
}
