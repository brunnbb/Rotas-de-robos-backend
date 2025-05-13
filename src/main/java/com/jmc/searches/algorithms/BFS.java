package com.jmc.searches.algorithms;

import com.jmc.entities.AlgorithmResult;
import com.jmc.entities.Block;
import com.jmc.entities.Robot;
import com.jmc.enums.Face;
import com.jmc.searches.Search;

import java.util.*;

public class BFS extends Search {
    // Fila que será manipulada pelos algoritmos
    private Queue<Block> queue;
    // Set para marcar os nós já visitados/vistos pelos algoritmos (serve para evitar loops)
    private Set<Block> visited;
    // Lista que marca os blocos que já foram removidos da fila para análise
    private List<Block> explored;
    // Map que mapeia como um bloco foi alcançado (Serve para reconstruir o caminho final <Bloco, PaiDoBloco>)
    private Map<Block, Block> parentMap;

    public BFS(Block[][] warehouseGrid) {
        super(warehouseGrid);
    }

    @Override
    public void startDataStructures() {
        this.queue = new LinkedList<>();
        this.visited = new HashSet<>();
        this.explored = new ArrayList<>();
        this.parentMap = new HashMap<>();
    }

    @Override
    public AlgorithmResult search(int shelfI, int shelfJ) {
        Block start = warehouseGrid[shelfI][shelfJ];
        startDataStructures();
        queue.add(start);
        visited.add(start);
        parentMap.put(start, null);

        while (!queue.isEmpty()) {
            Block current = queue.poll();
            explored.add(current);

            Robot robotFound = current.getRobot();
            if (robotFound != null) {
                // Adicionado para mover o robo imediatamente para prateleira
                start.setRobot(robotFound);
                current.setRobot(null);
                return new AlgorithmResult(reconstructFinalPath(parentMap, current), explored, robotFound);
            }

            for (Map.Entry<Face, Boolean> entry : current.getDirections().entrySet()) {
                // Se o bloco atual tiver algum bloco alcaçável pelas coordenads cardiais
                if (entry.getValue()) {
                    Face face = entry.getKey();
                    // Pega o vizinho baseado na coordenada cardial
                    Block neighbor = getNeighbor(current, face);

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
        return new AlgorithmResult(null, explored, null);
    }
}
