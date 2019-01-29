package Cristian;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.nio.file.Files;

public class CristianServerThread extends Thread {
    private long FIVE_SECONDS = 5000;
    protected DatagramSocket socket = null;
    private final File ARCHIVO_PRUEBA = new File("C:\\Users\\josh-\\Desktop\\servidor\\muffin.png");

    public CristianServerThread() throws IOException {
	this("QuoteServerThread");
    }

    public CristianServerThread(String name) throws IOException {
        super(name);
        socket = new DatagramSocket(50002);        
    }

    public void run() {
            while (true) {
                try {
                    byte[] buf = new byte[256];
                    //Integer tam=Integer.parseInt(ARCHIVO_PRUEBA.length()+"");
                    //byte[] bufArchivo = new byte[Integer.parseInt(ARCHIVO_PRUEBA.length()+"")];
              
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    socket.receive(packet);                   
                    String time = Long.toString(System.currentTimeMillis() + 5000 );
                    
                    buf = time.getBytes();
                    //bufArchivo= Files.readAllBytes(ARCHIVO_PRUEBA.toPath());
                            
                    InetAddress address = packet.getAddress();
                    int port = packet.getPort();
                    packet = new DatagramPacket(buf, buf.length, address, port);
                    socket.send(packet);
                    
                    //enviando tamano del archivo
                    buf= Long.toString(ARCHIVO_PRUEBA.length()).getBytes();
                    packet = new DatagramPacket(buf, buf.length, address, port);
                    socket.send(packet);
                    
                    //enviando archivo
                     buf= new byte[Integer.parseInt(ARCHIVO_PRUEBA.length()+"")];
                     
                     buf=Files.readAllBytes(ARCHIVO_PRUEBA.toPath());
                     
                    packet = new DatagramPacket(buf, buf.length, address, port);
                    socket.send(packet);
                    sleep((long)(Math.random() * FIVE_SECONDS)); 

                } catch (InterruptedException e) {
                } catch (IOException e) {
                    e.printStackTrace();                    
                }
            }   
    }
}

