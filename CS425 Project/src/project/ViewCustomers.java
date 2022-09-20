package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;

public class ViewCustomers extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewCustomers frame = new ViewCustomers();
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
	public ViewCustomers() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 555);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Icon icon = new ImageIcon("C:\\Users\\ijahw\\Documents\\IIT\\Spring 2022\\CS 425\\home.png");
		JButton homeButton = new JButton(icon);
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EmployeePage().setVisible(true);
				dispose();
			}
		});
		homeButton.setBounds(10, 10, 45, 39);
		contentPane.add(homeButton);
		
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
		
		String[][] data = new String[20][9];
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL", "HR", "oracle");
            PreparedStatement pst = conn.prepareStatement("select * from CUSTOMERS natural join ADDRESS natural join PAYMENT");
            ResultSet rs = pst.executeQuery();
            int i =0;
            while(rs.next()) {
            	data[i][0] = rs.getString("CUSTOMERID");
            	data[i][1] = rs.getString("NAME");
            	data[i][2] = rs.getString("EMAIL");
            	data[i][3] = rs.getString("AGE");
            	data[i][4] = rs.getString("SEX");
            	data[i][5] = rs.getString("PHONENUMBER");
            	data[i][6] = rs.getString("PASSWORD");
            	data[i][7] = rs.getString("STREET");
            	data[i][8] = rs.getString("PAYMENTTYPE");
            	i++;
            }
            
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String[] columnNames = {"CustomerID", "Name", "Email", "Age", "Sex", "Phone Number", "Password", "Address", "Payment Type"};
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(69, 88, 644, 331);
		getContentPane().add(scrollPane);
		table = new JTable(data,columnNames);
		scrollPane.setViewportView(table);
		
	}
}
