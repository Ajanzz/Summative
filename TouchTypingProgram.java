import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TouchTypingProgram extends JFrame {
    private JButton startButton;
    private JButton instructionsButton;
    private JButton quitButton;

    public TouchTypingProgram() {
        setTitle("Touch Typing Program");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Initialize buttons
        startButton = new JButton("Continue");
        instructionsButton = new JButton("Instructions");
        quitButton = new JButton("Quit");

        // Add action listeners to the buttons
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDifficultySelection();
            }
        });

        instructionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showInstructions();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Add buttons to the frame
        add(startButton);
        add(instructionsButton);
        add(quitButton);

        pack();
        setLocationRelativeTo(null);
    }

    private void showDifficultySelection() {
        String[] difficultyLevels = {"Beginner", "Intermediate", "Advanced"};
        String selectedDifficulty = (String) JOptionPane.showInputDialog(
                this,
                "Select difficulty level:",
                "Difficulty Selection",
                JOptionPane.PLAIN_MESSAGE,
                null,
                difficultyLevels,
                difficultyLevels[0]);

        if (selectedDifficulty != null) {
            LessonPlan lessonPlan = createLessonPlan(selectedDifficulty);
            startTypingPractice(lessonPlan);
        }
    }

    private LessonPlan createLessonPlan(String difficulty) {
        List<String> keysToLearn;
        if (difficulty.equals("Beginner")) {
            keysToLearn = Arrays.asList("A", "S", "D", "F", "G", "H", "J", "K", "L");
        } else if (difficulty.equals("Intermediate")) {
            keysToLearn = Arrays.asList("Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P");
        } else {
            keysToLearn = Arrays.asList("Z", "X", "C", "V", "B", "N", "M");
        }

        LessonPlan lessonPlan = new LessonPlan(difficulty, keysToLearn);
        return lessonPlan;
    }

    private void startTypingPractice(LessonPlan lessonPlan) {
        KeyboardPanel keyboardPanel = new KeyboardPanel(lessonPlan.getKeysToLearn());
        add(keyboardPanel);

        TypingExercise typingExercise = new TypingExercise(lessonPlan.getKeysToLearn());
        typingExercise.setExerciseListener(new TypingExerciseListener() {
            @Override
            public void onExerciseCompleted(boolean isMastered) {
                if (isMastered) {
                    if (lessonPlan.hasNextLevel()) {
                        lessonPlan.moveToNextLevel();
                        startTypingPractice(lessonPlan);
                    } else {
                        showCongratulations();
                    }
                }
            }

            @Override
            public void onExerciseCanceled() {
                remove(keyboardPanel);
                revalidate();
                repaint();
            }
        });
        add(typingExercise);

        pack();
    }

    private void showInstructions() {
        String instructions = "Instructions:\n\n" +
                "1. Focus on the highlighted keys on the virtual keyboard.\n" +
                "2. Type the given exercise using only the highlighted keys.\n" +
                "3. Aim for accuracy and speed.\n" +
                "4. Once you have mastered a portion of the keyboard, you will move on to the next level.";

        JOptionPane.showMessageDialog(this, instructions, "Instructions", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showCongratulations() {
        String congratulations = "Congratulations!\nYou have mastered the entire keyboard.";
        int choice = JOptionPane.showConfirmDialog(this, congratulations, "Congratulations", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            // Continue training
        } else {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TouchTypingProgram().setVisible(true);
            }
        });
    }
}
