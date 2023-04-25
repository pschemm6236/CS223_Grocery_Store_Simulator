package csc223Giraffes;

import javax.swing.*;

public class MyGUI extends JFrame {
	public MyGUI() {
        super("My GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Enter your name:");
        JTextField textField = new JTextField(20);
        JButton button = new JButton("Submit");
        panel.add(label);
        panel.add(textField);
        panel.add(button);
        button.addActionListener(e -> {
            String name = textField.getText();
            JOptionPane.showMessageDialog(this, "Hello, " + name + "!");
        });
        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


}
