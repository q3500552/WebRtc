package com.example.webrtc;

import androidx.appcompat.app.AppCompatActivity;
import com.example.webrtc.JWebSocketClient;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.URI;

public class MainActivity extends AppCompatActivity {
    public static TextView textView;
    private Button button;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JWebSocketClient webSocketClient = new JWebSocketClient(URI.create("ws://192.168.0.5:9001"));
        try {
            //connectBlocking多出一个等待操作，会先连接再发送，否则未连接发送会报错
            webSocketClient.connectBlocking();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);
    }
}
