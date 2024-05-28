/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.socket_programming.Selective_repeat;

/**
 *
 * @author tanir
 */
import java.io.*;
import java.net.*;
import java.util.*;

public class sr_sender {
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
            int[] base = {0}; // Using an array to encapsulate the base variable
            int nextSeqNum = 0;
            boolean[] ackReceived = new boolean[WINDOW_SIZE];

            Timer[] timers = new Timer[WINDOW_SIZE];
            for (int i = 0; i < WINDOW_SIZE; i++) {
                ackReceived[i] = false;
                final int seqNum = i;
                timers[i] = new Timer();
                timers[i].scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        synchronized (base) {
                            if (!ackReceived[seqNum]) {
                                int end = Math.min((base[0] + seqNum + 1) * PACKET_SIZE, length);
                                if ((base[0] + seqNum) * PACKET_SIZE < length) {
                                    String packet = (base[0] + seqNum) + ":" + message.substring((base[0] + seqNum) * PACKET_SIZE, end);
                                    out.println(packet);
                                    System.out.println("Resent: " + packet);
                                }
                            }
                        }
                    }
                }, 1000, 1000);
            }

            while (base[0] < length) {
                while (nextSeqNum < base[0] + WINDOW_SIZE && nextSeqNum * PACKET_SIZE < length) {
                    int end = Math.min((nextSeqNum + 1) * PACKET_SIZE, length);
                    String packet = nextSeqNum + ":" + message.substring(nextSeqNum * PACKET_SIZE, end);
                    out.println(packet);
                    System.out.println("Sent: " + packet);
                    nextSeqNum++;
                }

                try {
                    Thread.sleep(1000); // Simulate network delay
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted: " + e.getMessage());
                }

                if (random.nextDouble() > ACK_LOSS_PROBABILITY) {
                    String ack = in.readLine();
                    if (ack != null && ack.startsWith("ACK:")) {
                        int ackNum = Integer.parseInt(ack.split(":")[1]);
                        synchronized (base) {
                            if (ackNum >= base[0] && ackNum < base[0] + WINDOW_SIZE) {
                                ackReceived[ackNum - base[0]] = true;
                                System.out.println("Received ACK for packet: " + ackNum);

                                while (base[0] < length && ackReceived[0]) {
                                    System.arraycopy(ackReceived, 1, ackReceived, 0, WINDOW_SIZE - 1);
                                    ackReceived[WINDOW_SIZE - 1] = false;
                                    base[0]++;
                                }
                            }
                        }
                    }
                } else {
                    System.out.println("Simulated loss of ACK");
                }
            }

            for (Timer timer : timers) {
                timer.cancel();
            }

            socket.close();
            System.out.println("Sender closed.");
        } catch (Exception e) {
            System.out.println("Sender Error: " + e.getMessage());
        }
    }
}