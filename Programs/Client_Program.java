/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.socket_programming;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.net.Socket;
import java.util.Scanner;


/**
 *
 * @author tanir
 */
public class Client_Program {
    public static void main(String[] args) throws IOException{
        try{
            System.out.println("Client Started");
            Socket ss = new Socket("localhost",9080);
            Scanner sc = new Scanner(System.in) ;
            out.println("Enter the Message") ;
            String msg = sc.nextLine() ;
            PrintWriter out = new PrintWriter(ss.getOutputStream() , true);
            out.println(msg);
            Scanner sc2 = new Scanner(ss.getInputStream()) ;
            System.out.println(sc2.nextLine());
    }catch(Exception e){
        System.out.println("Error: " + e);
    }
    }
}
