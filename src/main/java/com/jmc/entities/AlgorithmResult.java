package com.jmc.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlgorithmResult {
    // Caminho final da prateleira ao robô mais próximo
    private List<Block> finalPath;
    // Lista completa de todos os nós explorados durante a busca
    private List<Block> exploredPath;
    // Caminho do robo até prateleira
    private List<Block> robotPath;

    public AlgorithmResult(List<Block> finalPath, List<Block> exploredPath) {
        this.finalPath = finalPath != null ? finalPath : Collections.emptyList();
        this.exploredPath = exploredPath != null ? exploredPath : Collections.emptyList();
        this.robotPath = new ArrayList<>(this.finalPath);
        Collections.reverse(this.robotPath);
    }

    public List<Block> getFinalPath() {
        return finalPath;
    }

    public List<Block> getExploredPath() {
        return exploredPath;
    }

    public List<Block> getRobotPath() {
        return robotPath;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AlgorithmResult\n");
        sb.append(" Final Path:\n");
        for (Block b : finalPath) {
            sb.append("\t").append(b.toString()).append("\n");
        }
        sb.append(" Robot Path:\n");
        for (Block b : robotPath) {
            sb.append("\t").append(b.toString()).append("\n");
        }
        sb.append(" Explored List:\n");
        for (Block b : exploredPath) {
            sb.append("\t").append(b.toString()).append("\n");
        }
        return sb.toString();
    }
}
