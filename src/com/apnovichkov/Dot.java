package com.apnovichkov;

import java.awt.Color;

public class Dot {
    final int OFFSET = 50;

    final int EDGE_OFFSET=100;

    int xDirectionCounter = 0;
    int yDirectionCounter = 0;

    int xDirectionScale;
    int yDirectionScale;

    final int SLOPE_FACTOR;

    private int dotDiameter;

    private int xPosition;
    private int yPosition;

    private int linksCount;
    private int myId;
    public int mySpeed;


    public Color myColor;
    public Color defaultColor = Color.white;
    public int defaultSpeed = 5;

    int screenWidth;
    int screenHeight;

    public Dot(int screenWidth, int screenHeight, int dotDiameter, int slope_factor, int speed, int dotId) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.dotDiameter = dotDiameter;
        this.myId = dotId;
        this.SLOPE_FACTOR = slope_factor;
        this.mySpeed = speed;

        setupDot();
    }


    private void setupDot() {
        xPosition = (int) (Math.random()*(screenWidth+EDGE_OFFSET));
        yPosition = (int) (Math.random()*(screenHeight+EDGE_OFFSET));

        xDirectionScale = (int) (((Math.random()*SLOPE_FACTOR+1) + mySpeed)*(Math.pow(-1, (int)(Math.random()*10))));
        yDirectionScale = (int) (((Math.random()*SLOPE_FACTOR+1) + mySpeed)*(Math.pow(-1, (int)(Math.random()*10))));

        linksCount = 0;
        myColor = Color.white;
    }

    public boolean isTouchingOtherDot(Dot dot){
        int touchingLimit = 5;

        int otherDotX = dot.getXPosition();
        int otherDotY = dot.getYPosition();

        if ((Math.abs((this.xPosition - otherDotX)) <= touchingLimit) && Math.abs((this.yPosition - otherDotY)) <= touchingLimit){
            System.out.println("----");
            System.out.println("Dots are touching");
            System.out.println("this.x: " + this.xPosition + "other.x: " + otherDotX);
            System.out.println("this.y: " + this.yPosition + "other.y: " + otherDotY);
            System.out.println("----");
            return true;
        }

        return false;
    }

    public void reverseDirection(boolean withRandomAdded){
        int randomLimit = 1;
        int xChange = 0;
        int yChange = 0;

        if(withRandomAdded){
            xChange = (int) (Math.random() * randomLimit) * (-1 * (int) (Math.random()) + 1);
            yChange = (int) (Math.random() * randomLimit) * (-1 * (int) (Math.random()) + 1);
        }

        this.yDirectionScale = -this.yDirectionScale + xChange;
        this.xDirectionScale = -this.xDirectionScale + yChange;
    }

    public void moveBounce() {
        moveAndBounceOffWalls();
    }

    private void moveAndBounceOffWalls() {
        if(xPosition >= screenWidth + EDGE_OFFSET || xPosition <= -EDGE_OFFSET) {
            xDirectionScale *= -1;
        }
        if(yPosition >= screenHeight + EDGE_OFFSET || yPosition <= -EDGE_OFFSET) {
            yDirectionScale *= -1;
        }

        xPosition += (xDirectionScale);
        yPosition += (yDirectionScale);
    }




    // Getters and Setters
    //---------------------
    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public int getDotDiameter() {
        return dotDiameter;
    }

    public int getId() {
        return myId;
    }

    public int getLinksCount() {
        return linksCount;
    }

    public int getSpeed() {
        return mySpeed;
    }


    public void setRandomColor() {
        int randomRed = (int) (Math.random()*255);
        int randomGreen = (int) (Math.random()*255);
        int randomBlue = (int) (Math.random()*255);

        this.myColor = new Color(randomRed, randomGreen, randomBlue);
    }


    public void setSpeed(int speed) {
        this.mySpeed = speed;
    }

    public void setXPosition(int value) {
        xPosition = value;
    }

    public void setYPosition(int value) {
        yPosition = value;
    }

    public void setDotDiameter(int value) {
        dotDiameter = value;
    }

    public void incrementNumOfLinks() {
        linksCount++;
    }

    public void decrementNumOfLinks() {
        linksCount--;
    }

    private double getDistance(Dot dot1, Dot dot2) {
        return Math.sqrt(Math.pow((dot1.getXPosition() - dot2.getXPosition()), 2.0) + Math.pow((dot1.getYPosition() - dot2.getYPosition()), 2.0));
    }

}

