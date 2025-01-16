package doancuoiky;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class student_manager {
	
	 public static boolean isValidName(String name) {
	    if (name == null || name.trim().isEmpty()) {
	        return false; // Không cho phép tên rỗng hoặc chỉ toàn khoảng trắng
	    }
	    for (int i = 0; i < name.length(); i++) {
	        char c = name.charAt(i);
	        if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
	            return false; // Tên không được chứa số hoặc ký tự đặc biệt
	        }
	    }
	    return true;
	}

	 public static void studenttopicManagementScreen() {
	        // JFrame
	        JFrame frame = new JFrame("Registration Form");
	        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
	        frame.setSize(750, 550);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setLayout(null);

	        // Tạo JPanel cho phần thông tin sinh viên
	        JPanel studentInfoPanel = new JPanel();
	        studentInfoPanel.setLayout(null);
	        studentInfoPanel.setBounds(10, 10, 450, 450);
	        TitledBorder titledBorder = BorderFactory.createTitledBorder(
	            BorderFactory.createLineBorder(Color.GRAY, 2), " Student Information ");
	        titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 14));
	        studentInfoPanel.setBorder(titledBorder);
	        frame.add(studentInfoPanel);

	        // ID
	        JLabel idLabel = new JLabel("ID:");
	        idLabel.setBounds(20, 30, 100, 20);
	        studentInfoPanel.add(idLabel);

	        JTextField idField = new JTextField();
	        idField.setBounds(150, 30, 200, 25);
	        studentInfoPanel.add(idField);

	        // Name
	        JLabel nameLabel = new JLabel("Name:");
	        nameLabel.setBounds(20, 70, 100, 20);
	        studentInfoPanel.add(nameLabel);
	        

	        JTextField nameField = new JTextField();
	        nameField.setBounds(150, 70, 200, 25);
	        studentInfoPanel.add(nameField);

	        // Class
	        JLabel classLabel = new JLabel("Class:");
	        classLabel.setBounds(20, 110, 100, 20);
	        studentInfoPanel.add(classLabel);

	        JTextField classField = new JTextField();
	        classField.setBounds(150, 110, 200, 25);
	        studentInfoPanel.add(classField);

	        // Email
	        JLabel emailLabel = new JLabel("Email:");
	        emailLabel.setBounds(20, 150, 100, 20);
	        studentInfoPanel.add(emailLabel);

	        JTextField emailField = new JTextField();
	        emailField.setBounds(150, 150, 200, 25);
	        studentInfoPanel.add(emailField);

	        // Gender
	        JLabel genderLabel = new JLabel("Gender:");
	        genderLabel.setBounds(20, 190, 100, 20);
	        studentInfoPanel.add(genderLabel);

	        JRadioButton maleButton = new JRadioButton("Male");
	        maleButton.setBounds(150, 190, 70, 20);
	        JRadioButton femaleButton = new JRadioButton("Female");
	        femaleButton.setBounds(220, 190, 80, 20);

	        ButtonGroup genderGroup = new ButtonGroup();
	        genderGroup.add(maleButton);
	        genderGroup.add(femaleButton);
	        studentInfoPanel.add(maleButton);
	        studentInfoPanel.add(femaleButton);

	        // DOB
	        JLabel dobLabel = new JLabel("DOB:");
	        dobLabel.setBounds(20, 230, 100, 20);
	        studentInfoPanel.add(dobLabel);

	        ArrayList<String> daysList = new ArrayList<>();
	        for (int i = 1; i <= 31; i++) {
	          daysList.add(String.valueOf(i));
	        }
	        ArrayList<String> monthsList = new ArrayList<>();
	        String[] monthNames = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
	        for (String month : monthNames) {
	          monthsList.add(month);
	        }
	        ArrayList<String> yearsList = new ArrayList<>();
	        for (int i = 2000; i <= 2010; i++) {
	          yearsList.add(String.valueOf(i));
	        }

	        JComboBox<String> dayBox = new JComboBox<>(daysList.toArray(new String[0]));
	        dayBox.setBounds(150, 230, 50, 25);
	        JComboBox<String> monthBox = new JComboBox<>(monthsList.toArray(new String[0]));
	        monthBox.setBounds(210, 230, 60, 25);
	        JComboBox<String> yearBox = new JComboBox<>(yearsList.toArray(new String[0]));
	        yearBox.setBounds(280, 230, 70, 25);

	        studentInfoPanel.add(dayBox);
	        studentInfoPanel.add(monthBox);
	        studentInfoPanel.add(yearBox);
	      

	        // Address
	        JLabel addressLabel = new JLabel("Address:");
	        addressLabel.setBounds(20, 270, 100, 20);
	        studentInfoPanel.add(addressLabel);

	        JTextArea addressArea = new JTextArea();
	        addressArea.setBounds(150, 270, 200, 50);
	        studentInfoPanel.add(addressArea);
	        
	        //Topic name
	        JLabel topicLabel = new JLabel ("Topic Name:");
	        topicLabel.setBounds(20,330,100,20);
	        studentInfoPanel.add(topicLabel);
	        
	        JTextArea topicArea = new JTextArea();
	        topicArea.setBounds(150,330,200,50);
	        studentInfoPanel.add(topicArea);
	        
	        //Exit
	        JButton exitButton = new JButton("Exit");
	        exitButton.setBounds(550,460,80,25);
	        frame.add(exitButton);
	        
	        exitButton.addActionListener(e -> {
	            int confirm = JOptionPane.showConfirmDialog(frame, "Do you want to Exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
	            if (confirm == JOptionPane.YES_OPTION) {
	                frame.dispose();
	                topic_management.loginScreen();
	            }
	        });

	        // Submit and Reset Buttons
	        JButton submitButton = new JButton("Submit");
	        submitButton.setBounds(150, 390, 90, 25);
	        studentInfoPanel.add(submitButton);

	        JButton resetButton = new JButton("Reset");
	        resetButton.setBounds(260, 390, 90, 25);
	        studentInfoPanel.add(resetButton);
	        
	        resetButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Xóa nội dung các JTextField
                    idField.setText("");
                    nameField.setText("");
                    classField.setText("");
                    emailField.setText("");

                    // Xóa lựa chọn JRadioButton
                    genderGroup.clearSelection();

                    // Đặt lại JComboBox về giá trị đầu tiên
                    dayBox.setSelectedIndex(0);
                    monthBox.setSelectedIndex(0);
                    yearBox.setSelectedIndex(0);

                    // Xóa nội dung JTextArea
                    addressArea.setText("");
                    topicArea.setText("");

                }
	        });
	        
	        submitButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String id = idField.getText();
	                String name = nameField.getText();
	                String className = classField.getText();
	                String email = emailField.getText();
	                String gender = maleButton.isSelected() ? "Male" : "Female";
	                String dob = yearBox.getSelectedItem() + "-" + monthBox.getSelectedItem() + "-" + dayBox.getSelectedItem();
	                String address = addressArea.getText();
	                String topic_Name = topicArea.getText();
	                
	             // Kiểm tra dữ liệu của nameField
	                if (!isValidName(name)) {
	                    JOptionPane.showMessageDialog(frame, 
	                        "Name is invalid! Name must not contain numbers or special characters.", 
	                        "Invalid Name", 
	                        JOptionPane.ERROR_MESSAGE);
	                    return; // Dừng xử lý nếu dữ liệu không hợp lệ
	                }

	                // Tạo đối tượng và gọi phương thức lưu dữ liệu
	                sql_connect dbHandler = new sql_connect();
	                dbHandler.saveStudentData(id, name, className, email, gender, dob, address, topic_Name);
	            }
	        });

	     // Panel hiển thị ảnh
	        JPanel imagePanel = new JPanel();
	        imagePanel.setLayout(null);
	        imagePanel.setBounds(470, 10, 250, 400);
	        frame.add(imagePanel);

	        // JLabel để hiển thị icon lớn
	        JLabel imageLabel = new JLabel();
	        imageLabel.setBounds(20, 70, 200, 250);

	        // Thêm icon lớn
	        try {
	            
	            String imagePath = "C:\\Users\\Administrator\\Downloads\\javapicture2.jpg";
	            File imageFile = new File(imagePath);
	            
	            if (imageFile.exists()) {
	                ImageIcon iconLarge = new ImageIcon(imagePath);
	                Image iconImage = iconLarge.getImage();
	                
	                // Tính tỷ lệ để thay đổi kích thước ảnh sao cho vừa với JLabel
	                double widthRatio = (double) imageLabel.getWidth() / iconImage.getWidth(null);
	                double heightRatio = (double) imageLabel.getHeight() / iconImage.getHeight(null);
	                
	                // Chọn tỷ lệ nhỏ hơn để đảm bảo ảnh không bị vỡ
	                double scaleRatio = Math.min(widthRatio, heightRatio);
	                
	                // Tính kích thước mới của ảnh dựa trên tỷ lệ
	                int newWidth = (int) (iconImage.getWidth(null) * scaleRatio);
	                int newHeight = (int) (iconImage.getHeight(null) * scaleRatio);
	                
	                // Thay đổi kích thước ảnh
	                iconImage = iconImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
	                
	                // Cập nhật ảnh vào JLabel
	                iconLarge = new ImageIcon(iconImage);
	                imageLabel.setIcon(iconLarge);
	                imagePanel.add(imageLabel);
	            } 
	            frame.setVisible(true);
	        } catch (Exception ex) {
	            
	        }
	    }
}