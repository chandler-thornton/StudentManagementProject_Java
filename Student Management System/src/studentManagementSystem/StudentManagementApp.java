package studentManagementSystem;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.sql.*;


public class StudentManagementApp{
	
	private static String writeStmt;
	private static String readStmt;
	public static int currentStudentID;
	public static String currentFirstname;
	public static String currentLastname;
	public static int currentYear;
	public static String currentCourses;
	public static int currentBalance;
	private static final String username = "ADMIN";
	private static final String password = "ADMINpass123?";
	private static int menuSelection;

	//Write student data (Student constructor, Pay balance)
	public static Connection studentWrite() throws IOException{
		
		//Read database login information from dbconfig.properties file
		Properties prop = new Properties();
		FileInputStream ip = new FileInputStream("C:\\Users\\chanm\\git\\StudentManagementProject_Java\\Student Management System\\src\\Resources\\dbconfig.properties");
		prop.load(ip);
				
				
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = prop.getProperty("dburl");
			String user = prop.getProperty("dbusername");
			String pass = prop.getProperty("dbpassword");
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url, user, pass);
			
			Statement statement = conn.createStatement();
			statement.executeUpdate(writeStmt);
			
			return conn;
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return null;
	}
	
	//Read student information
	public static Connection studentRead() throws IOException{
		
		//Read database login information from dbconfig.properties file
		Properties prop = new Properties();
		FileInputStream ip = new FileInputStream("C:\\Users\\chanm\\git\\StudentManagementProject_Java\\Student Management System\\src\\Resources\\dbconfig.properties");
		prop.load(ip);
				
				
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = prop.getProperty("dburl");
			String user = prop.getProperty("dbusername");
			String pass = prop.getProperty("dbpassword");
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url, user, pass);
			
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(readStmt);
			while(rs.next()) {
				switch(menuSelection) {
				case 1:
					currentYear = rs.getInt("year");
					System.out.println("School year: " + currentYear);
					menu();
					break;
				case 2:
					currentCourses = rs.getString("courses"); 
					System.out.println("Courses enrolled: " + currentCourses);
					menu();
					break;
				case 3:
					currentBalance = rs.getInt("balance");
					break;
				}
			}
			
			return conn;
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return null;
	}
	
	public static void getWriteStatement(String writeStatementInput) {
		writeStmt = writeStatementInput;
	}
	
	public static void getReadStatement(String firstname, int value) {
		switch(value) {
		case 1:
			readStmt = "SELECT year FROM students WHERE firstname = '" + firstname + "'";
			break;
		case 2:
			readStmt = "SELECT courses FROM students WHERE firstname = '" + firstname + "'";
			break;
		case 3:
			readStmt = "SELECT balance FROM students WHERE firstname = '" + firstname + "'";
			break;
		}
	}

	public static boolean login() throws IOException {
		Scanner in = new Scanner(System.in);
		
		System.out.println("Username:");
		String userInput = in.nextLine();
		if(userInput.equals(username)) {
			System.out.println("Password:");
			String passInput = in.nextLine();
			if(passInput.equals(password)) {
				return true;
			}
		}
		return false;
	}
	
	public static void menu() throws IOException {
		Scanner in = new Scanner(System.in);
		System.out.println("STUDENT DATABASE:\n1)Add new student\n2)View student info\n3)Pay student balance");
		menuSelection = in.nextInt();
		if(menuSelection == 1) {
			new Student();
		}
		else if(menuSelection == 2) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter first name:");
			currentFirstname = scan.nextLine();
			System.out.println("Enter data to pull:\n1)School year\n2)Courses enrolled\n3)Balance");
			menuSelection = scan.nextInt();
			getReadStatement(currentFirstname, menuSelection);
			studentRead();
		}
		else if(menuSelection == 3) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter first name");
			currentFirstname = scan.nextLine();
			getReadStatement(currentFirstname, 3);
			studentRead();
			System.out.println("Your current balance is: $" + currentBalance + ", Enter amount to pay:");
			int newBalance = currentBalance - (scan.nextInt());
			getWriteStatement("UPDATE students SET balance = " + newBalance + " WHERE firstname = '" + currentFirstname + "'");
			studentWrite();
			studentRead();
			System.out.println();
			System.out.println(currentFirstname + "'s new balance is: $" + currentBalance);
			menu();
		}
	}
	
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		
		System.out.println("LOGIN (Press 1 to continue)");
		int loginContinue = in.nextInt();
		if(loginContinue == 1) {
			if(login()) {
				menu();
			}
			else {
				System.out.println("Login Failed");
			}
		}
	}

}
