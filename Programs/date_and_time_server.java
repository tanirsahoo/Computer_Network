/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.socket_programming;

import java.io.*;
import java.net.*;

public class date_and_time_server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9000);
            System.out.println("Server started. Waiting for client...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);
                OutputStream outputStream = clientSocket.getOutputStream();
                PrintWriter pt = new PrintWriter(outputStream, true);
                String dateTime = java.time.LocalDateTime.now().toString();
                pt.println(dateTime);
                pt.close();
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
