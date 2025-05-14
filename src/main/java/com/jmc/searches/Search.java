package com.jmc.searches;

import com.jmc.entities.AlgorithmResult;
import com.jmc.entities.Block;
import com.jmc.enums.Face;

import java.util.*;

public abstract class Search {
    // Grid que serve de mapa para o armazém
    protected Block[][] warehouseGrid;

    protected Search(Block[][] warehouseGrid) {
        this.warehouseGrid = warehouseGrid;
    }

    // Metodo abstrado para busca
    public abstract AlgorithmResult search(int shelfI, int shelfJ);

    // Metodo abstrato para inicilizar e limpar as estruturas de dados utilizadas em cada algoritmo
    public abstract void startDataStructures();

    // Pega o bloco vizinho baseado na coordenada cardial dada
    protected Block getNeighbor(Block current, Face face) {
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
