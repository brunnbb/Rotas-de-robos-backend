package com.jmc.webSocket.commands;

import com.jmc.webSocket.commands.impl.StartCommand;
import jakarta.websocket.Session;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private final Map<String, Command> commands;

    public CommandManager() {
        this.commands = new HashMap<>();
        this.registerCommands();
    }

    private void registerCommands() {
        this.commands.put("start", new StartCommand());
    }

    public void commandHandler(String message, Session session) {
        String[] args = message.split(" ");
        if (args.length == 0) return;

        if (commands.containsKey(args[0])) {
            Command command = this.commands.get(args[0]);

            command.run(args, session);
        }
    }
}
