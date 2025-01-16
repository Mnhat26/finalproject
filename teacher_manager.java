package doancuoiky;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class teacher_manager {

    public static void teachertopicManagementScreen() {
        // JFrame
        JFrame frame = new JFrame("Topic Management");
        frame.setSize(1100, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setLayout(null);

        // Tạo khung bảng chính
        JPanel studentInfoPanel = new JPanel();
        studentInfoPanel.setBackground(Color.GRAY);
        studentInfoPanel.setLayout(null);
        studentInfoPanel.setBounds(10, 110, 590, 340);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(
        BorderFactory.createLineBorder(Color.WHITE, 2), " Students ");
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 14));
        studentInfoPanel.setBorder(titledBorder);
        frame.add(studentInfoPanel);

        // Tạo khung chi tiết sinh viên
        JPanel studentDetailPanel = new JPanel();
        studentDetailPanel.setBackground(Color.GRAY);
        studentDetailPanel.setLayout(null);
        studentDetailPanel.setBounds(610, 110, 460, 340);
        TitledBorder detailBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.WHITE, 2), " Student Details ");
        detailBorder.setTitleFont(new Font("Arial", Font.BOLD, 14));
        studentDetailPanel.setBorder(detailBorder);
        frame.add(studentDetailPanel);

        // Thêm các trường thông tin chi tiết
        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(20, 20, 100, 25);
        studentDetailPanel.add(idLabel);

        JTextField idField = new JTextField();
        idField.setBounds(120, 20, 300, 25);
        idField.setEditable(false);
        studentDetailPanel.add(idField);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 60, 100, 25);
        studentDetailPanel.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(120, 60, 300, 25);
        nameField.setEditable(false);
        studentDetailPanel.add(nameField);

        JLabel classLabel = new JLabel("Class:");
        classLabel.setBounds(20, 100, 100, 25);
        studentDetailPanel.add(classLabel);

        JTextField classField = new JTextField();
        classField.setBounds(120, 100, 300, 25);
        classField.setEditable(false);
        studentDetailPanel.add(classField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(20, 140, 100, 25);
        studentDetailPanel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(120, 140, 300, 25);
        emailField.setEditable(false);
        studentDetailPanel.add(emailField);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(20, 180, 100, 25);
        studentDetailPanel.add(genderLabel);

        JTextField genderField = new JTextField();
        genderField.setBounds(120, 180, 300, 25);
        genderField.setEditable(false);
        studentDetailPanel.add(genderField);

        JLabel dobLabel = new JLabel("DOB:");
        dobLabel.setBounds(20, 220, 100, 25);
        studentDetailPanel.add(dobLabel);

        JTextField dobField = new JTextField();
        dobField.setBounds(120, 220, 300, 25);
        dobField.setEditable(false);
        studentDetailPanel.add(dobField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(20, 260, 100, 25);
        studentDetailPanel.add(addressLabel);

        JTextField addressField = new JTextField();
        addressField.setBounds(120, 260, 300, 25);
        addressField.setEditable(false);
        studentDetailPanel.add(addressField);

        JLabel topicLabel = new JLabel("Topic Name:");
        topicLabel.setBounds(20, 300, 100, 25);
        studentDetailPanel.add(topicLabel);

        JTextField topicField = new JTextField();
        topicField.setBounds(120, 300, 300, 25);
        topicField.setEditable(false);
        studentDetailPanel.add(topicField);

        // Title
        JLabel titleLabel = new JLabel("Topic Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        titleLabel.setBounds(410, 10, 330, 60);
        frame.add(titleLabel);

        // Enter value to search
        JLabel searchLabel = new JLabel("Enter value to search: ");
        searchLabel.setFont(new Font("Arial", Font.BOLD, 15));
        searchLabel.setBounds(20, 70, 200, 30);
        frame.add(searchLabel);

        JTextField searchTextfield = new JTextField();
        searchTextfield.setBounds(200, 75, 200, 20);
        frame.add(searchTextfield);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(410, 75, 80, 20);
        frame.add(searchButton);

        // Nút delete
        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(505, 75, 80, 20);
        frame.add(deleteButton);

        // Nút Exit
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(950, 500, 100, 30);
        frame.add(exitButton);

        exitButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame, "Do you want to Exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                frame.dispose();
                topic_management.loginScreen();
            }
        });

        // Không cho sửa trong giao diện chính
        class NonEditableTableModel extends DefaultTableModel {
            public NonEditableTableModel(String[] columnNames, int rowCount) {
                super(columnNames, rowCount);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        }

        // Tạo bảng chính với DefaultTableModel
        String[] columns = {"ID", "Name", "Class", "Topic"};
        NonEditableTableModel model = new NonEditableTableModel(columns, 0);
        JTable table = new JTable(model);

        // Tùy chỉnh độ rộng của từng cột
        table.getColumnModel().getColumn(0).setPreferredWidth(30);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setPreferredWidth(30);
        table.getColumnModel().getColumn(3).setPreferredWidth(170);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 550, 300);
        studentInfoPanel.add(scrollPane);

        // Chức năng click vào bảng
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                int selectedRow = table.getSelectedRow();
                String selectedId = model.getValueAt(selectedRow, 0).toString();

                try {
                    // Kết nối cơ sở dữ liệu
                    String url = "jdbc:mysql://localhost:3306/m_nhat";
                    String user = "root";
                    String password = "mnhat26";

                    Connection connection = DriverManager.getConnection(url, user, password);

                    // Truy vấn chi tiết sinh viên
                    String query = "SELECT * FROM student_list WHERE id = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, selectedId);

                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        idField.setText(resultSet.getString("id"));
                        nameField.setText(resultSet.getString("name"));
                        classField.setText(resultSet.getString("class"));
                        emailField.setText(resultSet.getString("email"));
                        genderField.setText(resultSet.getString("gender"));
                        dobField.setText(resultSet.getString("dob"));
                        addressField.setText(resultSet.getString("address"));
                        topicField.setText(resultSet.getString("topic name"));
                    }

                    resultSet.close();
                    preparedStatement.close();
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error loading student details: " + ex.getMessage(),
                            "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Chức năng xóa
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Please select a student to delete!", "Selection Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String selectedId = model.getValueAt(selectedRow, 0).toString();

            int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this student?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    String url = "jdbc:mysql://localhost:3306/m_nhat";
                    String user = "root";
                    String password = "mnhat26";

                    Connection connection = DriverManager.getConnection(url, user, password);

                    String query = "DELETE FROM student_list WHERE id = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, selectedId);

                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(frame, "Student deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        model.removeRow(selectedRow);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Failed to delete student!", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    preparedStatement.close();
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error deleting student: " + ex.getMessage(),
                            "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Chức năng tìm kiếm  
        searchButton.addActionListener(e -> {
            String searchValue = searchTextfield.getText().trim();
            if (searchValue.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter a value to search!", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                String url = "jdbc:mysql://localhost:3306/m_nhat";
                String user = "root";
                String password = "mnhat26";

                Connection connection = DriverManager.getConnection(url, user, password);

                String query = "SELECT id, name, class, `topic name` FROM student_list WHERE name LIKE ? OR class LIKE ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, "%" + searchValue + "%");
                preparedStatement.setString(2, "%" + searchValue + "%");

                ResultSet resultSet = preparedStatement.executeQuery();

                model.setRowCount(0); // Xóa dữ liệu cũ trên bảng
                while (resultSet.next()) {
                    String id = resultSet.getString("id");
                    String name = resultSet.getString("name");
                    String className = resultSet.getString("class");
                    String topic = resultSet.getString("topic name");

                    model.addRow(new Object[]{id, name, className, topic});
                }

                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error searching data: " + ex.getMessage(),
                        "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Hiển thị lại danh sách đầy đủ khi xóa nội dung tìm kiếm
        searchTextfield.getDocument().addDocumentListener(new DocumentListener() {
            private void reloadFullTable() {
                if (searchTextfield.getText().trim().isEmpty()) {
                    try {
                        String url = "jdbc:mysql://localhost:3306/m_nhat";
                        String user = "root";
                        String password = "mnhat26";

                        Connection connection = DriverManager.getConnection(url, user, password);

                        String query = "SELECT id, name, class, `topic name` FROM student_list";
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery(query);

                        model.setRowCount(0); // Xóa dữ liệu cũ
                        while (resultSet.next()) {
                            String id = resultSet.getString("id");
                            String name = resultSet.getString("name");
                            String className = resultSet.getString("class");
                            String topic = resultSet.getString("topic name");

                            model.addRow(new Object[]{id, name, className, topic});
                        }

                        resultSet.close();
                        statement.close();
                        connection.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Error reloading data: " + ex.getMessage(),
                                "Database Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                reloadFullTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                reloadFullTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                reloadFullTable();
            }
        });

        // Kết nối cơ sở dữ liệu và nạp dữ liệu vào bảng
        try {
            String url = "jdbc:mysql://localhost:3306/m_nhat";
            String user = "root";
            String password = "mnhat26";

            Connection connection = DriverManager.getConnection(url, user, password);

            String query = "SELECT id, name, class, `topic name` FROM student_list";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String className = resultSet.getString("class");
                String topic = resultSet.getString("topic name");

                model.addRow(new Object[]{id, name, className, topic});
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error connecting to database: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        frame.setVisible(true);
    }
}
