package com.jmc.searches.algorithms;

import com.jmc.entities.AlgorithmResult;
import com.jmc.entities.Block;
import com.jmc.enums.Face;
import com.jmc.searches.Search;

import java.util.*;

public class DFS extends Search {
    public DFS(Block[][] warehouseGrid) {
        super(warehouseGrid);
    }

    @Override
    public AlgorithmResult search(int shelfI, int shelfJ) {
        Block start = warehouseGrid[shelfI][shelfJ];
        queue.add(start);
        visited.add(start);
        parentMap.put(start, null);

        while (!queue.isEmpty()) {
            Block current = queue.removeLast();
            explored.add(current);

            if (current.getRobot() != null) {
                return new AlgorithmResult(reconstructFinalPath(parentMap, current), explored);
            }

            // Isso é feito para garantir a lógica inicial de expansão/criação dos nós de Sul->Oeste->Leste->Norte
            List<Map.Entry<Face, Boolean>> neighbors = new ArrayList<>(current.getDirections().entrySet());
            Collections.reverse(neighbors);

            for (Map.Entry<Face, Boolean> entry : neighbors) {
                if (entry.getValue()) {
                    Face face = entry.getKey();
                    Block neighbor = getNeighbor(warehouseGrid, current, face);

                    if (neighbor != null && !visited.contains(neighbor)) {
                        queue.addLast(neighbor);
                        visited.add(neighbor);
                        parentMap.put(neighbor, current);
                    }
                }
            }
        }
        return new AlgorithmResult(null, explored);
    }
}
