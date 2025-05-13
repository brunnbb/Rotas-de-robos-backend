package com.jmc.webSocket.commands;

import jakarta.websocket.Session;

public abstract class Command {
    private final String command;

    public Command(String command) {
        this.command = command;
    }

    public abstract void run(String[] args, Session session);

    @Override
    public String toString() {
        return "Command{" +
                "command='" + command + '\'' +
                '}';
    }
}
