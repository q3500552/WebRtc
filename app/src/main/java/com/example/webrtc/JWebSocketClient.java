package com.example.webrtc;

import android.util.Log;
import com.example.webrtc.MainActivity;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class JWebSocketClient extends WebSocketClient {

    public JWebSocketClient(URI serverUri) { super(serverUri, new Draft_6455()); }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        Log.e("JWebSocketClient", "onOpen()");
        Map<String, String> map = new HashMap<>();
        map.put("id","register");
        map.put("username", WebRtcActivity.username);
        JSONObject jsonObject=new JSONObject(map);
        send(jsonObject.toString());
    }

    @Override
    public void onMessage(String message) {
        Log.e("JWebSocketClient", message);
        try {
            JSONObject jsonObject = new JSONObject(message);
            String name = (String) jsonObject.get("username");
            String text = (String) jsonObject.get("text");
            WebRtcActivity.textView.setText(name+": "+text);
        } catch (JSONException e) {
            Log.e("JWebSocketClient", "Line 35");
            e.printStackTrace();
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) { Log.e("JWebSocketClient", "onClose()"); }

    @Override
    public void onError(Exception ex) { Log.e("JWebSocketClient", "onError()"); } }



