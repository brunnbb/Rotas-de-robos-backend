package com.jmc.searches.algorithms;

import com.jmc.entities.AlgorithmResult;
import com.jmc.entities.Block;
import com.jmc.searches.Search;

public class AStar extends Search {

    protected AStar(Block[][] warehouseGrid) {
        super(warehouseGrid);
    }

    @Override
    public AlgorithmResult search(int shelfI, int shelfJ) {
        return null;
    }
}
