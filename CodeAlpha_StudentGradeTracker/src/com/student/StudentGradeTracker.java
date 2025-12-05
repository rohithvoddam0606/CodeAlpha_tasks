package com.student;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Student> students = new ArrayList<>();

		System.out.print("Enter number of students: ");
		int n = sc.nextInt();
		sc.nextLine();

		for (int i = 0; i < n; i++) {
			System.out.print("Enter student name: ");
			String name = sc.nextLine();
			System.out.print("Enter student marks: ");
			int marks = sc.nextInt();
			sc.nextLine();
			students.add(new Student(name, marks));
		}

		int total = 0;
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		String topStudent = "", lowStudent = "";

		for (Student s : students) {
			total += s.grade;
			if (s.grade > max) {
				max = s.grade;
				topStudent = s.name;
			}
			if (s.grade < min) {
				min = s.grade;
				lowStudent = s.name;
			}
		}

		double average = (double) total / students.size();

		System.out.println("----------STUDENT GRADE REPORT----------");
		for (Student s : students) {
			System.out.println(s.name + " : " + s.grade);
		}

		System.out.println("----------------------------------------");
		System.out.println("Highest Score : " + max + " (" + topStudent + ")");
		System.out.println("Lowest Score  : " + min + " (" + lowStudent + ")");
		System.out.println("Average Score : " + String.format("%.2f", average));
		
		sc.close();
	}
}
