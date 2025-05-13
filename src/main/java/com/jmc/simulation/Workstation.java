package com.jmc.simulation;

import com.jmc.entities.AlgorithmResult;
import com.jmc.entities.Block;
import com.jmc.entities.Grid;
import com.jmc.entities.Robot;
import com.jmc.searches.algorithms.AStar;
import com.jmc.searches.algorithms.BFS;
import com.jmc.searches.algorithms.DFS;
import com.jmc.searches.algorithms.IterativeDeepening;

public class Workstation {
    public static void main(String[] args) {
        Grid grid = new Grid();
        grid.createGrid();
        Block[][] warehouseGrid = grid.getGrid();

        BFS bfs = new BFS(warehouseGrid);
        DFS dfs = new DFS(warehouseGrid);
        AStar aStar = new AStar(warehouseGrid);
        IterativeDeepening iddfs = new IterativeDeepening(warehouseGrid);

        AlgorithmResult result1 = iddfs.search(7, 3);
        System.out.println(result1);
//        Block shelf1 = result1.getFinalPath().getFirst();
//        Robot r1 = result1.getClosestRobot();
//        r1.goToShelf(warehouseGrid, shelf1);

        System.out.println("\n");

//        AlgorithmResult result2 = dfs.search(9, 6);
//        System.out.println(result2);
//
//        System.out.println("\n");

        AlgorithmResult result3 = iddfs.search(9, 3);
        System.out.println(result3);

        System.out.println("\n");

//        AlgorithmResult result4 = dfs.search(9, 11);
//        System.out.println(result4);
//
//        System.out.println("\n");

    }
}
