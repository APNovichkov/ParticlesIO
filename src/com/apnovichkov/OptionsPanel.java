package com.apnovichkov;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class OptionsPanel extends JPanel{
    private static final long serialVersionUID = 1L;

    //button controls
    private JButton reshuffle;
    private JCheckBox drawLines;
    private JCheckBox reactToParticlePositionWithSize;
    private JCheckBox reactToParticlePositionWithColor;
    private JCheckBox reactToParticlePositionWithSpeed;
    private JCheckBox bounceOffParticles;
    private JCheckBox randomizeSpeed;

    ParticlePanel particlePanel;


    public OptionsPanel(ParticlePanel particlePanel) {
        reshuffle = new JButton("Reshuffle");
        drawLines = new JCheckBox("Draw Lines");
        reactToParticlePositionWithSize = new JCheckBox("React to Position with Size");
        reactToParticlePositionWithColor = new JCheckBox("React to Position with Color");
        reactToParticlePositionWithSpeed = new JCheckBox("React to Position with Speed");
        bounceOffParticles = new JCheckBox("Bounce off Particles");
        randomizeSpeed = new JCheckBox("Randomize speed");


        this.particlePanel = particlePanel;

        setupPanel();
    }


    private void setupPanel() {
        reshuffle.addActionListener(reshuffleListener);
        drawLines.addActionListener(drawLinesListener);
        reactToParticlePositionWithSize.addActionListener(reactToParticlePositionWithSizeListener);
        reactToParticlePositionWithColor.addActionListener(reactToParticlePositionWithColorListener);
        reactToParticlePositionWithSpeed.addActionListener(reactToParticlePositionWithSpeedListener);
        bounceOffParticles.addActionListener(bounceOffParticlesListener);
        randomizeSpeed.addActionListener(randomizeSpeedListener);
        this.add(reshuffle);
        this.add(drawLines);
        this.add(reactToParticlePositionWithSize);
        this.add(reactToParticlePositionWithColor);
        this.add(reactToParticlePositionWithSpeed);
        this.add(randomizeSpeed);
        this.add(bounceOffParticles);
    }


    ActionListener reshuffleListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Reshuffling dots!!");
            particlePanel.reshuffleDots();
        }
    };

    ActionListener drawLinesListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Going to draw lines between particles");
            particlePanel.toDrawLines = (particlePanel.toDrawLines) ? false : true;
        }
    };

    ActionListener reactToParticlePositionWithSizeListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Reacting to particle position with size");
            particlePanel.toReactToPositionWithSize = (particlePanel.toReactToPositionWithSize) ? false : true;
        }
    };

    ActionListener reactToParticlePositionWithColorListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Reacting to particle position with color");
            particlePanel.toReactToPositionWithColor = (particlePanel.toReactToPositionWithColor) ? false : true;
        }
    };

    ActionListener  reactToParticlePositionWithSpeedListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Reacting to particle position with color");
            particlePanel.toReactToPositionWithSpeed = (particlePanel.toReactToPositionWithSpeed) ? false : true;
        }
    };

    ActionListener bounceOffParticlesListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Bouncing off other particles");
            particlePanel.toBounceOffParticles = (particlePanel.toBounceOffParticles) ? false : true;
        }
    };

    ActionListener randomizeSpeedListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Randomizing speed");
            particlePanel.toRandomizeSpeed = (particlePanel.toRandomizeSpeed) ? false : true;
        }
    };


}
