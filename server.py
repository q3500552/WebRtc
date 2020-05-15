from websocket_server import WebsocketServer
import json

def findUser(username, clients):
    index = 0
    for i in clients:
        #print("Line7: "+i)
        if i["username"] != username:
            return index
        index = index+1
    return index
# Called for every client connecting (after handshake)
def new_client(client, server):
    print("New client connected and was given id %d" % client['id'])
    #server.send_message_to_all('{"id": "register_response", "isSucceed": "1"}')
    server.send_message(client, 'register_response')


# Called for every client disconnecting
def client_left(client, server):
    print("Client(%d) disconnected" % client['id'])


# Called when a client sends a message
def message_received(client, server, message):
    print("Client(%d) said: %s" % (client['id'], message))
    try:
        meg_json = json.loads(message)
        if meg_json["id"] == "register":
            client["username"] = meg_json["username"]
        else:
            client["username"] = meg_json["username"]
            client["text"] = meg_json["text"]
            client_num = findUser(client["username"], server.clients)
            print(client_num)
            server.send_message(server.clients[client_num], message)
    except:
        print("收到"+message)
    
    
PORT=9001
server = WebsocketServer(PORT, "0.0.0.0")
server.set_fn_new_client(new_client)
server.set_fn_client_left(client_left)
server.set_fn_message_received(message_received)
server.run_forever()
