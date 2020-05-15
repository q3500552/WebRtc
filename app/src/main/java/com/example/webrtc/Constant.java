package com.example.webrtc;

public class Constant {
    public static final String URL = "ws://192.168.0.4:9001/websocket";//server服务器地址
    public static final String STUN = "stun:stun.l.google.com:19302";//穿透服务器
    public static final String CHANNEL = "channel";

    public static final String OPEN = "open";
    public static final String REGISTER = "register";//注册
    public static final String REGISTER_RESPONSE = "register_response";//注册回复
    public static final int RESPONSE_SUCCEED = 1;//1成功
    public static final int RESPONSE_FAILURE = 2;//2失败
    public static final String CALL = "call";//拨打
    public static final String CALL_RESPONSE = "call_response";//拨打回复
    public static final String INCALL = "incall";//接听
    public static final String INCALL_RESPONSE = "incall_response";//接听回复


}
