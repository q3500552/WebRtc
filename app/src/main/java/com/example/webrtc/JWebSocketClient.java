package com.example.webrtc;

import android.util.Log;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.webrtc.WebRtcActivity;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class JWebSocketClient extends WebSocketClient {

    public JWebSocketClient(URI serverUri) { super(serverUri, new Draft_6455()); }
    private String receive_message;
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
            String id = (String) jsonObject.get("id");
            switch (id){
                case "register_response":
                    WebRtcActivity.textView.append("");
                    break;
                case "sendMessage":
                    String name = (String) jsonObject.get("username");
                    name = URLDecoder.decode(name, "utf-8");
                    String text = (String) jsonObject.get("text");
                    text = URLDecoder.decode(text, "utf-8");
                    WebRtcActivity.textView.append(name+" said: "+text+"\n");
                    break;
            }

//            receive_message = name+": "+text+"\n";
//            WebRtcActivity.receive_message_view.setText(name+": "+text+"\n");
        } catch (JSONException | UnsupportedEncodingException e) {
            Log.e("JWebSocketClient", "Line 35:"+ e);
            e.printStackTrace();
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) { Log.e("JWebSocketClient", "onClose()"); }

    @Override
    public void onError(Exception ex) {
        Log.e("JWebSocketClient", "onError()");
        Log.e("JWebSocketClient", ex+"");
    }
}



