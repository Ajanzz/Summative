
package src.java.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Main menu
public class Menu extends JFrame {
    private JButton startButton;
    private JButton aboutButton;
    private JPanel instructionPanel;
    private int panelY;
    private JButton closeButton;
    private JButton exitButton;

    public Menu() {
        super("Touch Typer");
        setSize(1200,800);
        setLocationRelativeTo(null);
        setBackground(new Color(53, 29, 89));
        setLayout(null);
        setResizable(false);

        JLabel titleLabel = new JLabel("Touch Typer", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 50));
        titleLabel.setForeground(new Color(255,255,255));
        titleLabel.setBounds(0,-100,1200,800);

        startButton = new RoundedButton("START",60);
        startButton.setFont(new Font("Helvetica Neue", Font.BOLD, 40));
        startButton.setBackground(new Color(204, 65, 146));
        startButton.setForeground(new Color(255,255,255));
        startButton.setFocusPainted(false);
        startButton.setBounds(500,400,200,60);
        startButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                getContentPane().removeAll();
                getContentPane().add(new Modes());
                validate();
                repaint();
            }
        });
        
        closeButton = new RoundedButton("CLOSE",60);
        closeButton.setFont(new Font("Helvetica Neue", Font.BOLD, 40));
        closeButton.setBackground(new Color(204, 65, 146));
        closeButton.setForeground(new Color(255,255,255));
        closeButton.setFocusPainted(false);
        closeButton.setVisible(false);
        closeButton.setBounds(500,1000,200,60);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                instructionPanel.setVisible(false);
                titleLabel.setVisible(true);
                startButton.setVisible(true);
                aboutButton.setVisible(true);
                exitButton.setVisible(true);
                closeButton.setVisible(false);

            }
        });
        aboutButton = new RoundedButton("ABOUT",60);
        aboutButton.setFont(new Font("Helvetica Neue", Font.BOLD, 40));
        aboutButton.setBackground(new Color(204, 65, 146));
        aboutButton.setForeground(new Color(255,255,255));
        aboutButton.setFocusPainted(false);
        aboutButton.setBounds(500,500,200,60);
        aboutButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                
                showInstructionsPanel();
                titleLabel.setVisible(false);
                startButton.setVisible(false);
                aboutButton.setVisible(false);
                exitButton.setVisible(false);
                closeButton.setVisible(true);
            }
        });

        exitButton = new RoundedButton("EXIT",60);
        exitButton.setFont(new Font("Helvetica Neue", Font.BOLD, 40));
        exitButton.setBackground(new Color(204, 65, 146));
        exitButton.setForeground(new Color(255,255,255));
        exitButton.setFocusPainted(false);
        exitButton.setBounds(500,600,200,60);
        exitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });

        instructionPanel = new RoundedPanel(20);
        instructionPanel.setLayout(null);
        instructionPanel.setBounds(200, 800, 800, 500);
        instructionPanel.setBackground(new Color(34, 21, 54));
        // Everytime you want to do a back space add <br> after word
        // Use <b>Text</b> to bold the text
        // Use <font size='+1'>Text</font> to change the size of desired text
        JLabel instructionLabel = new JLabel("<html><center><b><font size='+3'>ABOUT</b></font></center></html>", SwingConstants.CENTER);
        JLabel howtoplayLabel = new JLabel("<html><font size='+1'><b>What is touch typing?</font></b><br>Touch typing is a style of typing. Touch typist will know their location on the keyboard through muscle memory—the term is often used to refer to a specific form of touch typing that involves placing the eight fingers in a horizontal row along the middle of the keyboard (the home row) and having them reach for specific other keys.<br><br><font size='+1'><b>About the program</b></font><br>Our program is a tool that aids the user in learning the touch typing method with the goal of memorizing the keyboard!<br>There are 3 difficulties: beginner (middle row), intermediate (top row), and advanced (bottom row)<br>Once the user feels like it, they can go to the free type mode where they can test their WPM in a more practical way.</html>");
        instructionLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 30));
        howtoplayLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        howtoplayLabel.setForeground(Color.WHITE);
        howtoplayLabel.setHorizontalAlignment(SwingConstants.CENTER);
        howtoplayLabel.setBounds(20,0,760,500);
        instructionLabel.setForeground(Color.WHITE);
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        instructionLabel.setBounds(0,-200,800, 500);
        instructionPanel.add(instructionLabel);
        instructionPanel.add(howtoplayLabel);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setOpaque(false);
        buttonPanel.add(startButton);
        buttonPanel.add(aboutButton);

        JPanel menuPanel = new JPanel(new BorderLayout());
        menuPanel.setLayout(null);
        menuPanel.setOpaque(false);
        menuPanel.add(titleLabel);
        menuPanel.add(aboutButton);
        menuPanel.add(startButton);
        menuPanel.add(closeButton);
        menuPanel.add(instructionPanel);
        menuPanel.add(exitButton);

        setContentPane(menuPanel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void showInstructionsPanel(){
        instructionPanel.setVisible(true);
        animate();
    }

    private void animate() {
        int frameHeight = getContentPane().getHeight();
        int panelHeight = instructionPanel.getHeight();
        panelY = (frameHeight - panelHeight) / 2;
        instructionPanel.setLocation(instructionPanel.getX(), frameHeight);
        closeButton.setLocation(closeButton.getX(), 1180);
    
        int initialPanelY = instructionPanel.getY(); // initial Y-coordinate of the panel
        int initialButtonY = closeButton.getY(); // initial Y-coordinate of the close button
    
        Timer timer = new Timer(5, null);
        timer.addActionListener(new ActionListener() {
            int currentY = frameHeight;
    
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentY > panelY) {
                    currentY -= 15;
                    int changeInY = initialPanelY - currentY; // Calculate the change in Y-coordinate of the panel
                    int panelY = initialPanelY - changeInY; // Calculate the updated Y-coordinate of the panel
                    int buttonY = initialButtonY - changeInY; // Calculate the updated Y-coordinate of the close button
                    instructionPanel.setLocation(instructionPanel.getX(), panelY);
                    closeButton.setLocation(closeButton.getX(), buttonY); // Update the close button's position
                    instructionPanel.revalidate();
                    instructionPanel.repaint();
                } else {
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    public Menu(String string) {
    }
    
}
