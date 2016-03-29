package com.example.chat;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        setTitle("Entry");

        imageButton = (ImageButton) findViewById(R.id.Go);

        final EditText ipField = (EditText) findViewById(R.id.ip_field);
        final EditText userName = (EditText) findViewById(R.id.UserName);

        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                setContentView(R.layout.chat);
                String ipAddress = new String(ipField.getText().toString());
                String name = new String(userName.getText().toString());

                if (ipIsCorrect(ipAddress) && name != "-1"){

                    Intent intent = new Intent(MainActivity.this, Chat.class);
                    intent.putExtra("ip", ipAddress);
                    intent.putExtra("name", name);

                    startActivity(intent);

                }
                else{
                    final Timer timer = new Timer();

                    final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Важное сообщение!")
                            .setMessage("Введите коректный  IP")
                                    //   .setIcon(R.drawable.ic_android_cat)
                            .setCancelable(false);
                    /**
                     .setNegativeButton("ОК, иду на кухню",
                     new DialogInterface.OnClickListener() {
                     public void onClick(DialogInterface dialog, int id) {
                     dialog.cancel();
                     }
                     });
                     */
                    final AlertDialog alert = builder.create();
                    alert.show();

                    TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            alert.cancel();
                            timer.cancel();
                        }
                    };

                    timer.schedule(timerTask, 1700);

                }
            }

        });
    }

    private boolean ipIsCorrect(String s) {
        if(s.length() < 7) return false;

        final int fir = "0".charAt(0);
        final int last = "9".charAt(0);

        for (int i = 0; i < s.length(); i++) {
            int hash = (int) s.charAt(i);
            if ((hash < fir || hash > last) && !(s.charAt(i) == '.')) {
                return false;
            }
        }
        return true;
    }
}


/**
 @Override
 public boolean onCreateOptionsMenu(Menu menu) {

 getMenuInflater().inflate(R.menu.menu_main, menu);
 return true;
 }

 @Override
 public boolean onOptionsItemSelected(MenuItem item) {
 // Handle action bar item clicks here. The action bar will
 // automatically handle clicks on the Home/Up button, so long
 // as you specify a parent activity in AndroidManifest.xml.
 int id = item.getItemId();

 //noinspection SimplifiableIfStatement
 if (id == R.id.action_settings) {
 return true;
 }

 return super.onOptionsItemSelected(item);
 }
 */

