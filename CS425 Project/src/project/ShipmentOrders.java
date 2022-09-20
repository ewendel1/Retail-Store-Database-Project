package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;

public class ShipmentOrders extends JFrame {

	private JPanel contentPane;
	private JTable table_0;
	private JTable table2;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShipmentOrders frame = new ShipmentOrders();
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
	public ShipmentOrders() {
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
			new CustomerPage().setVisible(true);
			dispose();
		}
	});
	btnNewButton.setBounds(10, 10, 45, 39);
	contentPane.add(btnNewButton);
	
	String[] options = {
			 "Options",
	         "My Profile",
	         "View Cart",
	         "My Orders",
	         "Log out",
	};
	JComboBox settings = new JComboBox(options);
	settings.setEditable(true);
	settings.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String val = (String) settings.getSelectedItem();
			if (val.compareTo("My Profile")==0) {
				new CustomerInfo().setVisible(true);
				dispose();
			}
			else if(val.compareTo("View Cart")==0) {
				new Cart().setVisible(true);
				dispose();
			}
			else if(val.compareTo("My Orders")==0) {
				new ShipmentOrders().setVisible(true);
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
	
	
	//table1
	String[][] data = new String[12][3];
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL", "HR", "oracle");
		BufferedReader reader = new BufferedReader(new FileReader("CUSTOMER.ser"));
	    String customerID = reader.readLine();
	    reader.close();
	    BufferedReader readOrder = new BufferedReader(new FileReader("ORDER.ser"));
	    String orderID = readOrder.readLine();
	    readOrder.close();
        PreparedStatement pst = conn.prepareStatement("select * from CUSTOMERS natural join SHIPPINGORDER natural join ADDRESS where CUSTOMERID="+customerID);
       	ResultSet rs = pst.executeQuery();
        Statement st = conn.createStatement();
        
        //totalPrice = 0;
        int i = 0;
        while(rs.next()) {
	        	data[i][0] = rs.getString("TRACKINGNUMBER");
	        	data[i][1] = ""+rs.getString("STREET") + " " + rs.getString("CITY");
	        	PreparedStatement pst3 = conn.prepareStatement("select * from CART natural join CUSTOMERS natural join PRODUCTS where CUSTOMERID = "+customerID);
				ResultSet rs3 = pst3.executeQuery();
				double totprice = 0;
				while(rs3.next()) {
					totprice += Integer.parseInt(rs3.getString("PRICE"));
				}
				data[i][2] = Double.toString(totprice);
				i++;
        }
        st.close();
    	conn.close();
    	
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	String[] columnNames = {"# Tracking", "Address", "Total Price"};
	
	
	String[][] data2 = new String[12][4];

	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(10, 138, 371, 227);
	getContentPane().add(scrollPane);
	table_0 = new JTable(data,columnNames);
	table_0.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
		}
	});
	scrollPane.setViewportView(table_0);
	
	
	
	
	String[] columnNames2 = {"Product Name", "Brand", "Price"};
	
	
	JScrollPane scrollPane2 = new JScrollPane();
	scrollPane2.setBounds(431, 138, 355, 227);
	getContentPane().add(scrollPane2);
	table_1 = new JTable(data2,columnNames2);
	scrollPane2.setViewportView(table_1);
	
	JLabel lblNewLabel_1 = new JLabel("My Orders");
	lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
	lblNewLabel_1.setBounds(173, 89, 111, 29);
	contentPane.add(lblNewLabel_1);
	
	JLabel lblNewLabel_1_1 = new JLabel("Items");
	lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
	lblNewLabel_1_1.setBounds(565, 89, 111, 29);
	contentPane.add(lblNewLabel_1_1);
	
	JButton btnNewButton_1 = new JButton(">");
	btnNewButton_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL", "HR", "oracle");
				BufferedReader reader = new BufferedReader(new FileReader("CUSTOMER.ser"));
			    String customerID = reader.readLine();
			    reader.close();
		        PreparedStatement pst = conn.prepareStatement("select * from CUSTOMERS natural join SHIPPINGORDER natural "
		        		+ "join ORDERS natural join PRODUCTS where CUSTOMERID ='"+customerID+"'");
		       	ResultSet rs = pst.executeQuery();
		        Statement st = conn.createStatement();
		        int i = 0;
		        while(rs.next()) {	            
		            data2[i][0]= rs.getString("PRODUCTNAME");
		            PreparedStatement pst2 = conn.prepareStatement("select * from PRODUCTTYPE where CATEGORYID = "+rs.getString("CATEGORYID"));
	                ResultSet rs2 = pst2.executeQuery();
	                while(rs2.next()) {
	                	data[i][1] = rs2.getString("CATEGORYNAME");
	                }
		            data2[i][2]= rs.getString("PRICE");
		        	i++;

		        }
		        
		        st.close();
		    	conn.close();

			} catch (Exception er) {
				er.printStackTrace();
			}
			
		}
	});
	btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
	btnNewButton_1.setBounds(384, 241, 45, 21);
	contentPane.add(btnNewButton_1);
	

	
	
	}
}
	
