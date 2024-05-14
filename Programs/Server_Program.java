/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.socket_programming;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author tanir
 */
public class Server_Program {
    public static void main(String[] args) throws IOException{
    try{
        System.out.println("Waiting for Clients.") ;
        ServerSocket ss = new ServerSocket(9080) ;
        Socket soc = ss.accept();
        System.out.println("Connection Establised");
        Scanner sc = new Scanner(soc.getInputStream()) ;
        String msg2 = sc.nextLine() ;
        PrintWriter pw = new PrintWriter(soc.getOutputStream() , true) ;
        out.println("Server Says: I got your message of " + msg2) ;
    }catch(Exception e){
        System.out.print(e);
    }
    }
}
