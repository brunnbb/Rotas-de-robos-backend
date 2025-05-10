package com.jmc.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class AlgorithmResult {
    // Caminho final da prateleira ao robô mais próximo
    private List<Block> finalPath;
    // Lista completa de todos os nodos expandidos durante a busca
    private Set<Block> completePath;
    // Caminho do robo até prateleira
    private List<Block> robotPath;

    public AlgorithmResult(List<Block> finalPath, Set<Block> completePath) {
        this.finalPath = finalPath != null ? finalPath : Collections.emptyList();
        this.completePath = completePath != null ? completePath : Collections.emptySet();
        this.robotPath = new ArrayList<>(this.finalPath);
        Collections.reverse(this.robotPath);
    }

    public List<Block> getFinalPath() {
        return finalPath;
    }

    public Set<Block> getCompletePath() {
        return completePath;
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
        return sb.toString();
    }
}
