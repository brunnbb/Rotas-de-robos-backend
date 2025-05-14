package com.jmc.entities;

import com.jmc.enums.Face;

import java.util.LinkedHashMap;
import java.util.Map;


public class Block {
    private final int id; //ID
    private final int i;
    private final int j;
    private Map<Face, Boolean> directions; //FACE, BOOLEAN
    private Robot robot; //SE ESTIVER NULL NÃO TEM 1 ROBO AQUI

    public Block(int id, int i, int j) {
        this.id = id;
        this.i = i;
        this.j = j;
    }

    // O bloco base da grid do backend, todos os algoritmos de busca ocorrem em cima da manipulação dele
    private Block(int id, int i, int j, boolean[] connections) {
        this.id = id;
        this.i = i;
        this.j = j;
        this.robot = null;
        /* O Map directions armazena as direções válidas para o bloco e garante que os nós da grid
        serão expandidos no sentido: SOUTH -> WEST -> EAST -> NORTH */
        this.directions = new LinkedHashMap<>();
        this.directions.put(Face.SOUTH, connections[2]);
        this.directions.put(Face.WEST, connections[1]);
        this.directions.put(Face.EAST, connections[3]);
        this.directions.put(Face.NORTH, connections[0]);
    }

    public static Block createBlock(int id, int i, int j, boolean[] connections) {
        return new Block(id, i, j, connections);
    }

    public int getId() {
        return id;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public Map<Face, Boolean> getDirections() {
        return directions;
    }

    public Robot getRobot() {
        return robot;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Block [id=").append(id).append(", coord=(").append(i).append(",").append(j).append(")").append(", directions=").append(directions);
        if (robot != null) {
            sb.append(", robot=").append(robot);
        }
        sb.append("]");
        return sb.toString();
    }
}
