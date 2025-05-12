package com.jmc.simulation;

import com.jmc.entities.*;
import com.jmc.searches.algorithms.*;

public class Tester {
    public static void main(String[] args) {
        Grid grid = new Grid();
        grid.createGrid();
        Block[][] warehouseGrid = grid.getGrid();
//        for (int x = 0; x < 13; x++) {
//            for (int y = 0; y < 15; y++) {
//                System.out.print(warehouseGrid[x][y] + "\t");
//            }
//            System.out.println(" ");
//        }
        BFS bfs = new BFS(warehouseGrid);
        System.out.println(bfs.search(7, 6));

        System.out.println("\n");

        DFS dfs = new DFS(warehouseGrid);
        System.out.println(dfs.search(7,6));
    }
}
