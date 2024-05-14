/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.socket_programming;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author tanir
 */
public class MultiCast_Server {
    public static void main(String[] args)throws IOException{
        try{
            InetAddress grp = InetAddress.getByName("224.4.5.6");
            MulticastSocket multi = new MulticastSocket();
            String msg = "This is Tanir Sahoo" ;
            DatagramPacket dt = new DatagramPacket(msg.getBytes() , msg.length() , grp , 4567) ;
            multi.send(dt) ;
            multi.close() ;
        }catch(Exception e){
            System.out.println("Error: " + e) ;
        }
    }
    
}
