/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expense_income_tracker;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author HP
 */
public class ExpenseIncomeTracker extends JFrame{
    
    private final ExpenseIncomeTableModel tableModel;
    private final JTable table;
    private final JTextField dateField;
    private final JTextField descriptionField;
    private final JTextField amountField;
    private final JComboBox<String> typeCombobox;
    private final JButton addButton;
    private final JLabel balanceLabel;
    private double balance;
    
    public ExpenseIncomeTracker(){
        
        try{
            UIManager.setLookAndFeel(new FlatDarkLaf());
        }
        catch(Exception ex){
            System.err.println("Failed to set FlatDarkLaf LookAndFeel");
        }
        
        UIManager.put("TextField.foreground",Color.WHITE);
        UIManager.put("TextField.background",Color.DARK_GRAY);
        UIManager.put("TextField.caretForeground",Color.RED);
        UIManager.put("ComboBox.foreground",Color.YELLOW);
        UIManager.put("ComboBox.selectionForeground",Color.WHITE);
        UIManager.put("ComboBox.selectionForeground",Color.BLACK);
        UIManager.put("Button.foreground",Color.WHITE);
        UIManager.put("Button.background",Color.ORANGE);
        UIManager.put("Label.foreground",Color.WHITE);
        
        
        Font customFont=new Font("Arial",Font.PLAIN,10);
        UIManager.put("Label.font",customFont);
        UIManager.put("TextField.font",customFont);
        UIManager.put("ComboBox.font",customFont);
        UIManager.put("Button.font",customFont);
        
        balance = 0.0;
        tableModel = new ExpenseIncomeTableModel();
        table = new JTable(tableModel);
        JScrollPane scrollPane=new JScrollPane(table);
        table.setFillsViewportHeight(true);
        
        dateField = new JTextField(10);
        descriptionField=new JTextField(20);
        amountField=new JTextField(10);
        typeCombobox=new JComboBox<>(new String[]{"Esxpense","Income"});
        
        addButton=new JButton("Add");
        addButton.addActionListener(e -> addEntry());
        balanceLabel=new JLabel("Balance: $"+balance);
        
        
        JPanel inputPanel=new JPanel();
        inputPanel.add(new JLabel("Date"));
        inputPanel.add(dateField);
        
        inputPanel.add(new JLabel("Description"));
        inputPanel.add(descriptionField);
        
        inputPanel.add(new JLabel("Amount"));
        inputPanel.add(amountField);
        
        inputPanel.add(new JLabel("Type"));
        inputPanel.add(typeCombobox);
        
        inputPanel.add(addButton);
        
        JPanel bottomPanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(balanceLabel);
        setLayout(new BorderLayout());
        
        add(inputPanel,BorderLayout.NORTH);
        add(scrollPane,BorderLayout.CENTER);
        add(bottomPanel,BorderLayout.SOUTH);
        
        setTitle("Expense And Inome Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        
    }

 
    private void addEntry()
    {
        String date=dateField.getText();
        String description=descriptionField.getText();
        String amountStr=amountField.getText();
        String type=(String)typeCombobox.getSelectedItem();
        double amount;
        
        if(amountStr.isEmpty())
        {
            JOptionPane.showMessageDialog(this,"Enter the Amount","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try{
            amount=Double.parseDouble(amountStr);
        }
        catch(NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(this,"Invalid Amount Format","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(type.equals("Expense"))
        {
            amount *=-1;
        }
        
        ExpenseIncomeEntry entry =new ExpenseIncomeEntry(date,description,amount,type);
        tableModel.addEntry(entry);
        balance+=amount; 
        balanceLabel.setText("BalanceL $"+balance);
    }


    
    private void clearInputFields()
    {
        dateField.setText("");
        descriptionField.setText("");
        amountField.setText("");
        typeCombobox.setSelectedIndex(0);
        
    }
    
    
    
    
    
    
    
    
    
    
}
