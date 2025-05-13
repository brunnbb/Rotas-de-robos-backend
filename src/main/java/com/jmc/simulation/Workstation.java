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
        Block[][] warehouseGrid = grid.getGrid();

        BFS bfs = new BFS(warehouseGrid);
        DFS dfs = new DFS(warehouseGrid);
        AStar aStar = new AStar(warehouseGrid);
        IterativeDeepening iddfs = new IterativeDeepening(warehouseGrid);

        AlgorithmResult result1 = bfs.search(8, 0);
        System.out.println(result1);
        Block shelf1 = result1.getFinalPath().getFirst();
        Robot r1 = result1.getClosestRobot();
        r1.goToShelf(warehouseGrid, shelf1);

        System.out.println("\n");

        AlgorithmResult result2 = bfs.search(7, 2);
        System.out.println(result2);
        Block shelf2 = result2.getFinalPath().getFirst();
        Robot r2 = result2.getClosestRobot();
        r2.goToShelf(warehouseGrid, shelf2);

        System.out.println("\n");

        AlgorithmResult result3 = dfs.search(10, 6);
        System.out.println(result3);
        Block shelf3 = result3.getFinalPath().getFirst();
        Robot r3 = result3.getClosestRobot();
        r3.goToShelf(warehouseGrid, shelf3);

        System.out.println("\n");

        AlgorithmResult result4 = aStar.search(9, 11);
        System.out.println(result4);
        //Block shelf4 = result4.getFinalPath().getFirst();
        //Robot r4 = result4.getClosestRobot();
        //r4.goToShelf(warehouseGrid, shelf4);

        System.out.println("\n");

        AlgorithmResult result5 = iddfs.search(9, 11);
        System.out.println(result5);
        //Block shelf5 = result5.getFinalPath().getFirst();
        //Robot r5 = result5.getClosestRobot();
        //r5.goToShelf(warehouseGrid, shelf4);

        System.out.println("\n");

    }
}
