package doancuoiky;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class sql_connect {
//	public void reloadData(DefaultTableModel tableModel) {
//	    // Xóa dữ liệu cũ trong bảng
//	    tableModel.setRowCount(0);
//	}
	private static final String DB_URL = "jdbc:mysql://localhost:3306/m_nhat";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "mnhat26";


	
    // Phương thức lưu dữ liệu sinh viên vào CSDL
    public void saveStudentData(String id, String name, String className, String email, String gender, String dob, String address, String topic_Name) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Kết nối tới cơ sở dữ liệu
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //Câu lệnh SQL để chèn dữ liệu
            String query = "INSERT INTO student_list (`id`, `name`, `class`, `email`, `gender`, `dob`, `address` ,`topic name`) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, className);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, gender);
            preparedStatement.setString(6, dob);
            preparedStatement.setString(7, address);
            preparedStatement.setString(8, topic_Name);

            //Thực thi câu lệnh
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data saved successfully!");

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving data: " + ex.getMessage());
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

	}


