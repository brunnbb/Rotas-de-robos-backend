package com.jmc;

import com.jmc.entities.*;
import com.jmc.searches.algorithms.BFS;

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
        System.out.println(bfs.bfs(8,3));
    }
}
