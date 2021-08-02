package studentManagementSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagementApp {
	
	public static ArrayList<Student> students = new ArrayList<Student>();

	public static void main(String[] args) {
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
