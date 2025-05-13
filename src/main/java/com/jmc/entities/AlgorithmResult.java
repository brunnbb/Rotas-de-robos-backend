package com.jmc.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlgorithmResult {
    // Caminho final da prateleira ao robô mais próximo
    private final List<Block> finalPath;
    // Lista completa de todos os nós explorados durante a busca
    private final List<Block> exploredPath;
    // Caminho do robo até prateleira
    private final List<Block> robotPath;
    // Robo que foi achado
    private final Robot robot;

    public AlgorithmResult(List<Block> finalPath, List<Block> exploredPath, Robot robot) {
        this.finalPath = finalPath != null ? finalPath : Collections.emptyList();
        this.exploredPath = exploredPath != null ? exploredPath : Collections.emptyList();
        this.robotPath = new ArrayList<>(this.finalPath);
        Collections.reverse(this.robotPath);
        this.robot = robot;
    }

    public Robot getClosestRobot() {
        return robot;
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

    public int getNumberOfExploredBlocks(){
        return exploredPath.size();
    }

    public Robot getRobot() {
        return robot;
    }

    public List<String> getRobotCardinalDirections() {
        List<String> cardinalDirections = new ArrayList<>();

        for (int i = 0; i < robotPath.size() - 1; i++) {
            Block current = robotPath.get(i);
            Block next = robotPath.get(i + 1);

            int deltaI = next.getI() - current.getI();
            int deltaJ = next.getJ() - current.getJ();

            if (deltaI == -1 && deltaJ == 0) {
                cardinalDirections.add("NORTH");
            } else if (deltaI == 1 && deltaJ == 0) {
                cardinalDirections.add("SOUTH");
            } else if (deltaI == 0 && deltaJ == -1) {
                cardinalDirections.add("WEST");
            } else if (deltaI == 0 && deltaJ == 1) {
                cardinalDirections.add("EAST");
            } else {
                cardinalDirections.add("UNKNOWN");
            }
        }
        return cardinalDirections;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AlgorithmResult\n");
        sb.append(" Robot: ").append(robot).append("\n");
        sb.append(" Number of explored blocks: ").append(getNumberOfExploredBlocks()).append("\n");

        sb.append(" Final Path:\n");
        finalPath.forEach(b -> sb.append("\t").append(b).append("\n"));

        sb.append(" Robot Path:\n");
        robotPath.forEach(b -> sb.append("\t").append(b).append("\n"));

        sb.append(" Directions:\n");
        sb.append("\t").append(getRobotCardinalDirections().toString()).append("\n");

        sb.append(" Explored List:\n");
        exploredPath.forEach(b -> sb.append("\t").append(b).append("\n"));

        return sb.toString();
    }

}
