package com.example.chat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.IOException;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    ImageButton imageButton;
    EditText ipField, userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        setTitle("Entry");

        imageButton = (ImageButton) findViewById(R.id.Go);

        ipField = (EditText) findViewById(R.id.ip_field);
        userName = (EditText) findViewById(R.id.UserName);

    }

    public void goIsClicked(View view) {
        new Conn().execute();
    }

    private class Conn extends AsyncTask<Void, Void, Boolean> {

        private String ipAddress, name;

        Socket socket;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            ipAddress = new String(ipField.getText().toString());
            name = new String(userName.getText().toString());

        }

        @Override
        protected Boolean doInBackground(Void... params) {

            if (ipIsCorrect(ipAddress) && !name.equals("")) {
                return true;
            }
          return false;
          }

        @Override
        protected void onPostExecute(Boolean aVoid) {
            super.onPostExecute(aVoid);

            if (aVoid){

                Intent intent = new Intent(MainActivity.this, Chat.class);
                intent.putExtra("name", name);
                Chat.socket = socket;

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


        private boolean ipIsCorrect(String s) {
            try {
                socket = new Socket(s, 9999);

                return true;
            } catch (IOException e) {
                 return false;
            }


        }
    }
}
