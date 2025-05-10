package com.jmc.entities;

import com.jmc.enums.Face;

import java.util.HashMap;
import java.util.Map;


public class Block {
    private final int id; //ID
    private final Map<Face, Boolean> directions; //FACE, BOOLEAN
    private Robot robot; //SE ESTIVER NULL NÃO TEM 1 ROBO AQUI

    private Block(int id, boolean north, boolean west, boolean south, boolean east) {
        this.id = id;
        this.directions = new HashMap<>();
        this.robot = null;
        this.directions.put(Face.NORTH, north);
        this.directions.put(Face.WEST, west);
        this.directions.put(Face.SOUTH, south);
        this.directions.put(Face.EAST, east);
    }

    public static Block createBlock(int id, boolean[] connections) {
        return new Block(id, connections[0], connections[1], connections[2], connections[3]);
    }

    public int getId() {
        return id;
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
        sb.append("Block [id=").append(id).append(", directions=").append(directions).append("]");
        if (robot != null) {
            sb.append(", robot=").append(robot);
        }
        return sb.toString();
    }
}
