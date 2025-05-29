package Gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Ball {
    static int up_down = 0;
    static int left_right = 0;
    
    static class BallPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            g2d.setColor(Color.BLACK);
            g2d.fillOval(0, 0, getWidth(), getHeight());
        }
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Move a Ball");
        frame.setLayout(null);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.gray);
        panel.setBounds(0, 0, 800, 350);
        panel.setVisible(true);
        panel.setLayout(null);
        frame.add(panel);
        
        BallPanel el = new BallPanel();
        el.setBackground(Color.gray);
        el.setBounds(375, 150, 50, 50);
        el.setVisible(true);
        panel.add(el);
        
        JButton up = new JButton("Up");
        up.setBounds(350, 360, 100, 50);
        up.setVisible(true);
        JButton down = new JButton("Down");
        down.setBounds(350, 500, 100, 50);
        down.setVisible(true);
        JButton left = new JButton("Left");
        left.setBounds(290, 430, 100, 50);
        left.setVisible(true);
        JButton right = new JButton("Right");
        right.setBounds(420, 430, 100, 50);
        right.setVisible(true);
        
        up.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(up_down <= -150) {
                    JOptionPane.showMessageDialog(panel, "End reached. Please move in another direction.");
                    up.setVisible(false);
                }
                else {
                    up_down -= 10;
                    panel.remove(el);
                    frame.repaint();
                    el.setBounds(375 + left_right, 150 + up_down, 50, 50);
                    panel.add(el);
                    down.setVisible(true);
                }
            }
        });
        
        down.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(up_down >= 150) {
                    JOptionPane.showMessageDialog(panel, "End reached. Please move in another direction.");
                    down.setVisible(false);
                }
                else {
                    up_down += 10;
                    panel.remove(el);
                    frame.repaint();
                    el.setBounds(375 + left_right, 150 + up_down, 50, 50);
                    panel.add(el);
                    up.setVisible(true);
                }
            }
        });
        
        left.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(left_right <= -375) {
                    JOptionPane.showMessageDialog(panel, "End reached. Please move in another direction.");
                    left.setVisible(false);
                }
                else {
                    left_right -= 25;
                    panel.remove(el);
                    frame.repaint();
                    el.setBounds(375 + left_right, 150 + up_down, 50, 50);
                    panel.add(el);
                    right.setVisible(true);
                }
            }
        });
        
        right.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(left_right >= 375) {
                    JOptionPane.showMessageDialog(panel, "End reached. Please move in another direction.");
                    right.setVisible(false);
                }
                else {
                    left_right += 25;
                    panel.remove(el);
                    frame.repaint();
                    el.setBounds(375 + left_right, 150 + up_down, 50, 50);
                    panel.add(el);
                    left.setVisible(true);
                }
            }
        });
        
        frame.add(up);
        frame.add(down);
        frame.add(left);
        frame.add(right);
    }
}