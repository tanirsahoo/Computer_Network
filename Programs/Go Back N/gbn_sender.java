
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.socket_programming.Go_back_n;

/**
 *
 * @author tanir
 */
import java.io.*;
import java.net.*;
import java.util.Random;

public class gbn_sender {
    private static final int PACKET_SIZE = 1;
    private static final int WINDOW_SIZE = 5;
    private static final int PORT = 6565;
    private static final String HOST = "localhost";
    private static final double ACK_LOSS_PROBABILITY = 0.2; // 20% chance to lose an ACK

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println("Sender connected to Receiver.");
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Random random = new Random();

            System.out.print("Enter your message: ");
            String message = userInput.readLine();
            int length = message.length();
            int base = 0;
            int nextSeqNum = 0;

            while (base < length) {
                while (nextSeqNum < base + WINDOW_SIZE && nextSeqNum * PACKET_SIZE < length) {
                    int end = Math.min((nextSeqNum + 1) * PACKET_SIZE, length);
                    String packet = nextSeqNum + ":" + message.substring(nextSeqNum * PACKET_SIZE, end);
                    out.println(packet);
                    System.out.println("Sent: " + packet);
                    nextSeqNum++;
                }

                try {
                    Thread.sleep(0000); // Simulate network delay
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted: " + e.getMessage());
                }

                if (random.nextDouble() > ACK_LOSS_PROBABILITY) {
                    String ack = in.readLine();
                    if (ack != null && ack.startsWith("ACK:")) {
                        int ackNum = Integer.parseInt(ack.split(":")[1]);
                        if (ackNum >= base) {
                            base = ackNum + 1;
                            System.out.println("Received ACK for packet: " + ackNum);
                        }
                        else{
                            nextSeqNum = base ;
                        }
                    }
                } else {
                    System.out.println("Simulated loss of ACK");
                }
            }

            socket.close();
            System.out.println("Sender closed.");
        } catch (Exception e) {
            System.out.println("Sender Error: " + e.getMessage());
        }
    }
}