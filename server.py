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
    mes_json = json.loads(message)
    print(mes_json['id'])
    if mes_json['id'] == 'call':
        if client['id'] != server.clients[0]['id']:
            server.send_message(server.clients[0], '{"id": "incall", "isSucceed": "0"}')    
        else:
            server.send_message(server.clients[1], '{"id": "incall", "isSucceed": "0"}')
    elif mes_json['id'] == 'incall':  
        if client['id'] != server.clients[0]['id']:
            server.send_message(server.clients[0], '{"id": "incall_response", "isSucceed": "1"}')
        else:
            server.send_message(server.clients[1], '{"id": "incall_response", "isSucceed": "1"}')
    elif mes_json['id'] == "offer":
        if client['id'] != server.clients[0]['id']:
            server.send_message(server.clients[0], message)
        else:
            server.send_message(server.clients[1], message)
    elif mes_json['id'] == "candidate":
        if client['id'] != server.clients[0]['id']:
            server.send_message(server.clients[0], message)
        else:
            server.send_message(server.clients[1], message)
    
PORT=9001
server = WebsocketServer(PORT, "0.0.0.0")
server.set_fn_new_client(new_client)
server.set_fn_client_left(client_left)
server.set_fn_message_received(message_received)
server.run_forever()
