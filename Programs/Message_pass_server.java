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
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connection Established with Client");
            Scanner scanner = new Scanner(clientSocket.getInputStream());
            if (scanner.hasNextLine()) {
                String clientMessage = scanner.nextLine();
                System.out.println("Message received from client: " + clientMessage);
                String upperCaseMessage = clientMessage.toUpperCase();
                OutputStream outputStream = clientSocket.getOutputStream();
                PrintWriter out = new PrintWriter(outputStream, true);
                out.println(upperCaseMessage);
                System.out.println("Sent uppercase message back to client: " + upperCaseMessage);
                out.close();
                outputStream.close();
            }
            scanner.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}
