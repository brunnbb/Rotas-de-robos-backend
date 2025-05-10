package com.jmc;

import com.jmc.entities.*;
import com.jmc.webSocket.WebSocketEndpoint;
import org.glassfish.tyrus.server.Server;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        /*
        Block[][] grid = Grid.createGrid();
        for (int x = 0; x < 13; x++) {
            for (int y = 0; y < 15; y++) {
                System.out.print(grid[x][y] + "\t");
            }
            System.out.println(" ");
        }
         */
        Server server = new Server("localhost", 9000, "/", Map.of(), WebSocketEndpoint.class);

        try {
            server.start();
            while (true);
        } catch (Exception exception) {
            exception.printStackTrace(System.err);
        }

    }
}