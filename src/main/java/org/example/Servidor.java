package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    public static void main(String[] args) {

        try {
            ServerSocket server = new ServerSocket(2000);
            Socket sc;

            System.out.println("Servidor iniciado");
            while(true){
                sc = server.accept();

                DataInputStream in = new DataInputStream(sc.getInputStream());
                DataOutputStream out = new DataOutputStream(sc.getOutputStream());
                out.writeUTF("Nombre de usuario para realizar conexión: ");
                String nombreCliente = in.readUTF();


                ServidorHilo hilo = new ServidorHilo(in, out, nombreCliente);
                hilo.start();

                System.out.println("Conexión con el usuario: " + nombreCliente+" establecida con éxito");
            }

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

}