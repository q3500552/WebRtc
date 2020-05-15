package com.example.webrtc;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.gson.Gson;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class WebRtcActivity extends AppCompatActivity {
    public static TextView textView;
    private Button button;
    private EditText editText;
    private JWebSocketClient webSocketClient;
    public static String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webrtc);
        Bundle bundle = this.getIntent().getExtras();
        username = bundle.getString("username");
        webSocketClient = new JWebSocketClient(URI.create("ws://192.168.0.5:9001"));
        try {
            webSocketClient.connectBlocking();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);

    }

    public void summit(View view){
        String message = editText.getText()+"";
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("text", message);
        map.put("id", "sendMessage");
        JSONObject jsonObject=new JSONObject(map);
        Log.e("send", jsonObject.toString());
        webSocketClient.send(jsonObject.toString());
    }
}
