package com.chriscfs.snake;

import java.util.Random;

public class Apple {
    int[] position;
    Snake snake;
    GUI gui;

    public Apple(Snake snake, GUI gui){
        this.snake = snake;
        this.gui = gui;
    }

    public void setPosition(){
        Random random = new Random();
        int randX = random.nextInt(800 - gui.size + 1);
        int randY = random.nextInt(800 - gui.size + 1);
        int posX = randX - (randX % gui.size);
        int posY = randY - (randY % gui.size);

        while(!check(posX, posY)){
            randX = random.nextInt(800 - gui.size + 1);
            randY = random.nextInt(800 - gui.size + 1);
            posX = randX - (randX % gui.size);
            posY = randY - (randY % gui.size);
        }
        position = new int[]{posX, posY};
    }

    // checks if snake contains pos or borders are in the way (returns true if pos is free)
    public boolean check(int posX, int posY){
        for (int i = 0; i < snake.getBody().size(); i++) {
            if(snake.getBody().get(i)[0] == posX && snake.getBody().get(i)[1] == posY){
                return false;
            }
        }

        for (int i = 0; i < Borders.bordersList.size(); i++) {
            if(Borders.bordersList.get(i)[0] == posX && Borders.bordersList.get(i)[1] == posY){
                return false;
            }
        }
        return true;
    }
}
