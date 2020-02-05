/**
 * Created by: Jake Stephens
 * Class that contains all the info for the GUI
 */

package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui {

    // Main menu objects
    JFrame window;
    JPanel mainPanel;
    JLabel mainLabel;
    JButton mainButton;

    public Gui() {
        initialize();
    }

    public void start() {
        window.setVisible(true);
    }

    private void initialize() {

        // Initializing the window
        window = new JFrame();
        window.setBounds(100, 100, 450, 300);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new CardLayout());

        // Initializing the frames
        mainPanel = new JPanel();
        mainPanel.setBounds(100, 100, 450, 300);
        mainPanel.setLayout(null);

        // Adding the frames to the window
        window.add(mainPanel);

        mainLabel = new JLabel("Main Menu");
        mainLabel.setBounds(93, 67, 100, 15);

        mainButton = new JButton("Start it up!");
        mainButton.setBounds(93, 121, 200, 30);
        mainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent action) {
                System.out.println("TEST");
            }
        });

        // Adding the objects to the main frame
        mainPanel.add(mainLabel);
        mainPanel.add(mainButton);


    }
}
