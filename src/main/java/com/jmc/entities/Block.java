package com.jmc.entities;

import com.jmc.enums.Face;

import java.util.HashMap;
import java.util.Map;


public class Block {
    private final int id; //ID
    private int x;
    private int y;
    private final Map<Face, Boolean> directions; //FACE, BOOLEAN
    private Robot robot; //SE ESTIVER NULL NÃO TEM 1 ROBO AQUI

    private Block(int id, int x, int y, boolean[] connections) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.directions = new HashMap<>();
        this.robot = null;
        this.directions.put(Face.NORTH, connections[0]);
        this.directions.put(Face.WEST, connections[1]);
        this.directions.put(Face.SOUTH, connections[2]);
        this.directions.put(Face.EAST, connections[3]);
    }

    public static Block createBlock(int id, int x, int y, boolean[] connections) {
        return new Block(id, x, y, connections);
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
        sb.append("Block [id=").append(id).append(", coord=(").append(x).append(",").append(y).append(")").append(", directions=").append(directions);
        if (robot != null) {
            sb.append(", robot=").append(robot);
        }
        sb.append("]");
        return sb.toString();
    }
}
