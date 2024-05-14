/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.socket_programming;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Message_pass_server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9111);
            System.out.println("Server started. Waiting for client...");

            // Accept client connection
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connection Established with Client");

            // Create input stream to receive message from client
            Scanner scanner = new Scanner(clientSocket.getInputStream());
            if (scanner.hasNextLine()) {
                String clientMessage = scanner.nextLine();
                System.out.println("Message received from client: " + clientMessage);

                // Convert message to uppercase
                String upperCaseMessage = clientMessage.toUpperCase();

                // Create output stream to send modified message back to client
                OutputStream outputStream = clientSocket.getOutputStream();
                PrintWriter out = new PrintWriter(outputStream, true);

                // Send the modified message (in uppercase) back to client
                out.println(upperCaseMessage);
                System.out.println("Sent uppercase message back to client: " + upperCaseMessage);

                // Close resources
                out.close();
                outputStream.close();
            }

            // Close scanner and socket
            scanner.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}
