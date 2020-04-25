package com.chriscfs.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JPanel implements ActionListener {
    JFrame window;
    Snake snake;
    Apple apple;
    boolean sound = true;
    int size = 20;
    Controls controls;
    String world = "Basic World";
    Borders borders;

    public GUI(){
        //set up gui
        window = new JFrame();
        window.setContentPane(this);
        window.setTitle("Snake");
        window.getContentPane().setPreferredSize(new Dimension(800, 800));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

//        window.addKeyListener(this);
        addKeyBinding(this, KeyEvent.VK_UP, "UP", (evt) -> snake.setDirection("UP"));
        addKeyBinding(this, KeyEvent.VK_DOWN, "DOWN", (evt) -> snake.setDirection("DOWN"));
        addKeyBinding(this, KeyEvent.VK_LEFT, "LEFT", (evt) -> snake.setDirection("LEFT"));
        addKeyBinding(this, KeyEvent.VK_RIGHT, "RIGHT", (evt) -> snake.setDirection("RIGHT"));

        snake = new Snake(size, this);
        apple = new Apple(snake, this);
        apple.setPosition();

        borders = new Borders(this);
        // add controls
        controls = new Controls(this);
        controls.addAll();

        this.revalidate();
        this.repaint();
    }

    public void paintComponent(Graphics g){
        if(controls != null){
            // position the controls
            controls.position();

            if(!Snake.playing){
                // display menu
                for (int i = 0; i < this.getHeight(); i += size) {
                    for (int j = 0; j < this.getWidth(); j += size) {
                        g.setColor(new Color(30, 30, 30));
                        g.fillRect(i, j, size, size);
                    }
                }

                controls.getButton("start").setVisible(true);
                controls.getButton("exit").setVisible(true);
                controls.getButton("sound").setVisible(true);
                controls.getLabel("title").setVisible(true);
                controls.getWorlds().setVisible(true);

            }else if(!Snake.alive){
                // display game over
                controls.getLabel("gameOver").setVisible(true);
                controls.getButton("again").setVisible(true);
                controls.getButton("backToMenu").setVisible(true);

            }else{
                // display game
                controls.getButton("start").setVisible(false);
                controls.getButton("exit").setVisible(false);
                controls.getButton("sound").setVisible(false);
                controls.getButton("again").setVisible(false);
                controls.getButton("backToMenu").setVisible(false);
                controls.getLabel("title").setVisible(false);
                controls.getLabel("gameOver").setVisible(false);
                controls.getWorlds().setVisible(false);

                controls.getLabel("score").setVisible(true);
                controls.getLabel("score").setText("Score: " + snake.score);

                // draws grid
                for (int i = 0; i < this.getHeight(); i += size) {
                    for (int j = 0; j < this.getWidth(); j += size) {
                        g.setColor(new Color(30, 30, 30));
                        g.fillRect(i, j, size, size);
                    }
                }

                // draws borders
                for (int i = 0; i < Borders.bordersList.size(); i++) {
                    g.setColor(new Color(80, 80, 80));

                    g.fillRect(Borders.bordersList.get(i)[0] + 1, Borders.bordersList.get(i)[1] + 1,
                            size - 1, size - 1);
                }

                // draws snake
                for (int i = 0; i < snake.getBody().size(); i++) {
                    // different colors for the head and tail
                    if(i == 0){
                        g.setColor(new Color(250, 200, 40));
                    }else{
                        g.setColor(new Color(40, 200, 80));
                    }

                    g.fillRect(snake.getBody().get(i)[0] + 1, snake.getBody().get(i)[1] + 1, size - 1, size - 1);
                }

                // draws apple
                g.setColor(new Color(200, 30, 50));
                g.fillRect(apple.position[0] + 1, apple.position[1] + 1, size - 1, size - 1);
            }
        }
    }

    public void addKeyBinding(JComponent comp, int keyCode, String id, ActionListener actionListener){
        InputMap inputMap = comp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap ap = comp.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(keyCode, 0, false), id);
        ap.put(id, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionListener.actionPerformed(e);
                comp.repaint();
            }
        });
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand() != null){
            switch(e.getActionCommand()){
                case "Start Game":
                    snake = new Snake(size, this);
                    snake.start();
                    break;
                case "Exit":
                    System.exit(0);
                    break;
                case "Menu":
                    Snake.playing = false;
                    controls.getLabel("gameOver").setVisible(false);
                    controls.getButton("again").setVisible(false);
                    controls.getButton("backToMenu").setVisible(false);
                    controls.getLabel("score").setVisible(false);
                    snake = new Snake(size, this);
                    break;
                case "Again":
                    controls.getLabel("gameOver").setVisible(false);
                    controls.getButton("again").setVisible(false);
                    controls.getButton("backToMenu").setVisible(false);
                    snake = new Snake(size, this);
                    snake.start();
                    break;
                case "comboBoxChanged":
                    JComboBox cb = (JComboBox) e.getSource();
                    world = (String) cb.getSelectedItem();
                    borders.createWorld(world);
                    break;
                case "Sound":
                    controls.getButton("sound")
                            .setText("\uD835\uDDB2̶\uD835\uDDC8̶\uD835\uDDCE̶\uD835\uDDC7̶\uD835\uDDBD̶");
                    sound = false;
                    break;
                case "\uD835\uDDB2̶\uD835\uDDC8̶\uD835\uDDCE̶\uD835\uDDC7̶\uD835\uDDBD̶":       // sound crossed
                    controls.getButton("sound").setText("Sound");
                    sound = true;
                    break;
                default:
                    break;
            }
        }

        repaint();
    }
}
