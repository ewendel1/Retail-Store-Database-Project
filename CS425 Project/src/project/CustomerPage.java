package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.List;
import java.awt.TextArea;
import java.awt.ScrollPane;
import javax.swing.JTable;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerPage extends JFrame {
	private JTable table;
	private String name;
	private String price;
	private String stock;
	private String brand;
	private String category;
	private String productid;
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerPage frame = new CustomerPage();
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
	
	public CustomerPage() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 555);
		getContentPane().setLayout(null);
		
		String[][] data = new String[20][5];
			
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL", "HR", "oracle");
            PreparedStatement pst = conn.prepareStatement("select * from Products");
           	ResultSet rs = pst.executeQuery();
            Statement st = conn.createStatement();
            int i = 0;
            while(rs.next()) {
            	data[i][0] = rs.getString("PRODUCTID");
            	data[i][1] = rs.getString("PRODUCTNAME");
                PreparedStatement pst2 = conn.prepareStatement("select * from PRODUCTTYPE where CATEGORYID = "+rs.getString("CATEGORYID"));
                ResultSet rs2 = pst2.executeQuery();
                while(rs2.next()) {
                	data[i][2] = rs2.getString("CATEGORYNAME");
                }
            	data[i][3] = rs.getString("STOCK");
            	data[i][4] = rs.getString("PRICE");
            	i++;
            }
	        st.close();
        	conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String[] columnNames = {"ID", "Product Name", "Brand", "# in Stock", "Price"};
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(69, 88, 644, 331);
		getContentPane().add(scrollPane);
		table = new JTable(data,columnNames);
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			} 
		});
		
		
		
		JLabel lblNewLabel = new JLabel("Retail App");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(74, 22, 151, 25);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Search:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(77, 57, 90, 21);
		getContentPane().add(lblNewLabel_1);
		
		
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
		
		
		JButton btnNewButton_1 = new JButton("Add to cart");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					productid = table.getValueAt(table.getSelectedRow(),0).toString();
					name = table.getValueAt(table.getSelectedRow(),1).toString();
					stock = table.getValueAt(table.getSelectedRow(),3).toString();
					price = table.getValueAt(table.getSelectedRow(),4).toString();
					
					BufferedReader reader = new BufferedReader(new FileReader("CUSTOMER.ser"));
				    String customerID = reader.readLine();
				    reader.close();
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL", "HR", "oracle");			
					BufferedReader readOrd = new BufferedReader(new FileReader("ORDER.ser"));
	    			String orderID = readOrd.readLine();
	    			readOrd.close();
		            if(Integer.parseInt(stock)>0) {
		            	Statement st = conn.createStatement();
		            	st.executeUpdate("INSERT INTO CART (CARTID, CUSTOMERID, PRODUCTID) "
			            		+ "VALUES ('"+orderID+"', '"+customerID+"', '"+productid+"')");
		            	
		            	st.close();
		            }
		            else {
						JOptionPane.showMessageDialog(null, "Out of stock!");
		            }
		        	conn.close();
				} catch (Exception r) {
					JOptionPane.showMessageDialog(null, "Error, try again");
					r.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(591, 442, 122, 31);
		getContentPane().add(btnNewButton_1);
		
		Icon icon = new ImageIcon("C:\\Users\\ijahw\\Documents\\IIT\\Spring 2022\\CS 425\\home.png");
		JButton homeButton = new JButton(icon);
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		homeButton.setBounds(10, 10, 45, 39);
		getContentPane().add(homeButton);
		
		JButton btnNewButton_1_1 = new JButton("Check out");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Cart().setVisible(true);
				dispose();
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1_1.setBounds(591, 477, 122, 31);
		getContentPane().add(btnNewButton_1_1);
	
	}
}
