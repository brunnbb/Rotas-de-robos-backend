package com.jmc;

import com.jmc.algorithms.*;
import com.jmc.entities.*;
import com.jmc.enums.*;

public class Tester {
    public static void main(String[] args) {
        Block[][] grid = Grid.createGrid();
        for (int x = 0; x < 13; x++) {
            for (int y = 0; y < 15; y++) {
                System.out.print(grid[x][y] + "\t");
            }
            System.out.println(" ");
        }
    }
}
