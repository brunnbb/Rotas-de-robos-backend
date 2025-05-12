package com.jmc.entities;

import com.jmc.enums.Face;

import java.util.List;
import java.util.Map;

public class Robot {
    private int id;
    private boolean carrying;
    // 1-100 valid packages, -1 not carrying
    private int packageId;
    private boolean postGoal;

    public Robot(int id) {
        this.id = id;
        this.carrying = false;
        this.packageId = -1;
        this.postGoal = false;
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

    private List<Block> goToUnloadingStation(Block[][] warehouseGrid, Block shelf){
        for (Map.Entry<Face, Boolean> entry : shelf.getDirections().entrySet()){
            if(entry.getKey() == Face.EAST && entry.getValue()){

            } else if(entry.getKey() == Face.WEST && entry.getValue()){

            }
        }



        return null;
    }

    private List<Block> ReturnToShelf(Block[][] warehouseGrid, Block shelf){
        return null;
    }

    @Override
    public String toString() {
        return "R" + id;
    }
}
