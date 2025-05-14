package com.jmc.simulation;

import com.jmc.entities.AlgorithmResult;
import com.jmc.entities.Block;
import com.jmc.entities.Grid;
import com.jmc.entities.Robot;
import com.jmc.searches.algorithms.AStar;
import com.jmc.searches.algorithms.BFS;
import com.jmc.searches.algorithms.DFS;
import com.jmc.searches.algorithms.IterativeDeepening;

// Essa classe foi criada para analisar e testar a lógica dos algoritmos de busca
public class Workstation {
    public static void main(String[] args) {
        Grid grid = new Grid();
        grid.createGrid();
        Block[][] warehouseGrid = grid.getGrid();

        BFS bfs = new BFS(warehouseGrid);
        DFS dfs = new DFS(warehouseGrid);
        AStar aStar = new AStar(warehouseGrid);
        IterativeDeepening iddfs = new IterativeDeepening(warehouseGrid);

        AlgorithmResult result1 = bfs.search(7, 3);
        System.out.println(result1);

        System.out.println("\n");

        AlgorithmResult result2 = dfs.search(9, 6);
        System.out.println(result2);

        System.out.println("\n");

        AlgorithmResult result3 = iddfs.search(9, 3);
        System.out.println(result3);

//        for (int x = 0; x < 13; x++) {
//            for (int y = 0; y < 15; y++) {
//                System.out.print(warehouseGrid[x][y] + "\t");
//            }
//            System.out.println(" ");
//        }
    }
}
