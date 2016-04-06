package com.example.chat;

import android.app.Activity;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection implements Runnable{

    private Activity activity;

    private EditText message;

    private Socket socket;

    private boolean enough;

    private String userName;

    public TextView textView;

    public Connection(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!enough) {
                byte[] buffer = new byte[4096];
                int bc = socket.getInputStream().read(buffer); // receive?
                String string = new String(buffer, 0, bc);

                showMessage(string);
            }
        } catch (IOException e) {

        }
    }

    /**
     * ЗДЕСЬ
     */
    private void showMessage(final String string) {

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.append(string + "\n");
            }
        });
//        System.out.println(string);
        /**ЗДЕСЬ Нужно
         *
         * textView.append(string)
         *
         * */
    }

    public void close() {
        enough = true;

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send() {

        String s = new String(message.getText().toString());

         try {
         socket.getOutputStream().write((userName + ": " + s).getBytes());
         } catch (IOException e) {
         e.printStackTrace();
         }
        message.setText("");
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setMessage(EditText message) {
        this.message = message;
    }
}
