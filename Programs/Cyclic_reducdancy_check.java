/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.socket_programming;

import java.util.Scanner;

/**
 *
 * @author tanir
 */
public class Cyclic_reducdancy_check {
    private static String calculateCRC(String data , String polynomial){
        int crclength = polynomial.length() - 1 ;
        int crc = 0 ;
        int data_int = Integer.parseInt(data , 2) ;
        int polynomial_int = Integer.parseInt(polynomial , 2) ;
        for(int i = 0 ; i < data.length() ; i ++)
        {
            crc ^= (data_int & (1 << (data.length() - 1 - i))) ;
            if ((crc & (1 << (crclength - 1))) != 0) {
                crc = (crc << 1) ^ polynomial_int;
            } else {
                crc <<= 1;
            }
        }
        String crc_string = Integer.toBinaryString(crc & ((1 << crclength) - 1)) ;
        System.out.println("crc_String: " + crc_string);
        return String.format("%" + crclength + "s", crc_string).replace(' ' , '0') ;
    }
 public static void main(String[] args){
     Scanner sc = new Scanner(System.in) ;
     System.out.println("Enter the Message:");
     String msg = sc.nextLine();
     System.out.println(Integer.parseInt(msg , 2));
     System.out.println("Enter the polynomial");
     String poly = sc.nextLine();
     System.out.println("SubString: " + msg.substring(0, (msg.length() - poly.length() + 1)));
     String calculatedCRC = calculateCRC(msg.substring(0, (msg.length() - poly.length() + 1)) , poly) ;
     String receivedCRC = msg.substring((msg.length() - poly.length() + 1) , msg.length());
     System.out.println("CalculatedCRC = " + calculatedCRC) ;
     System.out.println("ReceivedCRC = " + receivedCRC) ;
     System.out.println("Hello World") ;
 }
}
