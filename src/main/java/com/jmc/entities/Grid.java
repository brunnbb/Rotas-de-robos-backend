package com.jmc.entities;

public class Grid {
    private final Block[][] grid;

    public Grid() {
        this.grid = new Block[13][15];
    }

    public Block[][] getGrid() {
        return grid;
    }

    public void createGrid() {
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
                this.grid[0][i] = Block.createBlock(0, 0, i, openEast);
            } else if (i == 1 || i == 4 || i == 7 || i == 10 || i == 13) {
                this.grid[0][i] = Block.createBlock(0, 0, i, openWestSouthEast);
            } else if (i == 2 || i == 3 || i == 5 || i == 6 || i == 8 || i == 9 || i == 11 || i == 12) {
                this.grid[0][i] = Block.createBlock(0, 0, i, openWestEast);
            } else {
                this.grid[0][i] = Block.createBlock(0, 0, i, openWest);
            }
        }

        for (int i = 1; i < 11; i++) {
            int shelfId = i;
            for (int j = 0; j < 15; j++) {
                if (j == 1 || j == 4 || j == 7 || j == 10 || j == 13) {
                    this.grid[i][j] = Block.createBlock(0, i, j, openAllDirections);
                } else if (j == 0 || j == 3 || j == 6 || j == 9 || j == 12) {
                    this.grid[i][j] = Block.createBlock(shelfId, i, j, openEast);
                    shelfId += 10;
                } else {
                    this.grid[i][j] = Block.createBlock(shelfId, i, j, openWest);
                    shelfId += 10;
                }
            }
        }

        for (int i = 0; i < 15; i++) {
            if (i == 0) {
                this.grid[11][i] = Block.createBlock(0, 11, i, openSouthEast);
            } else if (i == 1 || i == 4 || i == 7 || i == 10 || i == 13) {
                this.grid[11][i] = Block.createBlock(0, 11, i, openAllDirections);
            } else if (i == 2 || i == 3) {
                this.grid[11][i] = Block.createBlock(0, 11, i, openWestSouthEast);
            } else if (i == 5 || i == 6 || i == 8 || i == 9 || i == 11 || i == 12) {
                this.grid[11][i] = Block.createBlock(0, 11, i, openWestSouthEast);
            } else {
                this.grid[11][i] = Block.createBlock(101, 11, i, openWestSouth);
            }
        }

        for (int i = 0; i < 15; i++) {
            if (i < 5) {
                this.grid[12][i] = Block.createBlock(0, 12, i, openNorth);
                this.grid[12][i].setRobot(new Robot(i + 1));
            } else {
                this.grid[12][i] = Block.createBlock(-1, 12, i, openNorthWestEast);
            }
        }

    }

}
