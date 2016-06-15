package com.demo.lior.app.client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by app on 15/06/2016.
 */
public class Server {

    private static int PORT = 7000;

    public static void main(String[] args) {

        Thread t  = new Thread(){

            @Override
            public void run()
            {
                System.out.println("Server is running and listening ...");
                try {
                    ServerSocket ss = new ServerSocket(PORT);
                    while (true) {
                        Socket s = ss.accept();
                        DataInputStream dis = new DataInputStream(s.getInputStream());
                        System.out.println("Received from client: " + dis.readUTF());
                        dis.close();
                        s.close();
                    }
                }catch (IOException e)
                {
                    System.out.println("Unable to get data from client!");
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }
}
