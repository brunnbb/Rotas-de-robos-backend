package com.jmc.entities;

import com.jmc.enums.Face;

public class Robot {
    private int id;
    private boolean carrying;
    // 1-100 valid packages, -1 not carrying
    private int packageId;
    private boolean postGoal;
    // robot path to get to the shelf and then the goal
    // private Face[] path;

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

    @Override
    public String toString() {
        return "R" + id ;
    }
}
