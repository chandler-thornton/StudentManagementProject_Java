package studentManagementSystem;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.sql.*;


public class StudentManagementApp{
	
	private static String stmt;
	public static String currentUser;
	public static int currentID;

	//Connect to MySQL students database
	public static Connection getConnection() throws IOException{
		
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
			statement.executeUpdate(stmt);
			
			return conn;
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return null;
	}
	
	public static void getStatement(String statementInput) {
		stmt = statementInput;
	}

	public static boolean login() {
		Scanner in = new Scanner(System.in);
		System.out.println("Username (First Name):");
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		
		System.out.println("STUDENT DATABASE\n1)Login\n2)Add New Student");
		int loginPageInput = in.nextInt();
		if(loginPageInput == 1) {
			login();
		}
		else if(loginPageInput == 2) {
			new Student();
		}
	}

}
