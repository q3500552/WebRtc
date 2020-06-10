package com.example.webrtc;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.gson.Gson;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class WebRtcActivity extends AppCompatActivity {
    public static TextView textView;
    private Button button;
    private EditText editText;
    private JWebSocketClient webSocketClient;
    public static String username;
    private int now_lines;

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
        now_lines = 0;
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    public void summit(View view) throws UnsupportedEncodingException {
        String message = editText.getText()+"";
//        textView.append(URLDecoder.decode(username, "utf-8")+" said: "+URLDecoder.decode(message, "utf-8")+"\n");
        textView.append(username+" said: "+message+"\n");
//        textView.setText(message+"\n");
        Map<String, String> map = new HashMap<>();
        map.put("username", URLEncoder.encode(username, "utf-8"));
        map.put("text", URLEncoder.encode(message, "utf-8"));
        map.put("id", "sendMessage");
        JSONObject jsonObject=new JSONObject(map);
        Log.e("send", jsonObject.toString());
        editText.setText("");
        webSocketClient.send(jsonObject.toString());
    }

}
