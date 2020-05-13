package com.example.webrtc;

import android.util.Log;
import com.example.webrtc.MainActivity;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class JWebSocketClient extends WebSocketClient {

    public JWebSocketClient(URI serverUri) { super(serverUri, new Draft_6455()); }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        Log.e("JWebSocketClient", "onOpen()");
    }

    @Override
    public void onMessage(String message) {
        Log.e("JWebSocketClient", "onMessage()");
        MainActivity.textView.setText(message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) { Log.e("JWebSocketClient", "onClose()"); }

    @Override
    public void onError(Exception ex) { Log.e("JWebSocketClient", "onError()"); } }


