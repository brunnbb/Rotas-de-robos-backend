package com.jmc.simulation;

import com.jmc.entities.AlgorithmResult;
import com.jmc.entities.Block;
import com.jmc.entities.Grid;
import com.jmc.searches.algorithms.BFS;

public class Workstation {
    public static void main(String[] args) {
        Grid grid = new Grid();
        grid.createGrid();

        Block[][] warehouseGrid = grid.getGrid();
        BFS bfs = new BFS(warehouseGrid);
        AlgorithmResult result1 = bfs.search(7, 6);
        System.out.println(result1);

        System.out.println("\n");
        AlgorithmResult result2 = bfs.search(9, 8);
        System.out.println(result2);

//        System.out.println("\n");
//        AlgorithmResult result3 = bfs.search(7, 0);
//        System.out.println(result3);
//
//        System.out.println("\n");
//        AlgorithmResult result4 = bfs.search(10, 2);
//        System.out.println(result4);
//
//        System.out.println("\n");
//        AlgorithmResult result5 = bfs.search(1, 2);
//        System.out.println(result5);

    }
}
