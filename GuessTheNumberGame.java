
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessTheNumberGame {
    private int targetNumber;
    private int maxAttempts = 10;
    private int attempts = 0;
    private int score = 0;

    private JTextField guessField;
    private JLabel messageLabel;
    private JLabel scoreLabel;

    public GuessTheNumberGame() {
        JFrame frame = new JFrame("Guess the Number Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        targetNumber = new Random().nextInt(100) + 1; 
        
        messageLabel = new JLabel("I'm thinking of a number between 1 and 100.");
        scoreLabel = new JLabel("Score: " + score);

        guessField = new JTextField(10);
        guessField.addActionListener(new GuessListener());

        JPanel panel = new JPanel();
        panel.add(messageLabel);
        panel.add(guessField);
        panel.add(scoreLabel);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(300, 150);
        frame.setVisible(true);
    }

    class GuessListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int userGuess = Integer.parseInt(guessField.getText());
            attempts++;

            if (userGuess < targetNumber) {
                messageLabel.setText("My number is higher than your guess.");
            } else if (userGuess > targetNumber) {
                messageLabel.setText("My number is lower than your guess.");
            } else {
                int roundScore = (maxAttempts - attempts) * 10;
                score += roundScore;
                scoreLabel.setText("Score: " + score);

                if (attempts == maxAttempts) {
                    messageLabel.setText("Out of attempts! The correct number was: " + targetNumber);
                } else {
                    messageLabel.setText("Congratulations! You guessed the number in " + attempts + " attempts.");
                }

                targetNumber = new Random().nextInt(100) + 1; // Generate a new random target number
                attempts = 0;
            }

            guessField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GuessTheNumberGame());
    }
}