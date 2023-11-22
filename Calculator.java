package org.tofmal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {

    private JFrame frame;
    private JTextField textField;
    private boolean dotUsed = false;
    private double firstNumber;
    private String operator;

    public Calculator() {

        frame = new JFrame("Calculator");
        textField = new JTextField();
        JPanel buttonPanel = new JPanel();

        Font fn = new Font("Arial", Font.PLAIN, 30);

        boolean dotUsed = false;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());


        textField.setPreferredSize(new Dimension(100, 100));
        textField.setFont(fn);
        frame.add(textField, BorderLayout.NORTH);

        buttonPanel.setLayout(new GridLayout(5, 4));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "CE", "DEL", "(0_", "_0)"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ButtonClickListener());
            button.setFont(fn);
            if(label == ".") button.setFont(new Font("Arial", Font.PLAIN, 50));
            buttonPanel.add(button);
        }

        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String buttonText = ((JButton) event.getSource()).getText();

            if (buttonText.matches("[0-9.]")) {
                textField.setText(textField.getText().concat(buttonText));

            } else if (buttonText.equals("=")) {
                double secondNumber = Double.parseDouble(textField.getText());
                double result = calculateResult(firstNumber, secondNumber, operator);
                textField.setText(String.valueOf(result));

            }else if (buttonText.equals(".")) {
                    if (!dotUsed) {
                        textField.setText(textField.getText().concat(buttonText));
                        dotUsed = true;
                    }//не работает, почини

            }else if (buttonText.equals("CE")) {
                textField.setText("");

            }else if (buttonText.equals("DEL")) {

                String str = textField.getText();
                textField.setText("");
                for(int i = 0; i < str.length()-1; i++)
                    textField.setText(textField.getText() + str.charAt(i));

            } else {
                firstNumber = Double.parseDouble(textField.getText());
                operator = buttonText;
                textField.setText("");
            }
        }
    }

    private double calculateResult(double firstNumber, double secondNumber, String operator) {
        switch (operator) {
            case "+":
                return firstNumber + secondNumber;
            case "-":
                return firstNumber - secondNumber;
            case "*":
                return firstNumber * secondNumber;
            case "/":
                return firstNumber / secondNumber;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    public static void main(String[] args) {
                new Calculator();
    }
}