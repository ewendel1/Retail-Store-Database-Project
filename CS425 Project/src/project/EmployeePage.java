package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

public class EmployeePage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeePage frame = new EmployeePage();
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
	public EmployeePage() {
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
		
		JButton btnViewWarehouses = new JButton("View Customers");
		btnViewWarehouses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ViewCustomers().setVisible(true);
				dispose();
			}
		});
		btnViewWarehouses.setBounds(328, 217, 129, 32);
		contentPane.add(btnViewWarehouses);
		
		JButton btnViewOrders = new JButton("View Orders");
		btnViewOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ViewOrders().setVisible(true);
				dispose();
			}
		});
		btnViewOrders.setBounds(328, 259, 129, 32);
		contentPane.add(btnViewOrders);
		
		JLabel lblNewLabel = new JLabel("Options");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(356, 136, 129, 32);
		contentPane.add(lblNewLabel);
	}
}
