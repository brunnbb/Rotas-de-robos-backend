package com.jmc.entities;

import com.jmc.enums.Face;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Robot {
    private int id;
    private boolean carrying;
    // 1-100 valid packages, -1 not carrying
    private int packageId;
    private boolean postGoal;
    private List<Block> pathToUnloadingStation;

    public Robot(int id) {
        this.id = id;
        this.carrying = false;
        this.packageId = -1;
        this.postGoal = false;
        this.pathToUnloadingStation = new ArrayList<Block>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCarrying() {
        return carrying;
    }

    public void setCarrying(boolean carrying) {
        this.carrying = carrying;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public boolean isPostGoal() {
        return postGoal;
    }

    public void setPostGoal(boolean postGoal) {
        this.postGoal = postGoal;
    }

    public void goToShelf(Block[][] warehouseGrid, Block shelf) {
        warehouseGrid[shelf.getI()][shelf.getJ()].setRobot(this);
    }

    public List<Block> goToUnloadingStation(Block[][] warehouseGrid, Block shelf) {
        pathToUnloadingStation = new ArrayList<>();
        pathToUnloadingStation.add(shelf);

        int i = shelf.getI();
        int j = shelf.getJ();

        for (Map.Entry<Face, Boolean> entry : shelf.getDirections().entrySet()) {
            if (entry.getKey() == Face.EAST && entry.getValue()) {
                pathToUnloadingStation.add(warehouseGrid[i][j + 1]);
            } else if (entry.getKey() == Face.WEST && entry.getValue()) {
                pathToUnloadingStation.add(warehouseGrid[i][j - 1]);
            }
        }

        i = pathToUnloadingStation.getLast().getI();
        j = pathToUnloadingStation.getLast().getJ();

        while (i < 11) {
            pathToUnloadingStation.add(warehouseGrid[i + 1][j]);
            i++;
        }

        while (j < 14) {
            pathToUnloadingStation.add(warehouseGrid[i][j + 1]);
            j++;
        }

        warehouseGrid[11][14].setRobot(shelf.getRobot());
        shelf.setRobot(null);

        return pathToUnloadingStation;
    }

    public List<Block> ReturnToShelf(Block[][] warehouseGrid, Block shelf) {
        List<Block> pathToReturnToShelf = new ArrayList<>(this.pathToUnloadingStation);
        Collections.reverse(pathToReturnToShelf);

        shelf.setRobot(warehouseGrid[11][14].getRobot());
        warehouseGrid[11][14].setRobot(null);

        return pathToReturnToShelf;
    }

    @Override
    public String toString() {
        return "R" + id;
    }
}
