package com.example.webrtc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.URI;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button button;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        webSocketClient = new JWebSocketClient(URI.create("ws://192.168.0.5:9001"));
//        try {
//            webSocketClient.connectBlocking();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);
    }

    public  void connect(View view){
        String username = editText.getText()+"";
        Log.e("send", username);
        Intent intent=new Intent(MainActivity.this,WebRtcActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("username", username);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
