package com.jmc.searches;

import com.jmc.entities.AlgorithmResult;
import com.jmc.entities.Block;
import com.jmc.enums.Face;

import java.util.*;

public abstract class Search {
    // Grid que serve de mapa para o armazém
    protected Block[][] warehouseGrid;
    // Fila/Pilha que será manipulada pelos algoritmos
    protected Deque<Block> queue;
    // Lista que marca os nós explorados (aqueles que foram removidos da fila/pilha para análise)
    protected List<Block> explored;
    // Set para marcar os nós já visitados/vistos pelos algoritmos (serve para evitar loops)
    protected Set<Block> visited;
    // Map que mapeia como um bloco foi alcançado (Serve para reconstruir o caminho final <Bloco, PaiDoBloco>)
    protected Map<Block, Block> parentMap;

    protected Search(Block[][] warehouseGrid) {
        this.warehouseGrid = warehouseGrid;
        this.queue = new LinkedList<>();
        this.explored = new ArrayList<>();
        this.visited = new HashSet<>();
        this.parentMap = new HashMap<>();
    }

    public abstract AlgorithmResult search(int shelfI, int shelfJ);

    protected Block getNeighbor(Block[][] warehouseGrid, Block current, Face face) {
        int x = current.getI();
        int y = current.getJ();

        switch (face) {
            case NORTH -> {
                if (x > 0) return warehouseGrid[x - 1][y];
            }
            case SOUTH -> {
                if (x < 12) return warehouseGrid[x + 1][y];
            }
            case EAST -> {
                if (y < 15) return warehouseGrid[x][y + 1];
            }
            case WEST -> {
                if (y > 0) return warehouseGrid[x][y - 1];
            }
        }
        return null;
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
