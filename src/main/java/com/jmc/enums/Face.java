package com.jmc.enums;

/**
 * Representa as quatro direções cardeais principais.
 */
public enum Face {
    NORTH("North"),
    SOUTH("South"),
    EAST("East"),
    WEST("West");

    public final String face;

    Face(String face) {
        this.face = face;
    }
}