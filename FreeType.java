package src.java.main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FreeType extends JPanel implements ActionListener, KeyListener {

    private int wordCounter = 0;
    private int first = 1;
    private int totalTime;
    private int timer;

    private int countAll = 0;
    private int counter = 0;
    private int elapsedTime = 0;

    private File saveData;

    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    private JLabel secondsLabel = new JLabel("", SwingConstants.CENTER);
    private JLabel wpmLabel = new JLabel("", SwingConstants.CENTER);
    private JLabel accuracyLabel = new JLabel("", SwingConstants.CENTER);
    private JLabel programWordLabel = new JLabel("", SwingConstants.CENTER);
    private JLabel secondProgramWordLabel = new JLabel("", SwingConstants.CENTER);

    private JTextField userWord = new JTextField(20);

    private JButton playAgainButton = new JButton();
    private JButton homeButton = new JButton();

    ArrayList<String> words = new ArrayList<>();

    
    int frameWidth;
    int frameHeight;

    public FreeType(int duration){
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

        secondsLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 100));
        secondsLabel.setForeground(Color.WHITE);
        secondsLabel.setBounds(-300,-30,frameWidth,400);
        secondsLabel.setText("" + timer);
        
        wpmLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 100));
        wpmLabel.setForeground(Color.WHITE);
        wpmLabel.setBounds(0,-30,frameWidth,400);
        wpmLabel.setText("wpm");
        
        accuracyLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 100));
        accuracyLabel.setForeground(Color.WHITE);
        accuracyLabel.setBounds(300,-30,frameWidth,400);
        accuracyLabel.setText("acc");
        
        programWordLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 50));
        programWordLabel.setForeground(Color.WHITE);
        programWordLabel.setBounds(0,200,frameWidth,400);
        programWordLabel.setText("word");
        
        secondProgramWordLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 50));
        secondProgramWordLabel.setForeground(Color.WHITE);
        secondProgramWordLabel.setBounds(300,200,frameWidth,400);
        secondProgramWordLabel.setText("word2");

        userWord.setVisible(true);
        userWord.setFont(new Font("Helvetica Neue", Font.PLAIN, 25));
        userWord.setForeground(Color.WHITE);
        userWord.setBackground(new Color(34, 21, 54));
        userWord.setBounds(frameWidth/2-100,450,200,50);
        userWord.addActionListener(this);
        userWord.addKeyListener(this);
        userWord.setActionCommand("keyIn");

        playAgainButton = new ImageButton("C:\\Users\\namnam\\Desktop\\Summative\\src\\java\\main\\tryagain.png");
        playAgainButton.setBounds(570,525,60,60);
        playAgainButton.setFocusPainted(false);
        
        homeButton = new ImageButton("C:\\Users\\namnam\\Desktop\\Summative\\src\\java\\main\\home.png");
        homeButton.setVisible(true);
        homeButton.setBounds(9,10,60,60);
        homeButton.setFocusPainted(false);
        /* 
        homeButton.setText("image");
        homeButton.setActionCommand("home");
        homeButton.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
        homeButton.setBackground(new Color(204, 65, 146));
        homeButton.setForeground(new Color(255,255,255));
        homeButton.setBounds((frameWidth-250)/2,550,250,60);
        homeButton.addActionListener(this);
        homeButton.setFocusPainted(false);
        */
        
        panel.add(secondsLabel);         
        panel.add(wpmLabel);         
        panel.add(accuracyLabel);         
        panel.add(programWordLabel);         
        panel.add(secondProgramWordLabel); 
        panel.add(userWord);      
        panel.add(playAgainButton);  
        panel.add(homeButton);  
        
    }

    // add words to array list
    public void addToList() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\namnam\\Desktop\\Summative\\src\\java\\main\\wordsList.txt"));
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

    /*
    public void toMainMenu(ActionEvent ae) throws IOException {
        Main m = new Main();
        m.changeScene("sample.fxml");
    }
*/
    public void initialize() {

        playAgainButton.setVisible(false);
        playAgainButton.setEnabled(false);
        //secondsLabel.setText("60");
        addToList();
        Collections.shuffle(words);
        programWordLabel.setText(words.get(wordCounter));
        secondProgramWordLabel.setText(words.get(wordCounter + 1)); 
        wordCounter++;
/*
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        saveData = new File("src/data/" + formatter.format(date).strip() + ".txt");

        try {
            if (saveData.createNewFile()) {
                System.out.println("File created: " + saveData.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
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

                    try {
                        FileWriter myWriter = new FileWriter(saveData);
                        myWriter.write(countAll + ";");
                        myWriter.write(counter + ";");
                        myWriter.write(String.valueOf(countAll - counter));
                        myWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (timer == -2) {
                    playAgainButton.setVisible(true);
                    playAgainButton.setEnabled(true);
                    executor.shutdown();
                }

                timer -= 1;
            }
        }
    };
/*
    Runnable fadeCorrect = new Runnable() {
        @Override
        public void run() {
            correct.setOpacity(0);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            correct.setOpacity(50);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            correct.setOpacity(100);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            correct.setOpacity(0);
        }
    };
*/
/*
    Runnable fadeWrong = new Runnable() {
        @Override
        public void run() {
            wrong.setOpacity(0);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            wrong.setOpacity(50);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            wrong.setOpacity(100);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            wrong.setOpacity(0);
        }
    };
*/

    public void startGame(KeyEvent ke) {

        // only gets called once
        if (first == 1) {
            first = 0;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);
        }

        if (ke.getKeyCode() == KeyEvent.VK_SPACE) {

            String s = userWord.getText();
            String real = programWordLabel.getText();
            countAll++;
        
            // if correct
            if (s.equals(real)) {
                counter++;
                wpmLabel.setText(String.valueOf(counter));

                //Thread t = new Thread(fadeCorrect);
                //t.start();
            } 
            else {
                //Thread t = new Thread(fadeWrong);
                //t.start();
            }
            userWord.setText("");
            accuracyLabel.setText(String.valueOf(Math.round((counter * 1.0 / countAll) * 100)));
            programWordLabel.setText(words.get(wordCounter));
            secondProgramWordLabel.setText(words.get(wordCounter + 1));
            wordCounter++;
        }
    }
         
    /**/
    

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button click event
        if (e.getSource() == playAgainButton) {
            // Perform play again logic
        }
        
        String action = e.getActionCommand();


        System.out.println("action:" + action);
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

            wpmLabel.setText(String.valueOf((counter*60)/elapsedTime));
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
