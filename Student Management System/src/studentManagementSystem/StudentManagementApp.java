package studentManagementSystem;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.sql.*;


public class StudentManagementApp{

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
			System.out.println("Connected to DB");
			return conn;
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return null;
	}
	
	private static ArrayList<Student> students = new ArrayList<Student>();

	public static void main(String[] args) throws Exception {
		
		//Connect to MySQL students database
		getConnection();
		
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

}
