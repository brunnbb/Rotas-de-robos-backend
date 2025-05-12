package com.jmc.searches;

import com.jmc.entities.AlgorithmResult;
import com.jmc.entities.Block;
import com.jmc.enums.Face;

import java.util.*;

public abstract class Search {
    // Grid que serve de mapa para o armazém
    protected Block[][] warehouseGrid;
    // Fila/Pilha que será manipulada pelos algoritmos
    protected Deque<Block> dequeue;
    // Lista que marca os nós explorados (aqueles que foram removidos da fila/pilha para análise)
    protected List<Block> explored;
    // Set para marcar os nós já visitados/vistos pelos algoritmos (serve para evitar loops)
    protected Set<Block> visited;
    // Map que mapeia como um bloco foi alcançado (Serve para reconstruir o caminho final <Bloco, PaiDoBloco>)
    protected Map<Block, Block> parentMap;

    protected Search(Block[][] warehouseGrid) {
        this.warehouseGrid = warehouseGrid;
        this.dequeue = new LinkedList<>();
        this.explored = new ArrayList<>();
        this.visited = new HashSet<>();
        this.parentMap = new HashMap<>();
    }

    // Metodo abstrado para busca
    public abstract AlgorithmResult search(int shelfI, int shelfJ);

    // Pega o bloco vizinho baseado na coordenado cardial dada
    protected Block getNeighbor(Block[][] warehouseGrid, Block current, Face face) {
        int i = current.getI();
        int j = current.getJ();

        switch (face) {
            case NORTH -> {
                if (i > 0) return warehouseGrid[i - 1][j];
            }
            case SOUTH -> {
                if (i < 12) return warehouseGrid[i + 1][j];
            }
            case EAST -> {
                if (j < 15) return warehouseGrid[i][j + 1];
            }
            case WEST -> {
                if (j > 0) return warehouseGrid[i][j - 1];
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
