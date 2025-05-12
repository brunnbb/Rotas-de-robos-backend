package com.jmc.simulation;

import com.jmc.entities.AlgorithmResult;
import com.jmc.entities.Block;
import com.jmc.entities.Grid;
import com.jmc.entities.Robot;
import com.jmc.searches.algorithms.BFS;

public class Workstation {
    public static void main(String[] args) {
        Grid grid = new Grid();
        //grid.createGrid();

        Block[][] warehouseGrid = grid.getGrid();
        BFS bfs = new BFS(warehouseGrid);

        AlgorithmResult result1 = bfs.search(7, 6);
        //System.out.println(result1);
        Block b1 = result1.getFinalPath().getFirst();
        Robot r1 = result1.getClosestRobot();
        r1.goToShelf(warehouseGrid, b1);
        for (Block b : r1.goToUnloadingStation(warehouseGrid, b1)){
            System.out.println(b);
        }
        //System.out.println(r1.ReturnToShelf(warehouseGrid, b1));


//        System.out.println("\n");
//        AlgorithmResult result2 = bfs.search(9, 8);
//        System.out.println(result2);
//        result2.getClosestRobot().goToShelf(warehouseGrid, result2.getRobotPath());
//
//        System.out.println("\n");
//        AlgorithmResult result3 = bfs.search(7, 0);
//        System.out.println(result3);
//        result3.getClosestRobot().goToShelf(warehouseGrid, result3.getRobotPath());
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
