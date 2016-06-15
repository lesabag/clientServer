package com.demo.lior.app.client;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/*
    Lior esabag
 */
public class ClientActivity extends Activity  {
    EditText etMessage;
    Button bSend;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        etMessage = (EditText)findViewById(R.id.etMessage);
        bSend = (Button)findViewById(R.id.btSend);

        bSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Send button clicked");

                Thread t  = new Thread(){

                    @Override
                    public void run()
                    {
                        try {
                            Socket s = new Socket(InetAddress.getByName ("10.0.0.2"), 7000);// connect to Emulator us IP: 10.0.2.2 // On a real device you need to set setprop service.adb.tcp.port 5555
                            System.out.println("In run");
                            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                            dos.writeUTF(etMessage.getText().toString());
                            dos.close();
                            s.close();
                        }catch (IOException e)
                        {
                            System.out.println("Unable to connect to socket");
                            e.printStackTrace();
                        }
                    }
                };
                t.start();
                Toast.makeText(context, "message " + "\"" + etMessage.getText().toString() + "\"" + " sent!", Toast.LENGTH_LONG).show();
            }
        });
    }
}

