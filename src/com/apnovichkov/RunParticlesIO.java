package com.apnovichkov;

public class RunParticlesIO {

    //particle parameters
    private static final int NUMBER_OF_BALLS = 100;
    private static final int DOT_DIAMETER = 5;
    private static final int SLOPE_FACTOR = 2;

    //Refresh rate
    private static final int THREAD_REFRESH_RATE = 3;

    //The bigger the speed # the faster
    private static final int DOT_SPEED = 2;

    //Distance at which lines start forming between balls
    private static final int DOT_LINE_DISTANCE = 150;

    //screen size
    public static final int WIDTH = 1410;
//    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;

    public static void main(String[] args) throws InterruptedException {
        MainFrame mf = new MainFrame(WIDTH, HEIGHT, DOT_DIAMETER, DOT_LINE_DISTANCE, NUMBER_OF_BALLS, SLOPE_FACTOR, DOT_SPEED);
        mf.createDots();

        while(true) {
            mf.repaint();
            Thread.sleep(THREAD_REFRESH_RATE);
        }
    }
}
