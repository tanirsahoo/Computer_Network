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

public class gbn_receiver {
    private static final int WINDOW_SIZE = 5;
    private static final int PORT = 6565;
    private static final double LOSS_PROBABILITY = 0.2;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Receiver waiting for Sender...");
            Socket socket = serverSocket.accept();
            System.out.println("Receiver connected to Sender.");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String msg = "" ;
            Random random = new Random();

            int expectedSeqNum = 0;

            while (true) {
                String packet = in.readLine();
                if (packet == null || packet.isEmpty()) {
                    break;
                }

                int seqNum = Integer.parseInt(packet.substring(0, packet.indexOf(':')));
                String data = packet.substring(packet.indexOf(':') + 1);

                if (random.nextDouble() > LOSS_PROBABILITY) {
                    if (seqNum == expectedSeqNum) {
                        System.out.println("Received: " + data + " (seq: " + seqNum + ")");
                        msg = msg + data ;
                        expectedSeqNum++;
                        out.println("ACK:" + seqNum);
                    } else {
                        System.out.println("Discarded: " + data + " (seq: " + seqNum + ")");
                        out.println("ACK:" + (expectedSeqNum - 1));
                    }
                } else {
                    System.out.println("Simulated loss of packet: " + data + " (seq: " + seqNum + ")");
                }
            }

            serverSocket.close();
            socket.close();
            System.out.println("Receiver closed.");
            System.out.println("The Data received from Sender: " + msg);
        } catch (Exception e) {
            System.out.println("Receiver Error: " + e.getMessage());
        }
    }
}
