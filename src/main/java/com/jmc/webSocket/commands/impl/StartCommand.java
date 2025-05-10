package com.jmc.webSocket.commands.impl;

import com.jmc.enums.Algorithms;
import com.jmc.webSocket.commands.Command;
import jakarta.websocket.Session;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//start <algorithm> <queue>
public class StartCommand extends Command {

    public StartCommand() {
        super("start");
    }

    @Override
    public void run(String[] args, Session session) {
        if (args.length != 3)
            return;

        String algorithm = args[1];
        if (!Arrays.stream(Algorithms.values()).allMatch(alg -> alg.name().equalsIgnoreCase(algorithm)))
            return;

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 2; i < args.length; i++) {
            queue.add(Integer.parseInt(args[i]));
        }

        //INVOKE ALGORITHM RUN METHOD
    }
}
