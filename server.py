from websocket_server import WebsocketServer
import json


# Called for every client connecting (after handshake)
def new_client(client, server):
    print("New client connected and was given id %d" % client['id'])
    #server.send_message_to_all('{"id": "register_response", "isSucceed": "1"}')
    server.send_message(client, '{"id": "register_response", "isSucceed": "1"}')


# Called for every client disconnecting
def client_left(client, server):
    print("Client(%d) disconnected" % client['id'])


# Called when a client sends a message
def message_received(client, server, message):
    print("Client(%d) said: %s" % (client['id'], message))
    #mes_json = json.loads(message)
    print(mes_json['id'])
    if mes_json['id'] == 'call':
        #server.send_message_to_all('{"id": "call_response", "isSucceed": "1"}')
        server.send_message(server.clients[1], '{"id": "incall", "isSucceed": "0"}')
    elif mes_json['id'] == 'incall':
        #server.send_message_to_all('{"id": "incall_response", "isSucceed": "1"}')
        server.send_message(server.clients[0], '{"id": "incall_response", "isSucceed": "1"}')
    elif mes_json['id'] == "offer":
        #server.send_message_to_all('{"id": "offer","from": "11","fromName": "Jim","to": "12","toName": "Wendy","sessionDescription": {"type": "OFFER","description": "v=0\r\no=- 3285195603599485281 2 IN IP4 127.0.0.1\r\ns=-\r\nt=0 0\r\na=group:BUNDLE video\r\na=msid-semantic: WMS localstream\r\nm=video 9 RTP/AVPF 96 97 98 99 100 101 127 124 125\r\nc=IN IP4 0.0.0.0\r\na=rtcp:9 IN IP4 0.0.0.0\r\na=ice-ufrag:9Jqg\r\na=ice-pwd:lM34EvVxjaPGBislEH9VccVu\r\na=ice-options:trickle renomination\r\na=mid:video\r\na=extmap:14 urn:ietf:params:rtp-hdrext:toffset\r\na=extmap:13 http://www.webrtc.org/experiments/rtp-hdrext/abs-send-time\r\na=extmap:3 urn:3gpp:video-orientation\r\na=extmap:2 http://www.ietf.org/id/draft-holmer-rmcat-transport-wide-cc-extensions-01\r\na=extmap:5 http://www.webrtc.org/experiments/rtp-hdrext/playout-delay\r\na=extmap:6 http://www.webrtc.org/experiments/rtp-hdrext/video-content-type\r\na=extmap:7 http://www.webrtc.org/experiments/rtp-hdrext/video-timing\r\na=extmap:8 http://tools.ietf.org/html/draft-ietf-avtext-framemarking-07\r\na=extmap:9 http://www.webrtc.org/experiments/rtp-hdrext/color-space\r\na=sendrecv\r\na=rtcp-mux\r\na=rtcp-rsize\r\na=rtpmap:96 VP8/90000\r\na=rtcp-fb:96 goog-remb\r\na=rtcp-fb:96 transport-cc\r\na=rtcp-fb:96 ccm fir\r\na=rtcp-fb:96 nack\r\na=rtcp-fb:96 nack pli\r\na=rtpmap:97 rtx/90000\r\na=fmtp:97 apt=96\r\na=rtpmap:98 VP9/90000\r\na=rtcp-fb:98 goog-remb\r\na=rtcp-fb:98 transport-cc\r\na=rtcp-fb:98 ccm fir\r\na=rtcp-fb:98 nack\r\na=rtcp-fb:98 nack pli\r\na=rtpmap:99 rtx/90000\r\na=fmtp:99 apt=98\r\na=rtpmap:100 H264/90000\r\na=rtcp-fb:100 goog-remb\r\na=rtcp-fb:100 transport-cc\r\na=rtcp-fb:100 ccm fir\r\na=rtcp-fb:100 nack\r\na=rtcp-fb:100 nack pli\r\na=fmtp:100 level-asymmetry-allowed=1;packetization-mode=1;profile-level-id=42e01f\r\na=rtpmap:101 rtx/90000\r\na=fmtp:101 apt=100\r\na=rtpmap:127 red/90000\r\na=rtpmap:124 rtx/90000\r\na=fmtp:124 apt=127\r\na=rtpmap:125 ulpfec/90000\r\na=ssrc-group:FID 1791802911 305163890\r\na=ssrc:1791802911 cname:8uTWIc2YbUNSY02u\r\na=ssrc:1791802911 msid:localstream videtrack\r\na=ssrc:1791802911 mslabel:localstream\r\na=ssrc:1791802911 label:videtrack\r\na=ssrc:305163890 cname:8uTWIc2YbUNSY02u\r\na=ssrc:305163890 msid:localstream videtrack\r\na=ssrc:305163890 mslabel:localstream\r\na=ssrc:305163890 label:videtrack\r\n"},"isSucceed": 0}')
        if client['id'] == 0:
            server.send_message(server.clients[1], message)
        else:
            server.send_message(server.clients[0], message)
    
PORT=9001
server = WebsocketServer(PORT, "0.0.0.0")
server.set_fn_new_client(new_client)
server.set_fn_client_left(client_left)
server.set_fn_message_received(message_received)
server.run_forever()
