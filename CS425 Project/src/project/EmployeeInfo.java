package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class EmployeeInfo extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField email;
	private JTextField sex;
	private JTextField password;
	private JTextField phoneNumber;
	private String employeeID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeInfo frame = new EmployeeInfo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EmployeeInfo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 555);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Icon icon = new ImageIcon("C:\\Users\\ijahw\\Documents\\IIT\\Spring 2022\\CS 425\\home.png");
		JButton btnNewButton = new JButton(icon);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EmployeePage().setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(10, 10, 45, 39);
		contentPane.add(btnNewButton);
		
		String[] options = {
		        "Options",
				"Customers",
				"Orders",
				"My Profile",
		        "Log out",
		};
		JComboBox settings = new JComboBox(options);
		settings.setEditable(true);
		settings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String val = (String) settings.getSelectedItem();
				if (val.compareTo("My Profile")==0) {
					new EmployeeInfo().setVisible(true);
					dispose();
				}			
				else if(val.compareTo("Customers")==0) {
					new ViewCustomers().setVisible(true);
					dispose();
				}
				else if(val.compareTo("Orders")==0) {
					new ViewOrders().setVisible(true);
					dispose();
				}
				else if(val.compareTo("Log out")==0) {
					new MainMenu().setVisible(true);
					dispose();
				}
			}
		});
		settings.setBounds(684, 10, 102, 39);
		getContentPane().add(settings);
		
		JLabel lblNewLabel = new JLabel("My profile");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(64, 10, 188, 39);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name *");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(38, 90, 55, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Email *");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(38, 125, 55, 13);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Sex");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_2.setBounds(38, 154, 45, 20);
		contentPane.add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("Password");
		lblNewLabel_1_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_2_1.setBounds(38, 187, 90, 20);
		contentPane.add(lblNewLabel_1_1_2_1);
		
		JLabel lblNewLabel_1_1_2_1_1 = new JLabel("Phone Number");
		lblNewLabel_1_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_2_1_1.setBounds(38, 221, 125, 20);
		contentPane.add(lblNewLabel_1_1_2_1_1);
		
		name = new JTextField();
		name.setBounds(166, 89, 125, 20);
		contentPane.add(name);
		name.setColumns(10);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(166, 119, 125, 20);
		contentPane.add(email);
		
		sex = new JTextField();
		sex.setColumns(10);
		sex.setBounds(166, 154, 125, 20);
		contentPane.add(sex);
		
		password = new JTextField();
		password.setColumns(10);
		password.setBounds(166, 187, 125, 20);
		contentPane.add(password);
		
		phoneNumber = new JTextField();
		phoneNumber.setColumns(10);
		phoneNumber.setBounds(166, 221, 125, 20);
		contentPane.add(phoneNumber);
		
		try {
		    BufferedReader reader = new BufferedReader(new FileReader("EMPLOYEE.ser"));
		  	employeeID = reader.readLine();
		    reader.close();
		    Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL", "HR", "oracle");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from EMPLOYEES where EMPLOYEEID='"+employeeID+"'");
            if(rs.next()) {
	            name.setText(rs.getString("NAME"));
	            email.setText(rs.getString("EMAIL"));
	            sex.setText(rs.getString("SEX"));
	            phoneNumber.setText(rs.getString("PHONENUMBER"));
	            password.setText(rs.getString("PASSWORD"));
            }
            rs.close();
            st.close();		            
        	conn.close();  
		}
		catch(Exception ex) {
		    ex.printStackTrace();
		}
		
		JButton btnNewButton_1 = new JButton("Modify");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL", "HR", "oracle");
					
		            Statement st = conn.createStatement();

		            st.executeUpdate("UPDATE EMPLOYEES SET NAME='"+name.getText() +"', EMAIL='"
		            		+email.getText()+"', SEX = '"+ sex.getText()+"', PHONENUMBER = '"+Integer.parseInt(phoneNumber.getText())
		            		+"' WHERE EMPLOYEEID = '"+employeeID+"'");
			        st.close();		            
	            	conn.close();     
				} catch (Exception r) {
					r.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(116, 266, 94, 32);
		contentPane.add(btnNewButton_1);
	}

}
