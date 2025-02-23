import javax.swing.*;
import java.awt.*;
import java.nio.file.*;
import java.util.*;
import java.util.List;
import javax.swing.Timer;
import com.google.gson.*;

class Quiz extends JFrame {
    private int i = 0;
    private int score=0;
    private List<Questions> questionList;
    private JLabel questionLabel, questionText, timerLabel,scorelable;
    private JRadioButton option1, option2, option3, option4;
    private ButtonGroup optionsGroup;
    private JButton nextButton;
    private Timer timer;
    private int timeLeft = 20; // 20 seconds countdown

    Quiz(String username) {
        loadJSON();

        Font questionFont = new Font("Times New Roman", Font.BOLD, 35);
        Font optionsFont = new Font("Times New Roman", Font.PLAIN, 25);
        Font timerFont = new Font("Times New Roman", Font.BOLD, 25);
        Font scoreFont = new Font("Times New Roman", Font.BOLD, 32);

        questionLabel = new JLabel("Question", JLabel.CENTER);
        questionLabel.setFont(questionFont);
        questionLabel.setForeground(new Color(255, 255, 240));

        questionText = new JLabel("", JLabel.CENTER);
        questionText.setFont(optionsFont);
        questionText.setForeground(new Color(255, 255, 240));

        timerLabel = new JLabel("Time left: 20s");
        timerLabel.setFont(timerFont);
        timerLabel.setForeground(Color.RED);

        scorelable = new JLabel("Score: 0", JLabel.LEFT);
        scorelable.setFont(scoreFont);
        scorelable.setForeground(Color.white);


        option1 = new JRadioButton();
        option2 = new JRadioButton();
        option3 = new JRadioButton();
        option4 = new JRadioButton();

        option1.setFont(optionsFont);
        option2.setFont(optionsFont);
        option3.setFont(optionsFont);
        option4.setFont(optionsFont);

        option1.setBackground(new Color(100, 180, 250));
        option2.setBackground(new Color(65, 105, 225));
        option3.setBackground(new Color(100, 180, 250));
        option4.setBackground(new Color(65, 105, 225));

        option1.setForeground(Color.WHITE);
        option2.setForeground(Color.WHITE);
        option3.setForeground(Color.WHITE);
        option4.setForeground(Color.WHITE);

        optionsGroup = new ButtonGroup();
        optionsGroup.add(option1);
        optionsGroup.add(option2);
        optionsGroup.add(option3);
        optionsGroup.add(option4);

        nextButton = new JButton("Next");
        nextButton.setFont(optionsFont);
        nextButton.setBackground(new Color(50, 205, 50));
        nextButton.setForeground(Color.WHITE);
        nextButton.setFocusPainted(false);
        nextButton.setBorder(BorderFactory.createLineBorder(new Color(34, 139, 34)));

        Container c = getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(25, 40, 112));

        scorelable.setBounds(20,35,150,40);
        questionLabel.setBounds(95, 30, 600, 50);
        questionText.setBounds(95, 80, 600, 50);
        timerLabel.setBounds(590, 35, 150, 40);
        option1.setBounds(150, 160, 500, 50);
        option2.setBounds(150, 220, 500, 50);
        option3.setBounds(150, 280, 500, 50);
        option4.setBounds(150, 340, 500, 50);
        nextButton.setBounds(250, 420, 300, 50);

        c.add(scorelable);
        c.add(questionLabel);
        c.add(questionText);
        c.add(timerLabel);
        c.add(option1);
        c.add(option2);
        c.add(option3);
        c.add(option4);
        c.add(nextButton);

        // Timer Logic
        timer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText("Time left : " + timeLeft + "s"); // Update label

            if (timeLeft == 0)
            {
                timer.stop();
                JOptionPane.showMessageDialog(null, "Time's up! Moving to next question.");

                loadNextQuestion(username);
            }

        });
        timer.start();

        loadNextQuestion(username);

        nextButton.addActionListener(a -> {
            int x = validateAns();
            if (x == 1) {
                loadNextQuestion(username);
            } else {
                JOptionPane.showMessageDialog(null, "Select an answer!");
            }
        });

        setVisible(true);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Quiz Application");
    }

    void loadNextQuestion(String username) {
        if (i < questionList.size()) {
            Questions obj = questionList.get(i);
            questionLabel.setText("Question: " + (i + 1));
            questionText.setText(obj.question);
            option1.setText(obj.options[0]);
            option2.setText(obj.options[1]);
            option3.setText(obj.options[2]);
            option4.setText(obj.options[3]);
            optionsGroup.clearSelection();
            i++;

            // Reset timer
            timeLeft = 20;
            timerLabel.setText("Time left : 20s");
            timer.restart();
        } else {
            JOptionPane.showMessageDialog(null, "Quiz completed, Hurray!!");
            new Result(score,username);
            dispose();
        }
    }

    int validateAns() {
        Questions obj = questionList.get(i - 1);
        String correct = obj.correctAnswer;
        String current = "";

        if (option1.isSelected()) {
            current = option1.getText();
        } else if (option2.isSelected()) {
            current = option2.getText();
        } else if (option3.isSelected()) {
            current = option3.getText();
        } else if (option4.isSelected()) {
            current = option4.getText();
        } else {
            return 0;
        }

        timer.stop(); // Stop the timer when user submits an answer

        if (current.equals(correct)) {
            score++;
            JOptionPane.showMessageDialog(null, "Correct Answer ");

        } else {
            JOptionPane.showMessageDialog(null, "Wrong Answer! Correct Answer is: " + correct);
        }
        scorelable.setText("Score: " + score);
        return 1;
    }

    void loadJSON() {
        String fName = "Quiz.json";
        Path p = Paths.get(fName);
        try {
            if (Files.exists(p)) {
                String result = new String(Files.readAllBytes(p));
                Gson gson = new Gson();
                Questions[] obj1 = gson.fromJson(result, Questions[].class);
                questionList = new ArrayList<>(Arrays.asList(obj1));
            } else {
                JOptionPane.showMessageDialog(null, "File does not exist");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Quiz("ashish");
    }
}

class Questions {
    String question;
    String[] options;
    String correctAnswer;
}

