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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AddressPayment extends JFrame {

	private JPanel contentPane;
	private JTextField street;
	private JTextField city;
	private JTextField state;
	private JTextField zipcode;
	private JTextField country;
	private String customerID;
	private JTextField number;
	private JTextField cvc;
	private JButton modifyPayments;
	private JButton modifyAddress;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddressPayment frame = new AddressPayment();
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
	public AddressPayment() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 555); 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		Icon icon = new ImageIcon("C:\\Users\\ijahw\\Documents\\IIT\\Spring 2022\\CS 425\\home.png");
		JButton homeButton = new JButton(icon);
		homeButton.setBounds(10, 10, 45, 39);
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CustomerPage().setVisible(true);
				dispose();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(homeButton);
		
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
		
		JButton btnNewButton = new JButton("<- Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CustomerInfo().setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(65, 10, 81, 39);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("My profile -> Address/Payment Info");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(161, 10, 394, 39);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1_1 = new JLabel("Street");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(38, 125, 55, 13);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("City");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_2.setBounds(38, 154, 45, 20);
		contentPane.add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("State");
		lblNewLabel_1_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_2_1.setBounds(38, 187, 45, 20);
		contentPane.add(lblNewLabel_1_1_2_1);
		
		JLabel lblNewLabel_1_1_2_1_1 = new JLabel("Zipcode");
		lblNewLabel_1_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_2_1_1.setBounds(38, 221, 125, 20);
		contentPane.add(lblNewLabel_1_1_2_1_1);
		
		JLabel lblNewLabel_1_1_2_1_1_1 = new JLabel("Country");
		lblNewLabel_1_1_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_2_1_1_1.setBounds(38, 251, 125, 20);
		contentPane.add(lblNewLabel_1_1_2_1_1_1);
		
		street = new JTextField();
		street.setColumns(10);
		street.setBounds(166, 119, 125, 20);
		contentPane.add(street);
		
		city = new JTextField();
		city.setColumns(10);
		city.setBounds(166, 154, 125, 20);
		contentPane.add(city);
		
		state = new JTextField();
		state.setColumns(10);
		state.setBounds(166, 187, 125, 20);
		contentPane.add(state);
		
		zipcode = new JTextField();
		zipcode.setColumns(10);
		zipcode.setBounds(166, 221, 125, 20);
		contentPane.add(zipcode);
		
		country = new JTextField();
		country.setColumns(10);
		country.setBounds(166, 254, 125, 20);
		contentPane.add(country);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAddress.setBounds(92, 59, 91, 39);
		contentPane.add(lblAddress);
		
		JLabel lblPayment = new JLabel("Credit Cards");
		lblPayment.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPayment.setBounds(437, 59, 189, 39);
		contentPane.add(lblPayment);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Number");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(391, 122, 91, 13);
		contentPane.add(lblNewLabel_1_1_1);
		
		number = new JTextField();
		number.setColumns(10);
		number.setBounds(519, 116, 125, 20);
		contentPane.add(number);
		
		JLabel lblNewLabel_1_1_2_2 = new JLabel("CVC");
		lblNewLabel_1_1_2_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_2_2.setBounds(391, 151, 45, 20);
		contentPane.add(lblNewLabel_1_1_2_2);
		
		cvc = new JTextField();
		cvc.setColumns(10);
		cvc.setBounds(519, 151, 125, 20);
		contentPane.add(cvc);
		
		JLabel lblNewLabel_1_1_2_1_2 = new JLabel("Type");
		lblNewLabel_1_1_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_2_1_2.setBounds(391, 184, 91, 20);
		contentPane.add(lblNewLabel_1_1_2_1_2);
		
		String[] cardType = {
				 "Credit",
		         "Debit",
		     
		};
		JComboBox card = new JComboBox(cardType);
		card.setEditable(true);
		card.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		card.setBounds(519, 184, 125, 20);
		getContentPane().add(card);
		
		//Fill in information
		try {
		    BufferedReader reader = new BufferedReader(new FileReader("CUSTOMER.ser"));
		    customerID = reader.readLine();
		    reader.close();
		    Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL", "HR", "oracle");
            Statement st = conn.createStatement();
            Statement st2 = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from ADDRESS where CUSTOMERID='"+customerID+"'");
            ResultSet rs2 = st2.executeQuery("select * from PAYMENT where CUSTOMERID='"+customerID+"'");
            if(rs.next()) {
	            street.setText(rs.getString("STREET"));
	            city.setText(rs.getString("CITY"));
	            state.setText(rs.getString("STATE"));
	            zipcode.setText(rs.getString("ZIPCODE"));
	            country.setText(rs.getString("COUNTRY"));
            }
	        if(rs2.next()) {
            	number.setText(rs2.getString("CARDNUMBER"));
	            cvc.setText(rs2.getString("SECURITYNUM"));
	            card.setSelectedItem(rs2.getString("PAYMENTTYPE"));
            }
            rs.close();
            rs2.close();
            st.close();
            conn.close();
		}
		catch(Exception ex) {
		    ex.printStackTrace();
		}
		
		//Modify Address
		JButton btnNewButton_1;
		modifyAddress = new JButton("Modify");
		modifyAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL", "HR", "oracle");
					
		            Statement st = conn.createStatement();

		            st.executeUpdate("UPDATE ADDRESS SET STREET='"+street.getText()+"', CITY = '"+city.getText()+"', STATE = '"
		            		+ state.getText()+"', ZIPCODE = '"+zipcode.getText()
		            		+"', COUNTRY = '"+country.getText()+"' WHERE CUSTOMERID = '"+customerID+"'");
			        st.close();		            
	            	conn.close();     
				} catch (Exception r) {
					r.printStackTrace();
				}
			}
		});
		modifyAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		modifyAddress.setBounds(114, 315, 94, 32);
		contentPane.add(modifyAddress);
		
		
		//Modify Payment type
		
		JButton btnNewButton_2;
		modifyPayments = new JButton("Modify");
		modifyPayments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL", "HR", "oracle");
					
		            Statement st = conn.createStatement();

		            st.executeUpdate("UPDATE PAYMENT SET CARDNUMBER='"+number.getText() +"', SECURITYNUM='"
		            		+cvc.getText()+"', PAYMENTTYPE = '"+(String) card.getSelectedItem()+ "' WHERE CUSTOMERID = '"+customerID+"'");
			        st.close();		            
	            	conn.close();     
				} catch (Exception r) {
					r.printStackTrace();
				}
			}
		});
		modifyPayments.setFont(new Font("Tahoma", Font.PLAIN, 14));
		modifyPayments.setBounds(470, 239, 94, 32);
		contentPane.add(modifyPayments);
	}
	
	

}
