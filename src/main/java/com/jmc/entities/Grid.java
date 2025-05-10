package com.jmc.entities;

public class Grid {

    public static Block[][] createGrid() {
        Block[][] grid = new Block[13][15];
        // north, west, south, east
        boolean[] openEast = {false, false, false, true};
        boolean[] openWestSouthEast = {false, true, true, true};
        boolean[] openWestEast = {false, true, false, true};
        boolean[] openWest = {false, true, false, false};
        boolean[] openWestSouth = {false, true, true, false};
        boolean[] openNorthWestEast = {true, true, false, true};
        boolean[] openSouthEast = {false, false, true, true};
        boolean[] openNorth = {true, false, false, false};
        boolean[] openAllDirections = {true, true, true, true};

        for (int i = 0; i < 15; i++) {
            if (i == 0) {
                grid[0][i] = Block.createBlock(0, openEast);
            } else if (i == 1 || i == 4 || i == 7 || i == 10 || i == 13) {
                grid[0][i] = Block.createBlock(0, openWestSouthEast);
            } else if (i == 2 || i == 3 || i == 5 || i == 6 || i == 8 || i == 9 || i == 11 || i == 12) {
                grid[0][i] = Block.createBlock(0, openWestEast);
            } else {
                grid[0][14] = Block.createBlock(0, openWest);
            }
        }

        for (int i = 1; i < 11; i++) {
            int shelfId = i;
            for (int j = 0; j < 15; j++) {
                if (j == 1 || j == 4 || j == 7 || j == 10 || j == 13) {
                    grid[i][j] = Block.createBlock(0, openAllDirections);
                } else if (j == 0 || j == 3 || j == 6 || j == 9 || j == 12) {
                    grid[i][j] = Block.createBlock(shelfId, openEast);
                    shelfId += 10;
                } else {
                    grid[i][j] = Block.createBlock(shelfId, openWest);
                    shelfId += 10;
                }
            }
        }

        for (int i = 0; i < 15; i++) {
            if (i == 0) {
                grid[11][i] = Block.createBlock(0, openSouthEast);
            } else if (i == 1 || i == 4 || i == 7 || i == 10 || i == 13) {
                grid[11][i] = Block.createBlock(0, openAllDirections);
            } else if (i == 2 || i == 3) {
                grid[11][i] = Block.createBlock(0, openWestSouthEast);
            } else if (i == 5 || i == 6 || i == 8 || i == 9 || i == 11 || i == 12) {
                grid[11][i] = Block.createBlock(0, openWestSouthEast);
            } else {
                grid[11][i] = Block.createBlock(101, openWestSouth);
            }
        }

        for (int i = 0; i < 15; i++) {
            if (i < 5) {
                grid[12][i] = Block.createBlock(0, openNorth);
                grid[12][i].setRobot(new Robot(i+1));
            } else {
                grid[12][i] = Block.createBlock(-1, openNorthWestEast);
            }
        }

        return grid;
    }

}
