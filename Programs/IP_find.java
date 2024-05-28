/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.socket_programming;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author tanir
 */
public class IP_find {
    private static InetAddress find_address()throws IOException{
        InetAddress address = InetAddress.getLocalHost() ;
        return address ;
    }
    private static InetAddress find_ip_other_machine(String e)throws IOException{
        InetAddress address = InetAddress.getByName(e) ;
        return address ;
    }
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in) ;
        InetAddress addr = find_address() ;
        System.out.println("The Local IP of the machine is: " + addr.getHostAddress());
        System.out.println("Enter the URL of the server whose IP address you want to fetch.") ;
        String msg = sc.nextLine() ;
        InetAddress adr = find_ip_other_machine(msg) ;
        System.out.println("The IP Address of the server is: " + adr.getHostAddress()) ;
    }
}
