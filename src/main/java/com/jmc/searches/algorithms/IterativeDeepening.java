package com.jmc.searches.algorithms;

import com.jmc.entities.AlgorithmResult;
import com.jmc.entities.Block;
import com.jmc.entities.Robot;
import com.jmc.enums.Face;
import com.jmc.searches.Search;

import java.util.*;

public class IterativeDeepening extends Search {
    private List<Block> explored;
    private Set<Block> visited;
    private Map<Block, Block> parentMap;
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

            depthLimitedSearch(start, depthLimit, visited);

            if (foundBlock != null) {
                Robot robot = foundBlock.getRobot();
                return new AlgorithmResult(reconstructFinalPath(parentMap, foundBlock), explored, robot);
            }

            if (!cutoffOccurred) {
                return new AlgorithmResult(null, explored, null);
            }
            depthLimit++;
        }
    }

    private void depthLimitedSearch(Block current, int limit, Set<Block> visited) {
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
            if (!entry.getValue()) continue;

            Block neighbor = getNeighbor(current, entry.getKey());
            if (neighbor == null || visited.contains(neighbor)) continue;

            parentMap.put(neighbor, current);
            depthLimitedSearch(neighbor, limit - 1, visited);

            // Se encontrou o robô, para de buscar
            if (foundBlock != null) return;
        }
    }
}
