package Gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class ToDo_List extends JFrame {
    private DefaultTableModel tableModel;
    private JTable taskTable;
    private JTextField taskInput;
    private JButton addButton;
    private JButton clearButton;
    
    public ToDo_List() {
        setTitle("To Do List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        String[] columnNames = {"Id", "To Do List", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        taskTable = new JTable(tableModel);
        taskTable.setRowHeight(30);
        taskTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        taskTable.getColumnModel().getColumn(1).setPreferredWidth(350);
        taskTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        
        taskTable.setDefaultRenderer(Object.class, new TaskTableRenderer());
        
        taskTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = taskTable.rowAtPoint(e.getPoint());
                int col = taskTable.columnAtPoint(e.getPoint());
                
                if (col == 2 && row >= 0) {
                    String status = (String) tableModel.getValueAt(row, 2);
                    if (status.equals("Undone")) {
                        tableModel.setValueAt("Done", row, 2);
                    } else {
                        tableModel.setValueAt("Undone", row, 2);
                    }
                    taskTable.repaint();
                }
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(taskTable);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        addButton = new JButton("Add Task");
        clearButton = new JButton("Clear finished tasks");
        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });
        
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFinishedTasks();
            }
        });
        
        buttonPanel.add(addButton);
        buttonPanel.add(clearButton);
        
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        setSize(520, 400);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    private void addTask() {
        String taskText = JOptionPane.showInputDialog(this, "Unesite naziv taska:", "Novi Task", JOptionPane.PLAIN_MESSAGE);
        
        if (taskText != null && !taskText.trim().isEmpty()) {
            int nextId = tableModel.getRowCount() + 1;
            Object[] rowData = {nextId, taskText.trim(), "Undone"};
            tableModel.addRow(rowData);
        }
    }
    
    private void clearFinishedTasks() {
        for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
            String status = (String) tableModel.getValueAt(i, 2);
            if (status.equals("Done")) {
                tableModel.removeRow(i);
            }
        }
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            tableModel.setValueAt(i + 1, i, 0);
        }
    }
   
    private class TaskTableRenderer extends javax.swing.table.DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            
            String status = (String) tableModel.getValueAt(row, 2);
            
            if (column == 2) {
            	JButton button = new JButton(value.toString());
                button.setOpaque(true);
                
                if (status.equals("Undone")) {
                    button.setBackground(Color.GREEN);
                    button.setForeground(Color.BLACK);
                } else {
                    button.setBackground(Color.RED);
                    button.setForeground(Color.WHITE);
                }
                
                return button;
            } else {
                if (status.equals("Done")) {
                    c.setBackground(new Color(255, 200, 200)); 
                } else {
                    c.setBackground(new Color(200, 255, 200)); 
                }
            }
            
            return c;
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ToDo_List todoList = new ToDo_List();
            todoList.setVisible(true);
        });
    }
}