package com.jmc.searches;

import com.jmc.entities.Block;
import com.jmc.enums.Face;

import java.util.*;

public abstract class Search {
    protected Queue<Block> queue;
    protected Set<Block> visited;
    protected Map<Block, Block> parentMap;

    protected Search() {

    }

    protected Block getNeighbor(Block[][] warehouseGrid, Block current, Face face) {
        int x = current.getX();
        int y = current.getY();

        switch (face) {
            case NORTH:
                return warehouseGrid[x - 1][y];
            case SOUTH:
                return warehouseGrid[x + 1][y];
            case EAST:
                return warehouseGrid[x][y + 1];
            case WEST:
                return warehouseGrid[x][y - 1];
            default:
                return null;
        }
    }

    // Reeconstrói o caminho final da prateleira até o bloco onde está o robô
    protected List<Block> reconstructFinalPath(Map<Block, Block> parentMap, Block goal) {
        List<Block> path = new ArrayList<>();
        Block current = goal;

        // Reconstruir o caminho do robô até o ponto inicial
        while (current != null) {
            path.add(current);
            current = parentMap.get(current);
        }

        // O caminho é reconstruído de trás para frente
        Collections.reverse(path);
        return path;
    }
}
