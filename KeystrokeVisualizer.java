import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeystrokeVisualizer extends JFrame implements KeyListener, ActionListener {
    private JLabel label;
    private JButton[] keyboardButtons;

    public KeystrokeVisualizer() {
        super("Keystroke Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);

        label = new JLabel("Press any key...");
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setHorizontalAlignment(JLabel.CENTER);
        getContentPane().add(label, BorderLayout.NORTH);

        JPanel keyboardPanel = new JPanel(new GridLayout(4, 14, 5, 5));
        keyboardButtons = new JButton[47];

        String[] qwertyKeys = {
                "`", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-", "=",
                "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "[", "]", "\\",
                "A", "S", "D", "F", "G", "H", "J", "K", "L", ";", "'",
                "Z", "X", "C", "V", "B", "N", "M", ",", ".", "/"
        };

        for (int i = 0; i < 47; i++) {
            keyboardButtons[i] = new JButton(qwertyKeys[i]);
            keyboardButtons[i].addActionListener(this);
            keyboardButtons[i].setFont(new Font("Arial", Font.BOLD, 18));
            keyboardButtons[i].setFocusable(false);
            keyboardButtons[i].setBackground(new Color(229, 229, 229));
            keyboardButtons[i].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            keyboardButtons[i].setFocusPainted(false);
            keyboardButtons[i].setPreferredSize(new Dimension(50, 50));
            keyboardButtons[i].setForeground(Color.BLACK);
            keyboardPanel.add(keyboardButtons[i]);
        }

        getContentPane().add(keyboardPanel, BorderLayout.CENTER);

        addKeyListener(this);
        setFocusable(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            KeystrokeVisualizer visualizer = new KeystrokeVisualizer();
            visualizer.setVisible(true);
        });
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        String keyText = KeyEvent.getKeyText(keyCode);
        label.setText("Pressed: " + keyText);

        highlightButton(keyText, true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        String keyText = KeyEvent.getKeyText(keyCode);
        label.setText("Released: " + keyText);

        highlightButton(keyText, false);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Ignored for this example
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String keyText = ((JButton) e.getSource()).getText();
        label.setText("Pressed: " + keyText);

        simulateKeyPress(keyText);
    }

    private void highlightButton(String keyText, boolean highlight) {
        for (JButton button : keyboardButtons) {
            if (button.getText().equals(keyText)) {
                button.setBackground(highlight ? Color.YELLOW : new Color(229, 229, 229));
                break;
            }
        }
    }

    private void simulateKeyPress(String keyText) {
        for (JButton button : keyboardButtons) {
            if (button.getText().equals(keyText)) {
                button.doClick();
                break;
            }
        }
    }
}
