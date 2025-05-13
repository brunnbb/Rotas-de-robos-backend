package com.jmc;

import com.jmc.webSocket.WebSocketEndpoint;
import org.glassfish.tyrus.server.Server;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Server server = new Server("localhost", 9000, "/", Map.of(), WebSocketEndpoint.class);

        try {
            server.start();
            while (true);
        } catch (Exception exception) {
            exception.printStackTrace(System.err);
        }

    }
}