package src.java.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Displays the buttons to play number memory or sequence memory
public class FreeTypeMenu extends JPanel {
    private JButton tenButton, thirtyButton, sixtyButton;

    public FreeTypeMenu(){
        setSize(1200,800);
        JPanel modesPanel = new JPanel(null);
        JLabel titleLabel = new JLabel("SELECT YOUR TIME", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 70));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(0, -300, 1200,800);

        tenButton = new RoundedButton("10 Seconds",70);
        tenButton.setFont(new Font("Helvetica Neue", Font.BOLD, 35));
        tenButton.setBackground(new Color(204, 65, 146));
        tenButton.setForeground(new Color(255,255,255));
        tenButton.setFocusPainted(false);
        tenButton.setBounds(100,250,300,70);
        tenButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Menu menu = (Menu) SwingUtilities.getWindowAncestor(FreeTypeMenu.this);
                menu.getContentPane().removeAll();
                menu.getContentPane().add(new Ten());
                menu.validate();
                menu.repaint();
            }
        });

        thirtyButton = new RoundedButton("30 Seconds",70);
        thirtyButton.setFont(new Font("Helvetica Neue", Font.BOLD, 35));
        thirtyButton.setBackground(new Color(204, 65, 146));
        thirtyButton.setForeground(new Color(255,255,255));
        thirtyButton.setFocusPainted(false);
        thirtyButton.setBounds(450, 250, 300, 70);
        thirtyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = (Menu) SwingUtilities.getWindowAncestor(FreeTypeMenu.this);
                menu.getContentPane().removeAll();
                menu.getContentPane().add(new SequenceMemory());
                menu.validate();
                menu.repaint();
            }
        });

        sixtyButton = new RoundedButton("60 Seconds",70);
        sixtyButton.setFont(new Font("Helvetica Neue", Font.BOLD, 35));
        sixtyButton.setBackground(new Color(204, 65, 146));
        sixtyButton.setForeground(new Color(255,255,255));
        sixtyButton.setFocusPainted(false);
        sixtyButton.setBounds(800, 250, 300, 70);
        sixtyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = (Menu) SwingUtilities.getWindowAncestor(FreeTypeMenu.this);
                menu.getContentPane().removeAll();
                menu.getContentPane().add(new GameController(60));
                menu.validate();
                menu.repaint();
            }
        });
       
        modesPanel.setOpaque(false);
        modesPanel.add(titleLabel);
        modesPanel.add(tenButton);
        modesPanel.add(thirtyButton);
        modesPanel.add(sixtyButton);

        setOpaque(false);
        setLayout(new BorderLayout());
        add(modesPanel, BorderLayout.CENTER);


    }
}
