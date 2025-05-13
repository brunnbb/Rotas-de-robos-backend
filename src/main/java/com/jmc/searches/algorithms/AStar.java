package com.jmc.searches.algorithms;

import com.jmc.entities.AlgorithmResult;
import com.jmc.entities.Block;
import com.jmc.entities.Robot;
import com.jmc.enums.Face;
import com.jmc.searches.Search;

import java.util.*;

public class AStar extends Search {
    // Lista dos objetivos
    private final List<Block> goals;
    // Set para marcar os nós já visitados/vistos pelos algoritmos (serve para evitar loops)
    private Set<Block> visited;
    // Lista que marca os blocos que já foram removidos da fila para análise
    private List<Block> explored;
    // Map que mapeia como um bloco foi alcançado (Serve para reconstruir o caminho final <Bloco, PaiDoBloco>)
    private Map<Block, Block> parentMap;
    // Map que mapeia o custo real de até cada bloco
    private Map<Block, Integer> gScore;
    // Fila de fronteira que tem como prioridade o menor f(n)
    private PriorityQueue<Block> openSet;

    public AStar(Block[][] warehouseGrid) {
        super(warehouseGrid);
        this.goals = new ArrayList<>();
    }

    @Override
    public void startDataStructures() {
        this.visited = new HashSet<>();
        this.explored = new ArrayList<>();
        this.parentMap = new HashMap<>();
        this.gScore = new HashMap<>();

        // Encontrar todos os blocos que possuem goals (robôs)
        for (int i = 0; i < warehouseGrid.length; i++) {
            for (int j = 0; j < warehouseGrid[0].length; j++) {
                Block b = warehouseGrid[i][j];
                if (b.getRobot() != null) {
                    goals.add(b);
                }
            }
        }

        // Fila de prioridade baseada em f(n) = g(n) + h(n)
        this.openSet = new PriorityQueue<>(
                Comparator.comparingInt(b -> gScore.getOrDefault(b, Integer.MAX_VALUE) + heuristic(b))
        );
    }

    @Override
    public AlgorithmResult search(int shelfI, int shelfJ) {
        Block start = warehouseGrid[shelfI][shelfJ];
        startDataStructures();
        gScore.put(start, 0);
        openSet.add(start);
        visited.add(start);
        parentMap.put(start, null);

        while (!openSet.isEmpty()) {
            // bloco com menor f(n)
            Block current = openSet.poll();
            explored.add(current);

            Robot robotFound = current.getRobot();
            if (robotFound != null) {
                // Adicionado para mover o robo imediatamente para prateleira
                start.setRobot(robotFound);
                current.setRobot(null);
                return new AlgorithmResult(reconstructFinalPath(parentMap, current), explored, robotFound);
            }

            for (Map.Entry<Face, Boolean> entry : current.getDirections().entrySet()) {
                if (!entry.getValue()) continue;

                Face face = entry.getKey();
                Block neighbor = getNeighbor(current, face);
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
