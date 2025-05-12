package com.jmc.entities;

public record Coordinate(int i, int j) {

    @Override
    public String toString() {
        return "Coordinate{" +
                "i=" + i +
                ", j=" + j +
                '}';
    }
}
