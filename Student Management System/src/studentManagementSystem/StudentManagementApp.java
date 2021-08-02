package studentManagementSystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class StudentManagementApp implements ActionListener{
	
	private static ArrayList<Student> students = new ArrayList<Student>();
	private static JLabel userLabel;
	private static JTextField userText;
	private static JLabel passwordLabel;
	private static JPasswordField passwordText;
	private static JButton loginButton;

	public static void main(String[] args) {
		//JFrame
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		frame.setSize(275, 175);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		panel.setLayout(null);
		
		//User
		userLabel = new JLabel("User");
		userLabel.setBounds(15, 15, 80, 25);
		panel.add(userLabel);
		
		userText = new JTextField(25);
		userText.setBounds(80, 18, 165, 20);
		panel.add(userText);
		
		//Password
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(15, 55, 80, 25);
		panel.add(passwordLabel);
		
		passwordText = new JPasswordField(25);
		passwordText.setBounds(80, 58, 165, 20);
		panel.add(passwordText);
		
		//Login 
		loginButton = new JButton("Login");
		loginButton.setBounds(168, 100, 75, 25);
		loginButton.addActionListener(new StudentManagementApp());
		panel.add(loginButton);
		
		frame.setVisible(true);
		
		//Ask for number of new students
		Scanner in = new Scanner(System.in);
		
		System.out.println("Number of new students:");
		int numNewStudents = in.nextInt();
		
		//Create n number of new students
		for(int i = 0; i < numNewStudents; i++) {
			students.add(new Student());
		}
		
		//Show status of all students
		for(int i = 0; i < students.size(); i++) {
			students.get(i).showStatus();
			System.out.println();
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String userInput = userText.getText();
		String passwordInput = passwordText.getText();
	}

}
