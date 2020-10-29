package com.apnovichkov;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class ParticlePanel extends JPanel{
    private static final long serialVersionUID = 1L;

    //screen height
    private int width;
    private int height;

    //customization parameters
    protected boolean toDrawLines = false;
    protected boolean toReactToNumberOfConnections = false;
    protected boolean toReactToPositionWithSize = false;
    protected boolean toReactToPositionWithColor = false;
    protected boolean toReactToPositionWithSpeed = false;
    protected boolean toBounceOffParticles = false;
    protected boolean toRandomizeSpeed = false;

    //dot parameters
    private final int DOT_LINE_DISTANCE;
    private int defaultDotDiameter;
    private Dot[] dots;

    //center dot
    Dot centerDot;

    //random constants
    private final int MAX_OPACITY = 255;


    private int gameTime;

    public ParticlePanel(int width, int height, Dot[] dots, int dotDiameter, int dot_line_distance) {
        this.width = width;
        this.height = height;
        this.dots = dots;
        this.defaultDotDiameter = dotDiameter;
        this.DOT_LINE_DISTANCE = dot_line_distance;
        this.setBackground(new Color(43,57,79));

        gameTime = 0;

        centerDot = new Dot(width, height, dotDiameter, 0, 0,  dots.length);
        centerDot.setXPosition(width/2);
        centerDot.setYPosition(height/2);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics = (Graphics2D)g;

        //traversing through every dot
        for(int i = 0; i < dots.length; i++) {
            //linksCount = 0;
            Dot currentDot = dots[i];

            //calling specific things we want to do with the dots
            if(toDrawLines) {
                drawLinesBetweenDots(graphics, currentDot);
            }

            if(toReactToPositionWithSize){
                reactionToDotPositionWithSize(graphics, currentDot);
            }else {
//                resetDotSize();
            }

            if(toBounceOffParticles){
                bounceOffDots(graphics, currentDot);
            }

            if(toReactToPositionWithColor){
                reactionToDotPositionWithColor(graphics, currentDot);
            }else {
//                resetDotColor();
            }

            //draw dot
            graphics.setColor(currentDot.myColor);
            graphics.fillOval(currentDot.getXPosition(), currentDot.getYPosition(), currentDot.getDotDiameter(), currentDot.getDotDiameter());

            //move all the dots
            //Add different ways that the dots move
            if(gameTime%currentDot.getSpeed() == 0) {
                if(toReactToPositionWithSpeed) {
                    reactionToDotPositionWithSpeed(graphics, currentDot);
                }else if(!toRandomizeSpeed){
                    resetSpeed();
                }

                if(toRandomizeSpeed) {
                    randomizeSpeed(graphics, currentDot);
                }else if(!toReactToPositionWithSpeed){
                    resetSpeed();
                }

                // Move the dot
                currentDot.moveBounce();
            }
        }

        gameTime++;
    }

    //Options functions
    //------------------
    private void drawLinesBetweenDots(Graphics2D graphics, Dot currentDot) {
        for(int j = currentDot.getId()+1; j < dots.length; j++) {
            int dotDistance = (int)getDistance(currentDot, dots[j]);
            if(dotDistance <= DOT_LINE_DISTANCE && dotDistance != 0.0) {
                currentDot.incrementNumOfLinks();

                int lineOpacity = MAX_OPACITY - ((MAX_OPACITY-(int)DOT_LINE_DISTANCE)+(int)dotDistance);

                //maybe fuck with this later, add some listener to randomize the color of the lines
                Color lineColor = new Color(255,255,255, lineOpacity);

                graphics.setColor(lineColor);
                graphics.drawLine(currentDot.getXPosition()+currentDot.getDotDiameter()/2, currentDot.getYPosition()+currentDot.getDotDiameter()/2,
                        dots[j].getXPosition()+dots[j].getDotDiameter()/2, dots[j].getYPosition()+dots[j].getDotDiameter()/2);
            }
        }
    }

    private void bounceOffDots(Graphics2D graphics, Dot currentDot){
        // Run through all dots
        int randomRed = (int) (Math.random()*255);
        int randomGreen = (int) (Math.random()*255);
        int randomBlue = (int) (Math.random()*255);

        for(Dot dot: dots){
            if(!currentDot.equals(dot)){
                if(currentDot.isTouchingOtherDot(dot)){
//                System.out.println("Dots are touching, reversing their directions");

                    dot.reverseDirection(true);
                    currentDot.reverseDirection(true);
                    Color color = new Color(randomRed, randomGreen, randomBlue);
//                System.out.println("Color of dot before bouncing: " + currentDot.myColor);
                    currentDot.myColor = color;
//                System.out.println("Color of dot after bouncing: " + currentDot.myColor);

                    currentDot.setDotDiameter(currentDot.getDotDiameter() + 2 * (int)(Math.pow(-1, (int) (Math.random() * 2))));

                    break;
                }
            }
        }
    }

    private void reactionToDotPositionWithSize(Graphics2D graphics, Dot currentDot) {

        graphics.setColor(Color.white);

        int maxDotSize = 40;

        int x = currentDot.getXPosition();
        int y = currentDot.getYPosition();

        int maxXPlusY = width+height;
        int maxDistanceFromCenter = width/2;


        int biggerRightSideDiameter = (x+y)/(maxXPlusY/maxDotSize);
        int biggerLeftSideDiameter = maxDotSize-(x+y)/(maxXPlusY/maxDotSize);
        int smallerInTheMiddleDiameter = (int) (getDistance(currentDot, centerDot)/(maxDistanceFromCenter/maxDotSize));
        int biggerInTheMiddleDiameter = maxDotSize - (int) (getDistance(currentDot, centerDot)/(maxDistanceFromCenter/maxDotSize));


        currentDot.setDotDiameter(biggerInTheMiddleDiameter);
    }

    private void reactionToDotPositionWithColor(Graphics2D graphics, Dot currentDot) {
        int maxColorNum = 255;
		int red = Math.abs((currentDot.getXPosition()/((width+200)/maxColorNum))%maxColorNum);
		int green = Math.abs((currentDot.getYPosition()/((height+100)/maxColorNum))%maxColorNum);
		int blue = Math.abs(((currentDot.getXPosition()+currentDot.getYPosition())/((width+height+100)/maxColorNum))%255);

        int randomRed = (int) (Math.random()*255);
        int randomGreen = (int) (Math.random()*255);
        int randomBlue = (int) (Math.random()*255);

        //System.out.println("Math.random: " + Math.random());
        //System.out.println("Math.random()*255: " + (int)( Math.random()*255));

        Color dotColor = new Color(red, green, blue);
//        Color partyDotColor = new Color(randomRed, randomGreen, randomBlue);
        currentDot.myColor = dotColor;
    }


    //FINISHED FUNCTION
    private void reactionToDotPositionWithSpeed(Graphics2D graphics, Dot currentDot) {
        //the smaller the #, the faster the speed
        int minSpeed = 7;
        int maxSpeed = 1;
        int maxDistanceFromCenter = width/2+200;

        int fasterInTheMiddleSpeed = (int) (getDistance(currentDot, centerDot)/(maxDistanceFromCenter/minSpeed) + maxSpeed);
        //int fasterOnTheOutsideSpeed = (minSpeed - (int) (getDistance(currentDot, centerDot)/(maxDistanceFromCenter/minSpeed)))+1;

        currentDot.setSpeed(fasterInTheMiddleSpeed);
    }


    private void randomizeSpeed(Graphics2D graphics, Dot currentDot) {
        //The smaller the speed value the faster the speed
        int minSpeed = 5;
        int howOftenToChangeSpeed = 400;

        if(gameTime % howOftenToChangeSpeed == 0) {
            currentDot.setSpeed((int)(Math.random()*minSpeed) + 1);
        }
    }




    // HELPER FUNCTIONS
    //------------------
    private double getDistance(Dot dot1, Dot dot2) {
        return Math.sqrt(Math.pow((dot1.getXPosition() - dot2.getXPosition()), 2.0) + Math.pow((dot1.getYPosition() - dot2.getYPosition()), 2.0));
    }

    private void resetDotSize() {
        for(Dot dot: dots) {
            dot.setDotDiameter(defaultDotDiameter);
        }
    }

    private void resetDotColor() {
        for(Dot dot: dots) {
            dot.myColor = dot.defaultColor;
        }
    }

    private void resetSpeed() {
        for(Dot dot: dots) {
            dot.setSpeed(dot.defaultSpeed);
        }
    }

    protected void reshuffleDots() {
        System.out.println("Reshuffle dots");
        for(int i = 0; i < dots.length; i++) {
            dots[i].xDirectionScale = (int) ((Math.random()*dots[i].SLOPE_FACTOR+1)*(Math.pow(-1, (int)(Math.random()*10))));
            dots[i].yDirectionScale = (int) ((Math.random()*dots[i].SLOPE_FACTOR+1)*(Math.pow(-1, (int)(Math.random()*10))));
        }
    }

}
