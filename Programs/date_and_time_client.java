/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.socket_programming;

/**
 *
 * @author tanir
 */
//import java.io.BufferedReader;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class date_and_time_client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 9000);
            InputStream inputStream = socket.getInputStream();
//            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            Scanner in = new Scanner(inputStream) ;
            String dateTime = in.nextLine();
            System.out.println("Date and Time received from server: " + dateTime);
            in.close();
            socket.close();
        } catch (UnknownHostException e) {
            System.err.println("Host not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error occurred: " + e.getMessage());
        }
    }
}