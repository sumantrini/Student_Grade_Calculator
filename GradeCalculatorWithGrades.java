import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class GradeCalculatorWithGrades extends JFrame {

    private JTextField[] scoreFields;
    private JLabel totalLabel;
    private JLabel averageLabel;
    private JLabel gradeLabel;

    public GradeCalculatorWithGrades() {
        setTitle("Student Grade Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(0, 2));
        scoreFields = new JTextField[5];

        for (int i = 0; i < 5; i++) {
            JLabel label = new JLabel("Subject " + (i + 1) + " score:");
            scoreFields[i] = new JTextField(10);
            panel.add(label);
            panel.add(scoreFields[i]);
        }

        JButton calculateButton = new JButton("Calculate Grades");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateGrades();
            }
        });

        totalLabel = new JLabel("Total Marks: ");
        averageLabel = new JLabel("Average Percentage: ");
        gradeLabel = new JLabel("Overall Grade: ");

        panel.add(calculateButton);
        panel.add(totalLabel);
        panel.add(averageLabel);
        panel.add(gradeLabel);

        add(panel);
        setVisible(true);
    }

    private void calculateGrades() {
        double totalScore = 0;
        int count = 0;

        for (JTextField scoreField : scoreFields) {
            try {
                double score = Double.parseDouble(scoreField.getText());
                totalScore += score;
                count++;
            } catch (NumberFormatException e) {
                // Ignore non-numeric inputs
            }
        }

        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        if (count > 0) {
            double average = totalScore / count;
            totalLabel.setText("Total Marks: " + decimalFormat.format(totalScore));
            averageLabel.setText("Average Percentage: " + decimalFormat.format(average) + "%");
            gradeLabel.setText("Overall Grade: " + calculateGrade(average));
        } else {
            totalLabel.setText("Total Marks: N/A");
            averageLabel.setText("Average Percentage: N/A");
            gradeLabel.setText("Overall Grade: N/A");
        }
    }

    private String calculateGrade(double average) {
        if (average >= 90) {
            return "A";
        } else if (average >= 80) {
            return "B";
        } else if (average >= 70) {
            return "C";
        } else if (average >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GradeCalculatorWithGrades();
            }
        });
    }
}
