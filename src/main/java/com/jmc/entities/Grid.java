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
        boolean[] closed = {false, false, false, false};

        for (int j = 0; j < 15; j++) {
            if (j == 0) {
                this.grid[0][j] = Block.createBlock(0, 0, j, openEast);
            } else if (j == 1 || j == 4 || j == 7 || j == 10 || j == 13) {
                this.grid[0][j] = Block.createBlock(0, 0, j, openWestSouthEast);
            } else if (j == 2 || j == 3 || j == 5 || j == 6 || j == 8 || j == 9 || j == 11 ||  j== 12) {
                this.grid[0][j] = Block.createBlock(0, 0, j, openWestEast);
            } else {
                this.grid[0][j] = Block.createBlock(0, 0, j, openWest);
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

        for (int j = 0; j < 15; j++) {
            if (j == 0) {
                this.grid[11][j] = Block.createBlock(0, 11, j, openSouthEast);
            } else if (j == 1 || j == 4 ) {
                this.grid[11][j] = Block.createBlock(0, 11, j, openAllDirections);
            } else if (j == 2 || j == 3) {
                this.grid[11][j] = Block.createBlock(0, 11, j, openWestSouthEast);
            } else if (j == 5 || j == 6 || j == 8 || j == 9 || j == 11 || j == 12) {
                this.grid[11][j] = Block.createBlock(0, 11, j, openWestEast);
            } else if (j == 7 || j == 10 || j == 13) {
                this.grid[11][j] = Block.createBlock(0, 11, j, openNorthWestEast);
            } else {
                this.grid[11][j] = Block.createBlock(101, 11, j, openWest);
            }
        }

        for (int j = 0; j < 15; j++) {
            if (j < 5) {
                this.grid[12][j] = Block.createBlock(0, 12, j, openNorth);
                this.grid[12][j].setRobot(new Robot(j + 1));
            } else {
                this.grid[12][j] = Block.createBlock(-1, 12, j, closed);
            }
        }

    }

}
