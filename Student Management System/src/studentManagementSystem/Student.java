package studentManagementSystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Student {
	private String firstName;
	private String lastName;
	private int year;
	private String studentID;
	private ArrayList<String> courses = new ArrayList<String>();
	private String courseString;
	private int balance = 0;
	private static int courseCost = 600;
	private static int staticID = 1000;
	
	//Constructor: Ask for name & year of new student
	public Student() throws IOException {
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter first name:");
		this.firstName = in.nextLine();
		System.out.println("Enter last name:");
		this.lastName = in.nextLine();
		System.out.println("1 - Freshman\n2 - Sophomore\n3 - Junior\n4 - Senior");
		this.year = in.nextInt();
		
		setStudentID();
		enroll();
		coursesToString();
		
		StudentManagementApp.getStatement("INSERT INTO students"
				+ " (studentID, firstname, lastname, schoolYear, courses, balance)"
				+ " VALUES (" + studentID + ", '" + firstName + "', '" + lastName + "', " + year + ", '" + courseString + "', " + balance + ")");
		StudentManagementApp.getConnection();
	}
	
	//Generate 5 digit student ID
	private void setStudentID() {
		//StudentID = Grade Level + StaticID
		staticID++;
		this.studentID = year + "" + staticID;
	}
	
	//Enroll in courses from provided list (HIST 101, MATH 101, ENGL 101, CHEM 101, CS 101)
	public void enroll() {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter course to enroll (Q to quit)");
		String course;
	    while(!(course = in.nextLine()).equals("Q")) {
	        courses.add(course);
	    }
		balance = (courses.size()) * courseCost;
	}
	
	//Convert ArrayList courses to string
	public void coursesToString() {
		StringBuilder sb = new StringBuilder();
		for(String course : courses) {
			sb.append(course);
			sb.append(" ");
		}
		
		courseString = sb.toString();
	}
	
	//View student balance
	public void viewBalance() {
		System.out.println("Current Balance: $" + balance);
	}
	
	//Pay student tuition
	public void payBalance(int paymentAmt) {
		balance = balance - paymentAmt;
		System.out.println("Paid: $" + paymentAmt + "\nCurrent Balance: $" + balance);
	}
	
	//Show student status (Name, ID, Enrolled courses, Balance)
	public void showStatus() {
		System.out.println(firstName + " " + lastName + 
				" (#" + studentID + ")" +
				"\nEnrolled Courses: " + courses + 
				"\nCurrent Balance: $" + balance);
	}

}
