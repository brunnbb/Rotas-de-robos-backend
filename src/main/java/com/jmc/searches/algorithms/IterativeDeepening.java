package com.jmc.searches.algorithms;

import com.jmc.entities.AlgorithmResult;
import com.jmc.entities.Block;
import com.jmc.entities.Robot;
import com.jmc.enums.Face;
import com.jmc.searches.Search;

import java.util.*;

public class IterativeDeepening extends Search {
    // Set para marcar os nós já visitados/vistos pelos algoritmos (serve para evitar loops)
    private Set<Block> visited;
    // Lista que marca os blocos que já foram removidos da fila de execução para análise
    private List<Block> explored;
    // Map que mapeia como um bloco foi alcançado (Serve para reconstruir o caminho final <Bloco, PaiDoBloco>)
    private Map<Block, Block> parentMap;
    // Bloco que foi achado pela busca em profundidade limitada
    private Block foundBlock;
    private boolean cutoffOccurred;

    public IterativeDeepening(Block[][] warehouseGrid) {
        super(warehouseGrid);
    }

    @Override
    public void startDataStructures() {
        this.visited = new HashSet<>();
        this.explored = new ArrayList<>();
        this.parentMap = new HashMap<>();
        this.foundBlock = null;
        this.cutoffOccurred = false;
    }

    @Override
    public AlgorithmResult search(int shelfI, int shelfJ) {
        Block start = warehouseGrid[shelfI][shelfJ];

        int depthLimit = 0;
        while (true) {
            startDataStructures();
            depthLimitedSearch(start, depthLimit);

            if (foundBlock != null) {
                Robot robot = foundBlock.getRobot();
                start.setRobot(robot);
                foundBlock.setRobot(null);
                return new AlgorithmResult(reconstructFinalPath(parentMap, foundBlock), explored, robot);
            }

            if (!cutoffOccurred) {
                return new AlgorithmResult(null, explored, null);
            }
            depthLimit++;
        }
    }

    // Busca em profundidade limitada
    private void depthLimitedSearch(Block current, int limit) {
        visited.add(current);
        explored.add(current);

        if (current.getRobot() != null) {
            foundBlock = current;
            return;
        }

        if (limit == 0) {
            cutoffOccurred = true;
            return;
        }

        for (Map.Entry<Face, Boolean> entry : current.getDirections().entrySet()) {
            if (entry.getValue()) {
                Face face = entry.getKey();
                Block neighbor = getNeighbor(current, face);

                if (neighbor != null && !visited.contains(neighbor)) {
                    parentMap.put(neighbor, current);
                    depthLimitedSearch(neighbor, limit - 1);
                    if (foundBlock != null) return;
                }
            }
        }
    }
}
