package com.apnovichkov;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class MainFrame extends JFrame{

    private static final long serialVersionUID = 1L;

    //parameters needed
    private final int SCREEN_WIDTH, SCREEN_HEIGHT, DEFAULT_DOT_DIAMETER, DEFAULT_DOT_LINE_DISTANCE, NUMBER_OF_BALLS;
    private final int SLOPE_FACTOR, PARTICLE_SPEED;
    ParticlePanel pPanel;
    OptionsPanel oPanel;

    private static Dot[] dots;


    public MainFrame(int screenWidth, int screenHeight, int defaultDotDiameter, int defaultDotLineDistance, int numberOfBalls, int slopeFactor, int particleSpeed) {
        this.SCREEN_WIDTH = screenWidth;
        this.SCREEN_HEIGHT = screenHeight;
        this.DEFAULT_DOT_DIAMETER = defaultDotDiameter;
        this.DEFAULT_DOT_LINE_DISTANCE = defaultDotLineDistance;
        this.NUMBER_OF_BALLS = numberOfBalls;
        this.SLOPE_FACTOR = slopeFactor;
        this.PARTICLE_SPEED = particleSpeed;

        setupFrame();
    }

    public void setupFrame() {
        dots = new Dot[NUMBER_OF_BALLS];

        pPanel = new ParticlePanel(SCREEN_WIDTH, SCREEN_HEIGHT, dots, DEFAULT_DOT_DIAMETER, DEFAULT_DOT_LINE_DISTANCE);
        oPanel = new OptionsPanel(pPanel);

        this.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
        this.add(oPanel, BorderLayout.NORTH);
        this.add(pPanel, BorderLayout.CENTER);

        this.setVisible(true);
        this.setTitle("Particle IO");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }



    public void createDots() {
        for(int i = 0; i < NUMBER_OF_BALLS; i++) {
            dots[i] = new Dot(SCREEN_WIDTH, SCREEN_HEIGHT, DEFAULT_DOT_DIAMETER, SLOPE_FACTOR, PARTICLE_SPEED, i);
        }
    }
}
