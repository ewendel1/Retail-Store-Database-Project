package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class EmployeeSignup extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField email;
	private JTextField age;
	private JTextField sex;
	private JTextField phoneNumber;
	private JTextField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeSignup frame = new EmployeeSignup();
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
	public EmployeeSignup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 555);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee Sign Up");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(60, 10, 188, 39);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name *");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(38, 90, 65, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Email *");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(38, 125, 65, 13);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Age");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_2.setBounds(38, 154, 45, 20);
		contentPane.add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("Sex");
		lblNewLabel_1_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_2_1.setBounds(38, 187, 45, 20);
		contentPane.add(lblNewLabel_1_1_2_1);
		
		JLabel lblNewLabel_1_1_2_1_1 = new JLabel("Phone Number");
		lblNewLabel_1_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_2_1_1.setBounds(38, 221, 125, 20);
		contentPane.add(lblNewLabel_1_1_2_1_1);
		
		JLabel lblNewLabel_1_1_2_1_1_1 = new JLabel("Password *");
		lblNewLabel_1_1_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_2_1_1_1.setBounds(38, 251, 125, 20);
		contentPane.add(lblNewLabel_1_1_2_1_1_1);
		
		name = new JTextField();
		name.setBounds(166, 89, 125, 20);
		contentPane.add(name);
		name.setColumns(10);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(166, 119, 125, 20);
		contentPane.add(email);
		
		age = new JTextField();
		age.setColumns(10);
		age.setBounds(166, 154, 125, 20);
		contentPane.add(age);
		
		sex = new JTextField();
		sex.setColumns(10);
		sex.setBounds(166, 187, 125, 20);
		contentPane.add(sex);
		
		phoneNumber = new JTextField();
		phoneNumber.setColumns(10);
		phoneNumber.setBounds(166, 221, 125, 20);
		contentPane.add(phoneNumber);
		
		password = new JTextField();
		password.setColumns(10);
		password.setBounds(166, 254, 125, 20);
		contentPane.add(password);
		
		JButton btnNewButton = new JButton("Sign Up");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL", "HR", "oracle");
					PreparedStatement pst = conn.prepareStatement("select EMPLOYEE_SEQUENCE.NEXTVAL from dual");
		            synchronized( this ) {
		               ResultSet rs = pst.executeQuery();
		               long myId = 0;
		               if(rs.next()) {
		                 myId = rs.getLong(1);
		               }
		               Statement st = conn.createStatement();
		               st.executeUpdate("INSERT INTO EMPLOYEES (EMPLOYEEID, NAME, EMAIL, SEX, PHONENUMBER, PASSWORD) VALUES ('"+myId+"', '"+name.getText() +"', '"+email.getText()+"', '"
		            		+ sex.getText()+"', '"+Integer.parseInt(phoneNumber.getText())+"', '"+password.getText()+"')");
		               st.close();
		            }
	            	conn.close();
	            	new MainMenu().setVisible(true);
					dispose();
				} catch (Exception r) {
					r.printStackTrace();
				}
			}	
				
				
		});

		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(125, 297, 101, 29);
		contentPane.add(btnNewButton);
		
		Icon icon = new ImageIcon("C:\\Users\\ijahw\\Documents\\IIT\\Spring 2022\\CS 425\\home.png");
		JButton homeButton = new JButton(icon);
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MainMenu().setVisible(true);
				dispose();
			}
		});
		homeButton.setBounds(10, 10, 45, 39);
		contentPane.add(homeButton);
		
	}

}
