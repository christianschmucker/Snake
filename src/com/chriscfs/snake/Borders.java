package com.chriscfs.snake;

import java.util.ArrayList;
import java.util.Random;

public class Borders {
    private GUI gui;
    public static ArrayList<int[]> bordersList = new ArrayList<>();

    public Borders(GUI gui){
        this.gui = gui;
    }
    public void createWorld(String world){
        bordersList = new ArrayList<>();
        switch (world) {
            case "Random":
                createRandom();
                break;
            case "Portico":
                createPortico();
                break;
            case "Basic World":
                createBasicWorld();
                break;
        }
    }

    private void createBasicWorld(){
        bordersList = new ArrayList<>();
    }

    private void createPortico(){
        int distance = 4 * gui.size;
        int top = 4 * gui.size;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                bordersList.add(new int[]{distance, top});
                bordersList.add(new int[]{distance + gui.size, top});
                bordersList.add(new int[]{distance, top + gui.size});
                bordersList.add(new int[]{distance + gui.size, top + gui.size});

                distance = distance + 10 * gui.size;
            }
            top += 6 * gui.size;
            distance = 4 * gui.size;
        }
    }

    private void createRandom(){
        Random random = new Random();
        int randX;
        int randY;
        int posX;
        int posY;

        for (int i = 0; i < 50; i++) {
            randX = random.nextInt(800 - gui.size + 1);
            randY = random.nextInt(800 - gui.size + 1);
            posX = randX - (randX % gui.size);
            posY = randY - (randY % gui.size);
            bordersList.add(new int[]{posX, posY});
        }

    }

}
