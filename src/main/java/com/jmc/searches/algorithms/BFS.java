package com.jmc.searches.algorithms;

import com.jmc.entities.AlgorithmResult;
import com.jmc.entities.Block;
import com.jmc.entities.Grid;
import com.jmc.enums.Face;
import com.jmc.searches.Search;

import java.util.*;

public class BFS extends Search {
    private Block[][] warehouseGrid;

    public BFS(Block[][] warehouseGrid) {
        this.warehouseGrid = warehouseGrid;
        this.queue = new LinkedList<Block>();
        this.visited = new HashSet<Block>();
        this.parentMap = new HashMap<Block, Block>();
    }

    public AlgorithmResult bfs(int inicialX, int inicialY) {
        Block start = warehouseGrid[inicialX][inicialY];
        queue.add(start);
        visited.add(start);
        parentMap.put(start, null);

        while (!queue.isEmpty()) {
            Block current = queue.poll();

            if (current.getRobot() != null) {
                return new AlgorithmResult(reconstructFinalPath(parentMap, current), visited);
            }

            for (Map.Entry<Face, Boolean> entry : current.getDirections().entrySet()) {
                if (entry.getValue()) {
                    Face face = entry.getKey();
                    Block neighbor = getNeighbor(warehouseGrid, current, face);

                    // Se o vizinho não foi visitado e está dentro do grid
                    if (neighbor != null && !visited.contains(neighbor)) {
                        queue.add(neighbor);
                        visited.add(neighbor);
                        // Registrar o bloco atual como pai do vizinho
                        parentMap.put(neighbor, current);
                    }
                }
            }
        }
        return new AlgorithmResult(null, visited);
    }
}
