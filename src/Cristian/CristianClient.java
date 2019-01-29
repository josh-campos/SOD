package Cristian;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Juan
 */
import java.io.*;
import static java.lang.Thread.sleep;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class CristianClient {
    public static void main(String[] args) throws IOException{       

        DatagramSocket socket = new DatagramSocket();
        byte[] buf = new byte[256];
        InetAddress address = InetAddress.getByName("localHost");            
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 50002);
        
        long T0;
        long serverTime;
        long T1;
        long finalTime;
        T0 = System.currentTimeMillis();
        socket.send(packet);
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        T1 = System.currentTimeMillis();
        String received = new String(packet.getData(), 0, packet.getLength());
        serverTime = Long.parseLong(received);
        finalTime = serverTime + (T1 - T0) / 2;
        DateFormat estandar = new SimpleDateFormat("HH:mm:ss:SSS");
        System.out.println("Ejecutando........");
        System.out.println("Tiempo Cliente: " + estandar.format(new Date(T1)));
        System.out.println("Tiempo Servidor: " + estandar.format(new Date(serverTime)));
        System.out.println("Nuevo Tiempo Cliente: " + estandar.format(new Date(finalTime)));
        System.out.println("\n\tEsperando dimension de archivo...");
        
        //adquiriendo tamano de archivo
        buf=new byte[256];
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
         received = new String(packet.getData(), 0, packet.getLength());
         System.out.println("peso en byte del archivo: "+received);
        
        
        //adquiriendo archivo
        buf=new byte[Integer.parseInt(received)];
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        byte[] recibido= packet.getData();
         
        try (FileOutputStream stream = new FileOutputStream("C:\\Users\\josh-\\Desktop\\cliente\\muffin.png")) {
    stream.write(recibido);
}
        
         System.out.println("Archivo alamcenado exitosamente!");
        
        
        
            
	}     
   
    }
