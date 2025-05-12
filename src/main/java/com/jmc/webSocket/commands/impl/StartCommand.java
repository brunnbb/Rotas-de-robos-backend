package com.jmc.webSocket.commands.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jmc.entities.AlgorithmResult;
import com.jmc.entities.Coordinate;
import com.jmc.entities.Grid;
import com.jmc.enums.Algorithms;
import com.jmc.searches.Search;
import com.jmc.searches.algorithms.AStar;
import com.jmc.searches.algorithms.BFS;
import com.jmc.searches.algorithms.DFS;
import com.jmc.webSocket.commands.Command;
import jakarta.websocket.Session;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

//start <algorithm> <queue>
public class StartCommand extends Command {

    public StartCommand() {
        super("start");
    }

    @Override
    public void run(String[] args, Session session) {
        if (args.length < 3)
            return;

        String algorithm = args[1];
        Algorithms selectedAlgorithm = Algorithms.valueOf(algorithm);

        Grid grid = new Grid();

        Search algorithmInstance;
        switch (selectedAlgorithm) {
            case A_STAR -> algorithmInstance = new AStar(grid.getGrid());
            case BFS -> algorithmInstance = new BFS(grid.getGrid());
            case DFS -> algorithmInstance = new DFS(grid.getGrid());
            //case ITERATIVE_DEEPENING -> algorithmInstance = new IterativeDeepening(grid.getGrid());
            default -> {
                return;
            }
        }

        Map<Integer, AlgorithmResult> results = new LinkedHashMap<>();

        for (int i = 2; i < args.length; i++) {
            int shelf = Integer.parseInt(args[i]);
            System.out.println(shelf);

            Coordinate coordinate = grid.getShelfCoordinate(shelf);
            if (coordinate == null) continue;
            System.out.println(coordinate.toString());

            var result = algorithmInstance.search(coordinate.i(), coordinate.j());
            System.out.println(result.toString());

            results.put(shelf, result);
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String json = gson.toJson(results);
        try {
            session.getBasicRemote().sendText(json);
        } catch (Exception exception) {
            exception.printStackTrace(System.err);
        }


        System.out.println(json);
    }
}
