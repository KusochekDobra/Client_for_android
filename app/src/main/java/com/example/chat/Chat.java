package com.example.chat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.Socket;


public class Chat extends Activity{
    private Connection connection;
    public Button button;
    private String name;


    Thread threadForConnection;

    public static Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        connection = new Connection(socket);

        connection.setUserName(name);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);
        connection.setMessage((EditText) findViewById(R.id.message));
        connection.setTextView((TextView) findViewById(R.id.view_window));
        connection.setActivity(this);
        threadForConnection = new Thread(connection);
        threadForConnection.start();

    }

    //@Override
   // public void onBackPressed() {
        //to be Continue...
  //  }

    public void send(View view){
        connection.send();

    }
}
