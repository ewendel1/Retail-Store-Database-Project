package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

public class CheckOut extends JFrame {

	private JPanel contentPane;
	private String customerID;
	private String cartID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckOut frame = new CheckOut();
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
	public CheckOut() {		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 555);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Check out");
		lblNewLabel.setBounds(158, 18, 142, 31);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblNewLabel);
		
		JButton back = new JButton("<- Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Cart().setVisible(true);
				dispose();
			}
		});
		back.setBounds(65, 10, 81, 39);
		contentPane.add(back);
		
		Icon icon = new ImageIcon("C:\\Users\\ijahw\\Documents\\IIT\\Spring 2022\\CS 425\\home.png");
		JButton btnNewButton = new JButton(icon);
		btnNewButton.setBounds(10, 10, 45, 39);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CustomerPage().setVisible(true);
				dispose();
			}
		});
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
		
		JLabel lblSelectPaymentMethod = new JLabel("Payment method");
		lblSelectPaymentMethod.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelectPaymentMethod.setBounds(46, 73, 142, 31);
		contentPane.add(lblSelectPaymentMethod);
		
		JLabel lblSelectAddress = new JLabel("Address");
		lblSelectAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelectAddress.setBounds(46, 114, 142, 31);
		contentPane.add(lblSelectAddress);
		
		JLabel lblTotalPrice = new JLabel("Total Price");
		lblTotalPrice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTotalPrice.setBounds(46, 155, 88, 31);
		contentPane.add(lblTotalPrice);
		
		
		try {
			BufferedReader readCus = new BufferedReader(new FileReader("CUSTOMER.ser"));
			customerID = readCus.readLine();
			readCus.close();
			BufferedReader readOrd = new BufferedReader(new FileReader("ORDER.ser"));
			cartID = readOrd.readLine();
			readOrd.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		JButton btnNewButton_1 = new JButton("Confirm order");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL", "HR", "oracle");
					Statement st = conn.createStatement();
					PreparedStatement pst = conn.prepareStatement("select ORDERS_SEQUENCE.NEXTVAL from dual");
					ResultSet rs = pst.executeQuery();
		            long orderID = 0;
		            if(rs.next()) {
		            	orderID = rs.getLong(1);
		            }
					
					Calendar time = Calendar.getInstance();     
		            String status = "Pending";
					st.executeUpdate("INSERT INTO ORDERS (ORDERID, CUSTOMERID, CARTID, DATEINFO, STATUS) "
            		+ "VALUES ('"+orderID+"', '"+customerID+"', '"+cartID+"', TO_DATE('"+time.YEAR+"-"
            		+time.MONTH+"-"+time.DAY_OF_MONTH+" "+time.HOUR_OF_DAY+":"+time.MINUTE+":"
            		+time.SECOND+"', 'YYYY-MM-DD HH24:MI:SS'), '"+ status+"')");
		            
					//next cart = new cart	
					PreparedStatement pst4 = conn.prepareStatement("select CART_SEQUENCE.NEXTVAL from dual");
					ResultSet rs2 = pst4.executeQuery();
			        long cartID = 0;
			        if(rs2.next()) {
			        	cartID = rs2.getLong(1);
			        }
			        PrintWriter writer = new PrintWriter("ORDER.ser");
			        writer.println(cartID);
			        writer.close();
			        
					//st.execute("INSERT INTO CART (ORDERID, CUSTOMERID, PRODUCTID, DATEINFO, STATUS)");
					//PreparedStatement pst = conn.prepareStatement("SELECT * FROM CART natural join PRODUCTS WHERE CUSTOMERID="+customerID);
					//ResultSet rs = pst.executeQuery();
					//st.execute("UPDATE PRODUCTS SET STOCK=STOCK-1 WHERE PRODUCTID=82");
					
			        PreparedStatement pst5 = conn.prepareStatement("select SHIP_SEQUENCE.NEXTVAL from dual");
					ResultSet rs3 = pst5.executeQuery();
			        long tracking = 0;
			        if(rs3.next()) {
			        	tracking = rs3.getLong(1);
			        }
					st.execute("INSERT INTO SHIPPINGORDER (CUSTOMERID, TRACKINGNUMBER, CARTID) VALUES ('"+customerID+"', '"
							+tracking+"', '"+cartID+"')");
					
					
					JOptionPane.showMessageDialog(null, "Order submitted!");
					new CustomerPage().setVisible(true);
					dispose();
					
				}
				catch (Exception r) {
					JOptionPane.showMessageDialog(null, "Error, try again");
					r.printStackTrace();
				}
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(193, 220, 124, 31);
		contentPane.add(btnNewButton_1);
		
		JLabel payment = new JLabel("");
		payment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		payment.setBounds(198, 84, 102, 20);
		contentPane.add(payment);
		
		JLabel address = new JLabel("");
		address.setFont(new Font("Tahoma", Font.PLAIN, 16));
		address.setBounds(196, 114, 427, 20);
		contentPane.add(address);
		
		JLabel price = new JLabel("");
		price.setFont(new Font("Tahoma", Font.PLAIN, 16));
		price.setBounds(193, 155, 102, 20);
		contentPane.add(price);
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL", "HR", "oracle");
			
			PreparedStatement pst = conn.prepareStatement("select CARDNUMBER from PAYMENT natural join CUSTOMERS where CUSTOMERID = "+customerID);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
	            payment.setText(rs.getString("CARDNUMBER"));
			}
			PreparedStatement pst2 = conn.prepareStatement("select * from ADDRESS natural join CUSTOMERS where CUSTOMERID = "+customerID);
			ResultSet rs2 = pst2.executeQuery();
			while(rs2.next()) {
	            address.setText(""+rs2.getString("STREET")+" "+rs2.getString("CITY") + " "
	            		+ rs2.getString("STATE") + " " + rs2.getString("ZIPCODE")+ " "+rs2.getString("COUNTRY"));
			}
			PreparedStatement pst3 = conn.prepareStatement("select * from CART natural join CUSTOMERS natural join PRODUCTS where CUSTOMERID = "+customerID);
			ResultSet rs3 = pst3.executeQuery();
			double totprice = 0;
			while(rs3.next()) {
				totprice += Integer.parseInt(rs3.getString("PRICE"));
			}
	        price.setText(Double.toString(totprice));
	        
	        pst.close();
			pst2.close();
			rs.close();
			rs2.close();
	        conn.close();
		} catch (Exception r) {
			r.printStackTrace();
		}
	}

}
