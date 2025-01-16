package doancuoiky;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class topic_management {

    public static void main(String[] args) throws ClassNotFoundException,  SQLException {
        loginScreen();
    }
    

    public static void loginScreen() {
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setSize(400, 300);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLayout(null);
        loginFrame.setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        titleLabel.setBounds(150, 20, 100, 30);
        loginFrame.add(titleLabel);
        
        
        //Usename
        JLabel userLabel = new JLabel("Usename: "); 
        userLabel.setBounds(50, 80, 100, 25);
        loginFrame.add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds(150, 80, 200, 25);
        loginFrame.add(userField);
        
        //Password
        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(50,110,100,25);
        loginFrame.add(passwordLabel);
        
        JTextField passField = new JTextField();
        passField.setBounds(150,110,200,25);
        loginFrame.add(passField);
        
        //Student or Teacher
        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setBounds(50, 150, 100, 25);
        loginFrame.add(roleLabel);

        JCheckBox studentCheckBox = new JCheckBox("Student");
        studentCheckBox.setBounds(150, 150, 100, 25);
        loginFrame.add(studentCheckBox);

        JCheckBox teacherCheckBox = new JCheckBox("Teacher");
        teacherCheckBox.setBounds(250, 150, 100, 25);
        loginFrame.add(teacherCheckBox);

        ButtonGroup roleGroup = new ButtonGroup();
        roleGroup.add(studentCheckBox);
        roleGroup.add(teacherCheckBox);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 190, 100, 30);
        loginFrame.add(loginButton);

        loginButton.addActionListener(e -> {
        	String usename = "minhnhat26";
        	String password = "minhnhat26";
        	String name = userField.getText();
        	String pass = passField.getText();
            if (name.equals(usename) && pass.equals(password) && teacherCheckBox.isSelected()) {
                loginFrame.dispose(); 
                teacher_manager.teachertopicManagementScreen();
            }else if(!name.equals(usename) && !pass.equals(password) && teacherCheckBox.isSelected()) {
            	JOptionPane.showMessageDialog(loginFrame,"You are not a teacher=)))","Errol", JOptionPane.ERROR_MESSAGE);
            }
            else if (studentCheckBox.isSelected()) {
                loginFrame.dispose();
                student_manager.studenttopicManagementScreen();
                
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Please select a role!", "Errol", JOptionPane.ERROR_MESSAGE);
            }
        });

        loginFrame.setVisible(true);
    }
}



    

