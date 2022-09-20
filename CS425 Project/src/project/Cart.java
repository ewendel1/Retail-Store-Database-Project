package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class Cart extends JFrame {

	private JPanel contentPane;
	private JTable tableCart;
	private String name;
	private String price;
	private String stock;
	private String brand;
	private String category;
	private String id;
	private double totalPrice;
	private String cartID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cart frame = new Cart();
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
	public Cart() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 555);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Items in cart");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(83, 18, 142, 31);
		contentPane.add(lblNewLabel);
		
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
		
		
		String[][] data = new String[12][4];
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL", "HR", "oracle");
			BufferedReader reader = new BufferedReader(new FileReader("CUSTOMER.ser"));
		    String customerID = reader.readLine();
		    reader.close();
            //PreparedStatement pst = conn.prepareStatement("select * from CART where CUSTOMERID ='"+customerID+"'");
           	//ResultSet rs = pst.executeQuery();
            //Statement st = conn.createStatement();
            //totalPrice = 0;
            int i = 0;
            //while(rs.next()) {
            	//data[i][0] = rs.getString("ORDERID");
            	BufferedReader readOrd = new BufferedReader(new FileReader("ORDER.ser"));
    			cartID = readOrd.readLine();
    			readOrd.close();
                PreparedStatement pst2 = conn.prepareStatement("select * from PRODUCTS natural join Cart where CARTID="+cartID);
                ResultSet rs2 = pst2.executeQuery();
                
                while(rs2.next()) {
                	data[i][0]= rs2.getString("PRODUCTNAME");
                	PreparedStatement pst3 = conn.prepareStatement("select * from PRODUCTTYPE where CATEGORYID = "+rs2.getString("CATEGORYID"));
                    ResultSet rs3 = pst3.executeQuery();
                    while(rs3.next()) {
                    	data[i][1] = rs3.getString("CATEGORYNAME");
                    }
                	data[i][2] = rs2.getString("STOCK");
                    data[i][3] = rs2.getString("PRICE");
                	totalPrice += Double.parseDouble(rs2.getString("PRICE"));
                	i++;
                }
            //}
	        //st.close();
        	conn.close();
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String[] columnNames = {"Product Name", "Brand", "# in Stock", "Price"};
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 100, 644, 227);
		getContentPane().add(scrollPane);
		tableCart = new JTable(data,columnNames);
		scrollPane.setViewportView(tableCart);
		tableCart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			} 
		});
		
		JLabel lblNewLabel_1 = new JLabel("Total cost:      $");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(534, 337, 109, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel priceLabel = new JLabel("New label");
		priceLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		priceLabel.setBounds(642, 337, 89, 20);
		contentPane.add(priceLabel);
		priceLabel.setText(""+totalPrice+"");
		
		JButton btnNewButton_1 = new JButton("Check out");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CheckOut().setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(577, 378, 117, 31);
		contentPane.add(btnNewButton_1);
	}

}
