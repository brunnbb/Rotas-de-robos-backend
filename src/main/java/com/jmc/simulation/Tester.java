package com.jmc.simulation;

import com.jmc.entities.*;
import com.jmc.searches.algorithms.*;

public class Tester {
    public static void main(String[] args) {
        Grid grid = new Grid();
        grid.createGrid();
        Block[][] warehouseGrid = grid.getGrid();
//
//        for (int x = 0; x < 13; x++) {
//            for (int y = 0; y < 15; y++) {
//                System.out.print(warehouseGrid[x][y] + "\t");
//            }
//            System.out.println(" ");
//        }

        System.out.println("BFS:");
        BFS bfs = new BFS(warehouseGrid);
        AlgorithmResult result1 = bfs.search(8, 0);
        System.out.println(result1);

//        System.out.println("\n");
//
//        System.out.println("A*:");
//        AStar astar = new AStar(warehouseGrid);
//        AlgorithmResult result2 = astar.search(7, 6);
//        System.out.println(result2);
//
//        System.out.println("\n");
//
//        System.out.println("DFS:");
//        DFS dfs = new DFS(warehouseGrid);
//        AlgorithmResult result3 = dfs.search(7, 6);
//        System.out.println(result3);
    }
}
