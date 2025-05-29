package Calc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private JPanel buttonPanel;
    private String currentNumber = "";
    private String previousNumber = "";
    private String operation = "";
    private boolean shouldClearDisplay = false;
    
    public Calculator() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setPreferredSize(new Dimension(300, 50));
        display.setBackground(Color.WHITE);
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setBackground(Color.DARK_GRAY);
        
        String[] buttonLabels = {
            "7", "8", "9", "+",
            "4", "5", "6", "-",
            "1", "2", "3", "x",
            "C", "0", "=", "/"
        };
        
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(this);
            button.setPreferredSize(new Dimension(50, 50));
            
            if (label.equals("C")) {
                button.setBackground(Color.RED);
                button.setForeground(Color.WHITE);
            } else if (label.equals("=")) {
                button.setBackground(new Color(0, 0, 139)); 
                button.setForeground(Color.WHITE);
            } else if (label.equals("+") || label.equals("-") || label.equals("x") || label.equals("/")) {
                button.setBackground(Color.BLACK);
                button.setForeground(Color.WHITE);
            } else {
                button.setBackground(Color.LIGHT_GRAY);
                button.setForeground(Color.BLACK);
            }
            
            button.setFocusPainted(false);
            button.setBorderPainted(true);
            buttonPanel.add(button);
        }
        
        getContentPane().setBackground(Color.DARK_GRAY);
        
        add(display, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        
        setSize(300, 400);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        if (command.equals("C")) {
            currentNumber = "";
            previousNumber = "";
            operation = "";
            display.setText("");
            shouldClearDisplay = false;
        } else if (command.equals("=")) {
            if (!previousNumber.isEmpty() && !currentNumber.isEmpty() && !operation.isEmpty()) {
                double result = calculate();
                if (result == Double.POSITIVE_INFINITY) {
                    currentNumber = "";
                    previousNumber = "";
                    operation = "";
                    display.setText("");
                } else {
                    display.setText(String.valueOf(result));
                    currentNumber = String.valueOf(result);
                    previousNumber = "";
                    operation = "";
                    shouldClearDisplay = true;
                }
            }
        } else if (command.equals("+") || command.equals("-") || command.equals("x") || command.equals("/")) {          
            if (!currentNumber.isEmpty()) {
                if (!previousNumber.isEmpty() && !operation.isEmpty()) {                  
                    double result = calculate();
                    previousNumber = String.valueOf(result);
                    display.setText(previousNumber);
                } else {
                    previousNumber = currentNumber;
                }
                currentNumber = "";
                operation = command;
                shouldClearDisplay = true;
            }
        } else {
            if (shouldClearDisplay) {
                display.setText("");
                currentNumber = "";
                shouldClearDisplay = false;
            }
            currentNumber += command;
            display.setText(currentNumber);
        }
    }
    
    private double calculate() {
        double num1 = Double.parseDouble(previousNumber);
        double num2 = Double.parseDouble(currentNumber);
        double result = 0;
        
        switch (operation) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "x":
                result = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    JOptionPane.showMessageDialog(this, "Dijeljenje sa nulom nije dozvoljeno!");
                    return Double.POSITIVE_INFINITY;
                }
                break;
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator calculator = new Calculator();
            calculator.setVisible(true);
        });
    }
}