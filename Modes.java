package src.java.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Displays the buttons to play number memory or sequence memory
public class Modes extends JPanel {
    private JButton beginnerButton, intermediateButton, advancedButton, ftButton;


    public Modes(){
        setSize(1200,800);
        JPanel modesPanel = new JPanel(null);
        JLabel titleLabel = new JLabel("SELECT YOUR LEVEL", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 70));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(0, -300, 1200,800);

        beginnerButton = new RoundedButton("BEGINNER",70);
        beginnerButton.setFont(new Font("Helvetica Neue", Font.BOLD, 35));
        beginnerButton.setBackground(new Color(204, 65, 146));
        beginnerButton.setForeground(new Color(255,255,255));
        beginnerButton.setFocusPainted(false);
        beginnerButton.setBounds(100,250,300,70);
        beginnerButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Menu menu = (Menu) SwingUtilities.getWindowAncestor(Modes.this);
                menu.getContentPane().removeAll();
                menu.getContentPane().add(new FreeTypeMenu(1));
                menu.validate();
                menu.repaint();
            }
        });

        intermediateButton = new RoundedButton("INTERMEDIATE",70);
        intermediateButton.setFont(new Font("Helvetica Neue", Font.BOLD, 35));
        intermediateButton.setBackground(new Color(204, 65, 146));
        intermediateButton.setForeground(new Color(255,255,255));
        intermediateButton.setFocusPainted(false);
        intermediateButton.setBounds(450, 250, 300, 70);
        intermediateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = (Menu) SwingUtilities.getWindowAncestor(Modes.this);
                menu.getContentPane().removeAll();
                menu.getContentPane().add(new FreeTypeMenu(2));
                menu.validate();
                menu.repaint();
            }
        });

        advancedButton = new RoundedButton("ADVANCED",70);
        advancedButton.setFont(new Font("Helvetica Neue", Font.BOLD, 35));
        advancedButton.setBackground(new Color(204, 65, 146));
        advancedButton.setForeground(new Color(255,255,255));
        advancedButton.setFocusPainted(false);
        advancedButton.setBounds(800, 250, 300, 70);
        advancedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = (Menu) SwingUtilities.getWindowAncestor(Modes.this);
                menu.getContentPane().removeAll();
                menu.getContentPane().add(new FreeTypeMenu(3));
                menu.validate();
                menu.repaint();
            }
        });

        ftButton = new RoundedButton("FREE TYPE",70);
        ftButton.setFont(new Font("Helvetica Neue", Font.BOLD, 35));
        ftButton.setBackground(new Color(204, 65, 146));
        ftButton.setForeground(new Color(255,255,255));
        ftButton.setFocusPainted(false);
        ftButton.setBounds(450, 500, 300, 70);
        ftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = (Menu) SwingUtilities.getWindowAncestor(Modes.this);
                menu.getContentPane().removeAll();
                menu.getContentPane().add(new FreeTypeMenu(4));
                menu.validate();
                menu.repaint();
            }
        });
       
        modesPanel.setOpaque(false);
        modesPanel.add(titleLabel);
        modesPanel.add(beginnerButton);
        modesPanel.add(intermediateButton);
        modesPanel.add(advancedButton);
        modesPanel.add(ftButton);

        setOpaque(false);
        setLayout(new BorderLayout());
        add(modesPanel, BorderLayout.CENTER);


    }
}
