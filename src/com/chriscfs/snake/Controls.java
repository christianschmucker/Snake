package com.chriscfs.snake;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.util.ArrayList;

// class for menu controls, label, etc.
public class Controls {
    private final GUI gui;
    private final JButton startButton, exitButton, againButton, backToMenuButton, soundButton;
    private final JLabel score, gameOver, title;
    private final JComboBox<String> worldsBox;
    private final ArrayList<JButton> buttons;
    private final ArrayList<JLabel> labels;

    public Controls(GUI gui){
        this.gui = gui;
        buttons = new ArrayList<>();
        labels = new ArrayList<>();

        title = new JLabel();
        title.setText("NEON Snake");
        title.setName("title");
        title.setForeground(new Color(255, 255, 255));
        title.setFont(new Font("arial", Font.BOLD, 50));
        title.setVisible(false);

        gameOver = new JLabel();
        gameOver.setText("Game Over");
        gameOver.setName("gameOver");
        gameOver.setForeground(new Color(255, 255, 255));
        gameOver.setFont(new Font("arial", Font.BOLD, 80));
        gameOver.setVisible(false);

        score = new JLabel();
        score.setText("Score: ");
        score.setName("score");
        score.setForeground(new Color(255, 255, 255));
        score.setFont(new Font("arial", Font.BOLD, 18));
        score.setVisible(false);

        startButton = new JButton();
        startButton.setText("Start Game");
        startButton.setName("start");
        startButton.setFocusable(false);
        startButton.addActionListener(gui);
        startButton.setMargin(new Insets(10,20,10,20));
        startButton.setBackground(new Color(40, 100, 200));
        startButton.setForeground(new Color(255, 255, 255));
        startButton.setFont(new Font("arial", Font.PLAIN, 20));
        startButton.setVisible(false);
        startButton.setVisible(false);

        exitButton = new JButton();
        exitButton.setText("Exit");
        exitButton.setName("exit");
        exitButton.setFocusable(false);
        exitButton.addActionListener(gui);
        exitButton.setMargin(new Insets(10,20,10,20));
        exitButton.setBackground(new Color(200, 30, 50));
        exitButton.setForeground(new Color(255, 255, 255));
        exitButton.setFont(new Font("arial", Font.PLAIN, 20));
        exitButton.setVisible(false);

        soundButton = new JButton();
        soundButton.setText("Sound");
        soundButton.setName("sound");
        soundButton.setFocusable(false);
        soundButton.addActionListener(gui);
        soundButton.setMargin(new Insets(10,20,10,20));
        soundButton.setBackground(new Color(250, 120, 0));
        soundButton.setForeground(new Color(255, 255, 255));
        soundButton.setFont(new Font("arial", Font.PLAIN, 20));
        soundButton.setVisible(false);

        againButton = new JButton();
        againButton.setText("Again");
        againButton.setName("again");
        againButton.setFocusable(false);
        againButton.addActionListener(gui);
        againButton.setMargin(new Insets(10,20,10,20));
        againButton.setBackground(new Color(200, 30, 50));
        againButton.setForeground(new Color(255, 255, 255));
        againButton.setFont(new Font("arial", Font.PLAIN, 20));
        againButton.setVisible(false);

        backToMenuButton = new JButton();
        backToMenuButton.setText("Menu");
        backToMenuButton.setName("backToMenu");
        backToMenuButton.setFocusable(false);
        backToMenuButton.addActionListener(gui);
        backToMenuButton.setMargin(new Insets(10,20,10,20));
        backToMenuButton.setBackground(new Color(200, 30, 50));
        backToMenuButton.setForeground(new Color(255, 255, 255));
        backToMenuButton.setFont(new Font("arial", Font.PLAIN, 20));
        backToMenuButton.setVisible(false);

        worldsBox = new JComboBox<>();
        worldsBox.setName("Worlds");
        worldsBox.addItem("Basic World");
        worldsBox.addItem("Portico");
        worldsBox.addItem("Random");
        worldsBox.setBackground(new Color(100, 50, 220));
        worldsBox.setForeground(new Color(170, 50, 220));
        worldsBox.setFont(new Font("arial", Font.PLAIN, 20));
        worldsBox.setUI(new BasicComboBoxUI() {
            protected JButton createArrowButton() {
                return new JButton() {
                    public int getWidth() {
                        return 0;
                    }
                };
            }
        });
        worldsBox.setRenderer(new DefaultListCellRenderer(){
            public Component getListCellRendererComponent(JList list, Object value,
                                                          int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent (list, value, index, isSelected, cellHasFocus);

                list.setSelectionBackground(new Color(170, 50, 220));
                list.setSelectionForeground(new Color(255, 255, 255));
                setBackground(new Color(100, 50, 220));
                if(isSelected){
                    setBackground(new Color(100, 50, 220));
                }else{
                    setBackground(new Color(170, 50, 220));
                }
                setForeground(new Color(255, 255, 255));
                return c;
            }
        });
        ((JLabel) worldsBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        worldsBox.setVisible(false);
        worldsBox.addActionListener(gui);
        
        labels.add(title);
        labels.add(gameOver);
        labels.add(score);
        buttons.add(startButton);
        buttons.add(exitButton);
        buttons.add(soundButton);
        buttons.add(againButton);
        buttons.add(backToMenuButton);
    }

    public JButton getButton(String name){
        for (JButton button : buttons) {
            if (button.getName().equals(name))
                return button;
        }
        return null;
    }

    public JLabel getLabel(String name){
        for (JLabel label : labels) {
            if (label.getName().equals(name))
                return label;
        }
        return null;
    }

    public JComboBox<String> getWorlds() {
        return worldsBox;
    }

    public void position(){
        // label bounds
        title.setBounds((int) (gui.getWidth() * 0.5 - title.getWidth() * 0.5), (int)(gui.getHeight() * 0.2),
                title.getWidth(), title.getHeight());
        gameOver.setBounds((int) (gui.getWidth() * 0.5 - gameOver.getWidth() * 0.5), (int)(gui.getHeight() * 0.25),
                gameOver.getWidth(), gameOver.getHeight());
        score.setBounds( 20, 20,
                score.getWidth(), score.getHeight());


        // button bounds
        startButton.setBounds((int) (gui.getWidth() * 0.5 - startButton.getWidth() * 0.5), (int)(gui.getHeight() * 0.40),
                200, 50);
        soundButton.setBounds((int) (gui.getWidth() * 0.5 - soundButton.getWidth() * 0.5), (int)(gui.getHeight() * 0.50),
                200, 50);
        exitButton.setBounds((int) (gui.getWidth() * 0.5 - exitButton.getWidth() * 0.5), (int)(gui.getHeight() * 0.70),
                200, 50);
        againButton.setBounds((int) (gui.getWidth() * 0.5 - againButton.getWidth() * 0.5), (int)(gui.getHeight() * 0.45),
                againButton.getWidth(), againButton.getHeight());
        backToMenuButton.setBounds((int) (gui.getWidth() * 0.5 - backToMenuButton.getWidth() * 0.5), (int)(gui.getHeight() * 0.55),
                backToMenuButton.getWidth(), backToMenuButton.getHeight());
        worldsBox.setBounds((int) (gui.getWidth() * 0.5 - worldsBox.getWidth() * 0.5), (int)(gui.getHeight() * 0.60),
                200, 50);
    }

    public void addAll(){
        gui.add(startButton);
        gui.add(exitButton);
        gui.add(againButton);
        gui.add(soundButton);
        gui.add(backToMenuButton);
        gui.add(title);
        gui.add(score);
        gui.add(gameOver);
        gui.add(worldsBox);
    }
}
