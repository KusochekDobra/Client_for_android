package com.example.chat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Chat extends Activity{
    private Connection connection;
    public Button button;
    private String address;
    private String name;

    Thread threadForConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        address = intent.getStringExtra("ip");
        name = intent.getStringExtra("name");

        connection = new Connection(address, 9999);

        connection.setUserName(name);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        connection.setTextView((TextView) findViewById(R.id.view_window));

        threadForConnection = new Thread(connection);
        threadForConnection.start();


        button = (Button) findViewById(R.id.send);
    }

    //@Override
   // public void onBackPressed() {
        //to be Continue...
  //  }

    public void send(View view){
        button.setText(name);
    }

}
