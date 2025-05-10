package com.jmc.enums;

public enum Face {
    NORTH("North"),
    SOUTH("South"),
    EAST("East"),
    WEST("West");

    public String face;

    Face(String face) {
        this.face = face;
    }

    public String getFace() {
        return face;
    }
}