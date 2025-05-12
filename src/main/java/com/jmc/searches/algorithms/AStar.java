package com.jmc.searches.algorithms;

import com.jmc.entities.AlgorithmResult;
import com.jmc.entities.Block;
import com.jmc.enums.Face;
import com.jmc.searches.Search;

import java.util.*;

public class AStar extends Search {
    private final List<Block> goals;

    public AStar(Block[][] warehouseGrid) {
        super(warehouseGrid);
        this.goals = new ArrayList<>();

        // Encontrar todos os blocos que possuem goals (robôs)
        for (int i = 0; i < warehouseGrid.length; i++) {
            for (int j = 0; j < warehouseGrid[0].length; j++) {
                Block b = warehouseGrid[i][j];
                if (b.getRobot() != null) {
                    goals.add(b);
                }
            }
        }
    }

    @Override
    public AlgorithmResult search(int shelfI, int shelfJ) {
        Block start = warehouseGrid[shelfI][shelfJ];
        // Custo real até cada bloco
        Map<Block, Integer> gScore = new HashMap<>();
        gScore.put(start, 0);

        // Fila de prioridade baseada em f(n) = g(n) + h(n)
        PriorityQueue<Block> openSet = new PriorityQueue<>(
                Comparator.comparingInt(b -> gScore.getOrDefault(b, Integer.MAX_VALUE) + heuristic(b))
        );

        openSet.add(start);
        visited.add(start);
        parentMap.put(start, null);

        while (!openSet.isEmpty()) {
            Block current = openSet.poll(); // bloco com menor f(n)
            explored.add(current);

            // Encontrou um robô (objetivo)
            if (current.getRobot() != null) {
                return new AlgorithmResult(reconstructFinalPath(parentMap, current), explored, current.getRobot());
            }

            for (Map.Entry<Face, Boolean> entry : current.getDirections().entrySet()) {
                if (!entry.getValue()) continue;

                Face face = entry.getKey();
                Block neighbor = getNeighbor(warehouseGrid, current, face);
                if (neighbor == null) continue;

                int tentativeG = gScore.get(current) + 1;

                if (!gScore.containsKey(neighbor) || tentativeG < gScore.get(neighbor)) {
                    parentMap.put(neighbor, current);
                    gScore.put(neighbor, tentativeG);

                    if (!visited.contains(neighbor)) {
                        openSet.add(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
        }
        return new AlgorithmResult(null, explored, null);
    }

    // Heurística de Manhattan até o robô mais próximo
    private int heuristic(Block block) {
        return goals.stream()
                .mapToInt(goal -> Math.abs(block.getI() - goal.getI()) + Math.abs(block.getJ() - goal.getJ()))
                .min()
                .orElse(Integer.MAX_VALUE);
    }
}
