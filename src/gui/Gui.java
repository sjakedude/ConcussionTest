/**
 * CS-622 HW 2
 * Gui.java
 * Purpose: Controls the GUI
 *
 * @author Jake Stephens
 * @version 1.0 2/11/2020
 */
package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui {

    // Concussion Test Objects


    // Main menu objects
    JFrame window;
    JPanel mainPanel;
    JLabel mainHeaderLabel;
    JButton mainStartButton;
    JTextField mainNameTextField;
    JLabel mainNameLabel;
    JTextField mainAgeTextField;
    JLabel mainAgeLabel;
    JButton mainSettingsButton;

    // Settings menu objects
    JPanel settingsPanel;
    JButton settingsBackButton;
    JLabel settingsCardLabel;
    JLabel settingsGridLabel;
    JTextField settingsGridTextField;
    JTextField settingsCardTextField;
    JButton settingsGridPlusButton;
    JButton settingsGridMinusButton;
    JButton settingsCardPlusButton;
    JButton settingsCardMinusButton;

    // Image test items
    JPanel imagePanel;
    JLabel imageHeaderLabel;
    JLabel imageInstructionsLabel;
    JButton imageStartButton;

    public Gui() { initialize(); }

    public void start() { window.setVisible(true); }

    public void begin() {
        mainPanel.setVisible(false);
        imagePanel.setVisible(true);
    }

    public void settings() {
        mainPanel.setVisible(false);
        settingsPanel.setVisible(true);
    }

    public void mainMenu() {
        settingsPanel.setVisible(false);
        mainPanel.setVisible(true);
    }

    public void startImageTest() {
        System.out.println("YOO");
    }

    /**
     * Method that will increase or decrease the number of rounds for a specific test
     * @param test
     * @param operation
     */
    public void incrementRounds(String test, String operation) {
        switch (test) {
            case "card":
                if (operation == "+") {
                    // increase card test rounds

                } else {
                    // decrease card test rounds
                }
            case "grid":
                if (operation == "+") {
                    // increase grid test rounds

                } else {
                    // decrease grid test rounds

                }
        }
    }

    private void initialize() {

        // Initializing the window
        window = new JFrame();
        window.setBounds(100, 100, 650, 400);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new CardLayout());

        // Initializing the frames
        mainPanel = new JPanel();
        mainPanel.setBounds(100, 100, 650, 400);
        mainPanel.setLayout(null);
        mainPanel.setVisible(true);

        settingsPanel = new JPanel();
        settingsPanel.setBounds(100, 100, 650, 400);
        settingsPanel.setLayout(null);
        settingsPanel.setVisible(false);

        imagePanel = new JPanel();
        imagePanel.setBounds(100, 100, 650, 400);
        imagePanel.setLayout(null);
        imagePanel.setVisible(false);

        // Adding the frames to the window
        window.add(mainPanel);
        window.add(settingsPanel);
        window.add(imagePanel);

        // Main menu items
        mainHeaderLabel = new JLabel("Concussion Test Main Menu");
        mainHeaderLabel.setBounds(230, 15, 200, 15);

        mainNameTextField = new JTextField();
        mainNameTextField.setBounds(80, 150, 200, 20);

        mainAgeTextField = new JTextField();
        mainAgeTextField.setBounds(80, 180, 200, 20);

        mainNameLabel = new JLabel("Name: ");
        mainNameLabel.setBounds(10, 150, 50, 20);

        mainAgeLabel = new JLabel("Age: ");
        mainAgeLabel.setBounds(10, 180, 50, 20);

        mainStartButton = new JButton("Begin");
        mainStartButton.setBounds(230, 340, 200, 30);
        mainStartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent action) {
                begin();
            }
        });

        mainSettingsButton = new JButton("Settings");
        mainSettingsButton.setBounds(450, 340, 200, 30);
        mainSettingsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent action) {
                settings();
            }
        });

        // Adding the objects to the main frame
        mainPanel.add(mainHeaderLabel);
        mainPanel.add(mainStartButton);
        mainPanel.add(mainNameTextField);
        mainPanel.add(mainAgeTextField);
        mainPanel.add(mainNameLabel);
        mainPanel.add(mainAgeLabel);
        mainPanel.add(mainSettingsButton);

        // Settings menu items
        settingsGridTextField = new JTextField();
        settingsGridTextField.setBounds(130, 50, 25, 20);
        settingsGridTextField.setText("1");
        settingsGridTextField.setEditable(false);

        settingsCardTextField = new JTextField();
        settingsCardTextField.setBounds(130, 200, 25, 20);
        settingsCardTextField.setText("1");
        settingsCardTextField.setEditable(false);

        settingsCardLabel = new JLabel("Card Test Rounds:");
        settingsCardLabel.setBounds(10, 50, 200, 15);

        settingsGridLabel = new JLabel("Grid Test Rounds:");
        settingsGridLabel.setBounds(10, 200, 200, 15);

        settingsBackButton = new JButton("Back");
        settingsBackButton.setBounds(450, 340, 200, 30);
        settingsBackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent action) {
                mainMenu();
            }
        });

        settingsCardPlusButton = new JButton("+");
        settingsCardPlusButton.setBounds(160, 50, 30, 30);
        settingsCardPlusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent action) {
                incrementRounds("card", "+");
            }
        });

        settingsCardMinusButton = new JButton("-");
        settingsCardMinusButton.setBounds(200, 50, 30, 30);
        settingsCardMinusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent action) {
                incrementRounds("card", "-");
            }
        });

        settingsGridPlusButton = new JButton("+");
        settingsGridPlusButton.setBounds(160, 200, 30, 30);
        settingsGridPlusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent action) {
                incrementRounds("grid", "+");
            }
        });

        settingsGridMinusButton = new JButton("-");
        settingsGridMinusButton.setBounds(200, 200, 30, 30);
        settingsGridMinusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent action) {
                incrementRounds("grid", "-");
            }
        });

        // Adding the objects to the settings frame
        settingsPanel.add(settingsBackButton);
        settingsPanel.add(settingsGridTextField);
        settingsPanel.add(settingsCardTextField);
        settingsPanel.add(settingsCardLabel);
        settingsPanel.add(settingsGridLabel);
        settingsPanel.add(settingsCardPlusButton);
        settingsPanel.add(settingsCardMinusButton);
        settingsPanel.add(settingsGridPlusButton);
        settingsPanel.add(settingsGridMinusButton);

        // Image test items
        imageHeaderLabel = new JLabel("Image Test");
        imageHeaderLabel.setBounds(250, 15, 200, 15);

        imageInstructionsLabel = new JLabel("Instructions: When you see an image appear more than once, press the spacebar.");
        imageInstructionsLabel.setBounds(10, 330, 600, 15);

        imageStartButton = new JButton("Begin");
        imageStartButton.setBounds(450, 340, 200, 30);
        imageStartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent action) {
                startImageTest();
            }
        });

        // Adding the objects to the image test frame
        imagePanel.add(imageHeaderLabel);
        imagePanel.add(imageInstructionsLabel);
        imagePanel.add(imageStartButton);
    }
}
