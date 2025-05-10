package com.jmc.webSocket;

import com.jmc.webSocket.commands.CommandManager;
import jakarta.websocket.OnMessage;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/server")
public class WebSocketEndpoint {
    private final CommandManager commandManager;

    public WebSocketEndpoint() {
        this.commandManager = new CommandManager();
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        this.commandManager.commandHandler(message, session);
    }
}
