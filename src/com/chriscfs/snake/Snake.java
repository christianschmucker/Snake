package com.chriscfs.snake;

import javax.sound.sampled.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Snake {
    public static boolean alive = false;
    public static boolean playing = false;
    public Timer move;
    public int score = 0;
    private ArrayList<int[]> body;
    private final GUI gui;
    private final int size;
    private String direction = "DOWN";  //current movement direction

    private Apple apple;

    public Snake(int size, GUI gui){
        this.gui = gui;
        this.size = size;

        body = new ArrayList<>();
        move = new Timer();

        // add head an tail to body
        body.add(new int[]{400 - size, 400 - size});
        body.add(new int[]{400 - size, 400 - 2 * size});
    }

    void start(){
        alive = true;
        playing = true;
        apple = gui.apple;
        move.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                switch (direction) {
                    case "UP":
                        moveUp();
                        break;
                    case "DOWN":
                        moveDown();
                        break;
                    case "LEFT":
                        moveLeft();
                        break;
                    case "RIGHT":
                        moveRight();
                        break;
                }

            }
        }, 0, 100);
    }

    private void moveUp(){
        int[] head = body.get(0);
        checkStep(new int[]{head[0], head[1] - size});
    }

    private void moveDown(){
        int[] head = body.get(0);
        checkStep(new int[]{head[0], head[1] + size});
    }

    private void moveLeft(){
        int[] head = body.get(0);
        checkStep(new int[]{head[0] - size, head[1]});
    }

    private void moveRight(){
        int[] head = body.get(0);
        checkStep(new int[]{head[0] + size, head[1]});
    }

    // checks if move is possible and adds tail or kills snake
    private void checkStep(int[] step){
        // bites into tail
        for (int[] ints : body) {
            if (ints[0] == step[0] && ints[1] == step[1]) {
                dead();
                return;
            }
        }
        // runs into wall
        for (int i = 0; i < Borders.bordersList.size(); i++) {
            if(Borders.bordersList.get(i)[0] == step[0] && Borders.bordersList.get(i)[1] == step[1]){
                dead();
                return;
            }
        }
        if(step[0] < 0 || step[0] > 800 - size ||
                step[1] < 0 || step[1] > 800 - size) {
            dead();     //snake dies
        }else if(step[0] == apple.position[0] && step[1] == apple.position[1]){
            moveStep(step, 1);  // snake eats an apple
        }else{
            moveStep(step, 0);  // snake moves without eating an apple
        }
    }

    // int eat helps adding tail if snake eats an apple
    private void moveStep(int[] step, int eat){
        ArrayList<int[]> newBody = new ArrayList<>();
        for (int i = 0; i < body.size() + eat; i++) {
            if(i == 0){
                newBody.add(step);
            }else{
                newBody.add(body.get(i - 1));
            }
        }
        if(eat == 1) {
            apple.setPosition();
            score++;
            // play eating sound
            if(gui.sound){
                new Thread(() -> {
                    try {
                        Clip clip = AudioSystem.getClip();
                        clip.open(AudioSystem.getAudioInputStream(
                                new File(getClass().getResource("Music/apple.wav").getPath()))
                        );
                        clip.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
        body = newBody;
        gui.repaint();
    }

    private void dead(){
        // delete body
        body.clear();
        alive = false;
        direction = "DOWN";
        move.cancel();

        // play game over sound
        if(gui.sound){
            new Thread(() -> {
                try {
                    Clip clip = AudioSystem.getClip();
                    clip.open(AudioSystem.getAudioInputStream(
                            new File(getClass().getResource("gameOver.wav").getPath()))
                    );
                    clip.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
        // add head an tail to body
        body.add(new int[]{400 - size, 400 - size});
        body.add(new int[]{400 - size, 400 - 2 * size});
        gui.repaint();
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public ArrayList<int[]> getBody() {
        return body;
    }
}
