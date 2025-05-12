package com.jmc.searches.algorithms;

import com.jmc.entities.AlgorithmResult;
import com.jmc.entities.Block;
import com.jmc.entities.Grid;
import com.jmc.enums.Face;
import com.jmc.searches.Search;

import java.util.*;

public class BFS extends Search {

    public BFS(Block[][] warehouseGrid) {
        super(warehouseGrid);
    }

    @Override
    public AlgorithmResult search(int shelfI, int shelfJ) {
        Block start = warehouseGrid[shelfI][shelfJ];
        queue.add(start);
        visited.add(start);
        parentMap.put(start, null);

        while (!queue.isEmpty()) {
            Block current = queue.poll();
            explored.add(current);

            if (current.getRobot() != null) {
                return new AlgorithmResult(reconstructFinalPath(parentMap, current), explored);
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
        return new AlgorithmResult(null, explored);
    }
}
