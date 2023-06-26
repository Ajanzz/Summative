package src.java.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FreeType extends JPanel implements ActionListener, KeyListener {
    private int mode;

    private int wordCounter;
    private int first = 1;
    private int totalTime;
    private int timer;

    private int countAll;
    private int counter;
    private int elapsedTime;

    private JLabel levelLabel = new JLabel("", SwingConstants.CENTER);
    private JLabel secondsLabel = new JLabel("", SwingConstants.CENTER);
    private JLabel wpmLabel = new JLabel("", SwingConstants.CENTER);
    private JLabel accuracyLabel = new JLabel("", SwingConstants.CENTER);
    private JLabel programWordLabel = new JLabel("", SwingConstants.CENTER);
    private JLabel secondProgramWordLabel = new JLabel("", SwingConstants.CENTER);

    private JLabel secondsSubLabel = new JLabel("", SwingConstants.CENTER);
    private JLabel wpmSubLabel = new JLabel("", SwingConstants.CENTER);
    private JLabel accuracySubLabel = new JLabel("", SwingConstants.CENTER);

    private JTextField userWord = new JTextField(20);

    private JButton playAgainButton = new JButton();
    private JButton homeButton = new JButton();

    ArrayList<String> words = new ArrayList<>();
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    int frameWidth;
    int frameHeight;

    public FreeType(int setMode, int duration){
        mode = setMode;
        totalTime = duration;
        timer = totalTime;
        start(); 
        addToList();
        initialize();
    }


    private void start(){        
        frameWidth = 1200;
        frameHeight = 800;
        
        setSize(frameWidth, frameHeight);
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(null);

        setOpaque(false);
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        levelLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 70));
        levelLabel.setForeground(Color.WHITE);
        levelLabel.setBounds(0,-100,frameWidth,400);

        secondsLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 50));
        secondsLabel.setForeground(Color.WHITE);
        secondsLabel.setBounds(-300,10,frameWidth,400);

        secondsSubLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
        secondsSubLabel.setForeground(new Color(204, 65, 146));
        secondsSubLabel.setBounds(-300,60,frameWidth,400);
        secondsSubLabel.setText("Timer");
        
        wpmLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 50));
        wpmLabel.setForeground(Color.WHITE);
        wpmLabel.setBounds(0,10,frameWidth,400);

        wpmSubLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
        wpmSubLabel.setForeground(new Color(204, 65, 146));
        wpmSubLabel.setBounds(0,60,frameWidth,400);
        
        accuracyLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 50));
        accuracyLabel.setForeground(Color.WHITE);
        accuracyLabel.setBounds(300,10,frameWidth,400);

        accuracySubLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
        accuracySubLabel.setForeground(new Color(204, 65, 146));
        accuracySubLabel.setBounds(300,60,frameWidth,400);
        accuracySubLabel.setText("Accuracy");
        
        programWordLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 50));
        programWordLabel.setForeground(Color.WHITE);
        programWordLabel.setBounds(0,200,frameWidth,400);
        
        secondProgramWordLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 50));
        secondProgramWordLabel.setForeground(Color.WHITE);
        secondProgramWordLabel.setBounds(300,200,frameWidth,400);

        userWord.setVisible(true);
        userWord.setFont(new Font("Helvetica Neue", Font.PLAIN, 25));
        userWord.setForeground(Color.WHITE);
        userWord.setBackground(new Color(34, 21, 54));
        userWord.setBounds(frameWidth/2-100,450,200,50);
        userWord.addActionListener(this);
        userWord.addKeyListener(this);

        playAgainButton = new ImageButton("C:\\Users\\namnam\\Desktop\\Summative\\src\\java\\main\\tryagain.png");
        playAgainButton.setBounds(570,525,60,60);
        playAgainButton.setFocusPainted(false);
        playAgainButton.addActionListener(this);
        
        homeButton = new ImageButton("C:\\Users\\namnam\\Desktop\\Summative\\src\\java\\main\\home.png");
        homeButton.setVisible(true);
        homeButton.setBounds(9,10,60,60);
        homeButton.setFocusPainted(false);
        homeButton.addActionListener(this);
        
        switch (mode) {
            case 1: levelLabel.setText("BEGINNER"); wpmSubLabel.setText("Correct"); break;
            case 2: levelLabel.setText("INTERMEDIATE"); wpmSubLabel.setText("Correct"); break;
            case 3: levelLabel.setText("ADVANCED"); wpmSubLabel.setText("Correct"); break;
            case 4: levelLabel.setText("FREE TYPE"); wpmSubLabel.setText("WPM"); break;
        }

        panel.add(levelLabel);  
        panel.add(secondsLabel); 
        panel.add(secondsSubLabel);         
        panel.add(wpmLabel);        
        panel.add(wpmSubLabel);                
        panel.add(accuracyLabel);             
        panel.add(accuracySubLabel);        
        panel.add(programWordLabel);         
        panel.add(secondProgramWordLabel); 
        panel.add(userWord);      
        panel.add(playAgainButton);  
        panel.add(homeButton);  
    }

    // add words to array list
    public void addToList() {
        BufferedReader reader;
        String filename;

        filename = "C:\\Users\\namnam\\Desktop\\Summative\\src\\java\\main\\wordBeginnerList.txt.";
        System.out.println(mode);

        switch (mode) {
            case 1: filename = "C:\\Users\\namnam\\Desktop\\Summative\\src\\java\\main\\wordBeginnerList.txt."; break;
            case 2: filename = "C:\\Users\\namnam\\Desktop\\Summative\\src\\java\\main\\wordIntermediateList.txt"; break;
            case 3: filename = "C:\\Users\\namnam\\Desktop\\Summative\\src\\java\\main\\wordAdvancedList.txt"; break;
            case 4: filename = "C:\\Users\\namnam\\Desktop\\Summative\\src\\java\\main\\wordFreeTypeList.txt"; break;
        }
        
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {
                words.add(line);
                // read next line
                line = reader.readLine();
                //System.out.println("Current line:" + line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void initialize() {  
        timer = totalTime;
        elapsedTime = 0;
        wordCounter = 0;
        countAll = 0;
        counter = 0;

        secondsLabel.setText("" + totalTime);

        if (mode == 4) {
            wpmLabel.setText("WPM");
        }
        else {
            wpmLabel.setText("correct/total");
        }
        accuracyLabel.setText("acc");
        programWordLabel.setText("word");
        secondProgramWordLabel.setText("word2");

        userWord.setText("");
        userWord.setEnabled(true);

        playAgainButton.setVisible(false);
        playAgainButton.setEnabled(false);
        
        Collections.shuffle(words);
        programWordLabel.setText(words.get(wordCounter));
        secondProgramWordLabel.setText(words.get(wordCounter + 1)); 
        wordCounter++;
    }


    Runnable r = new Runnable() {
        @Override
        public void run() {
            if (timer > -1) {
                secondsLabel.setText(String.valueOf(timer));
                timer -= 1;
            } else {
                if (timer == -1) {
                    userWord.setEnabled(false);
                    userWord.setText("Game over");
                }

                if (timer == -2) {
                    playAgainButton.setVisible(true);
                    playAgainButton.setEnabled(true);
                }

                timer -= 1;
            }
        }
    };


    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        System.out.println("action:" + action);


        if (e.getSource() == playAgainButton) {
            initialize();
        }
        if (e.getSource() == homeButton) {
            Menu menu = (Menu) SwingUtilities.getWindowAncestor(FreeType.this);
            //Menu menu = (Menu) SwingUtilities.getWindowAncestor(FreeType.this).getParent();
            menu.getContentPane().removeAll();
            menu.getContentPane().add(new Modes());
            //menu.getContentPane().add(new Menu());
            menu.revalidate();
            menu.repaint();
        }    
    }


    @Override
    public void keyTyped(KeyEvent ke) {
        // Handle key typed event
    }


    @Override
    public void keyPressed(KeyEvent ke) {
        // Handle key pressed event
        int keyCode = ke.getKeyCode();

        String keyString = "key code = " + keyCode + " (" + KeyEvent.getKeyText(keyCode) + ")";
        System.out.println("action:" + keyString + ", " + KeyEvent.VK_SPACE);
    
        elapsedTime = totalTime - timer;

        // only gets called once
        if (first == 1) {
            first = 0;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);
        }

        if ((keyCode == KeyEvent.VK_SPACE) || (keyCode == KeyEvent.VK_ENTER)) {

            String userType = userWord.getText().trim();
            String real = programWordLabel.getText();
            String wpmText;
            countAll++;
        
            System.out.println("userType:'" + userType + "', real: '" + real + "'");

            // if correct
            if (userType.equals(real)) {
                counter++;

                //Thread t = new Thread(fadeCorrect);
                //t.start();
                
                System.out.println("correct");
            } 
            else {
                //Thread t = new Thread(fadeWrong);
                //t.start();
                System.out.println("incorrect");
            }

            if (elapsedTime != 0) {
                if (mode == 4) {
                    wpmText = String.valueOf((counter*60)/elapsedTime);
                }
                else {
                    wpmText = "" + counter + "/" + countAll;
                }
                wpmLabel.setText(wpmText);
            }

            userWord.setText("");
            accuracyLabel.setText(String.valueOf(Math.round((counter * 1.0 / countAll) * 100)) + "%");
            programWordLabel.setText(words.get(wordCounter));
            secondProgramWordLabel.setText(words.get(wordCounter + 1));
            wordCounter++;
        }
    }


    @Override
    public void keyReleased(KeyEvent ke) {
        // Handle key released event
    }


}
