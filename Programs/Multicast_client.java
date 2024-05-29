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
public class Multicast_client {
    public static void main(String[] args)throws IOException{
        try{
            InetAddress net = InetAddress.getByName("224.4.5.6") ;
            MulticastSocket multicast = new MulticastSocket(4567);
            multicast.joinGroup(net) ;
            byte[] bt = new byte[100];
            DatagramPacket packet = new DatagramPacket(bt , bt.length);
            multicast.receive(packet) ;
            System.out.println(new String(bt));
            multicast.close() ;
        }catch(Exception e){
            System.out.println("Error: " + e);
        }
}
}
