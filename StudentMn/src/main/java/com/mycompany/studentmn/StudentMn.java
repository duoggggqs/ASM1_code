/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.studentmn;

/**
 *
 * @author DUONG LE
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class StudentMn {
         private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.print("Enter the number of students in the class: ");
        int numStudents = scanner.nextInt();
        scanner.nextLine(); // consume newline character

        for (int i = 0; i < numStudents; i++) {
            System.out.println("\nEnter details for Student " + (i + 1) + ":");
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // consume newline character    

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Marks: ");
            double marks = scanner.nextDouble();
            scanner.nextLine(); // consume newline character

            students.add(new Student(id, name, marks));
        }
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline character

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    editStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    sortStudents();
                    break;
                case 5:
                    searchStudents();
                    break;
                case 6:
                    displayStudents();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nStudent Management System");
        System.out.println("1. Add Student");
        System.out.println("2. Edit Student");
        System.out.println("3. Delete Student");
        System.out.println("4. Sort Students");
        System.out.println("5. Search Students");
        System.out.println("6. Display Students");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addStudent() {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline character

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Marks: ");
        double marks = scanner.nextDouble();
        scanner.nextLine(); // consume newline character

        Student newStudent = new Student(id, name, marks);
        students.add(newStudent);
        System.out.println("Student added successfully.");
    }

    private static void editStudent() {
        System.out.print("Enter ID of the student to edit: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline character

        Student studentToEdit = findStudentById(id);
        if (studentToEdit == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter new Name: ");
        String newName = scanner.nextLine();

        System.out.print("Enter new Marks: ");
        double newMarks = scanner.nextDouble();
        scanner.nextLine(); // consume newline character

        studentToEdit.setName(newName);
        studentToEdit.setMarks(newMarks);
        System.out.println("Student information updated successfully.");
    }

    private static void deleteStudent() {
        System.out.print("Enter ID of the student to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline character

        Student studentToDelete = findStudentById(id);
        if (studentToDelete == null) {
            System.out.println("Student not found.");
            return;
        }

        students.remove(studentToDelete);
        System.out.println("Student deleted successfully.");
    }

    private static void sortStudents() {
        System.out.println("Sort students by:");
        System.out.println("1. ID");
        System.out.println("2. Name");
        System.out.println("3. Marks");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline character

        switch (choice) {
            case 1:
                Collections.sort(students, Comparator.comparingInt(Student::getId));
                break;
            case 2:
                Collections.sort(students, Comparator.comparing(Student::getName));
                break;
            case 3:
                Collections.sort(students, Comparator.comparingDouble(Student::getMarks).reversed());
                break;
            default:
                System.out.println("Invalid choice. Returning to main menu.");
                return;
        }

        System.out.println("Students sorted successfully.");
    }

    private static void searchStudents() {
        System.out.print("Enter the search keyword: ");
        String keyword = scanner.nextLine();

        ArrayList<Student> searchResults = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.add(student);
            }
        }

        if (searchResults.isEmpty()) {
            System.out.println("No students found matching the search keyword.");
        } else {
            System.out.println("Search Results:");
            for (Student student : searchResults) {
                System.out.println("ID: " + student.getId() + ", Name: " + student.getName() + ", Marks: " + student.getMarks());
            }
        }
    }

    private static void displayStudents() {
        System.out.println("\nStudent Details:");
        System.out.println("ID\tName\tMarks\tRank");
        for (Student student : students) {
            String grade = getGrade(student.getMarks());
            System.out.println(student.getId() + "\t" + student.getName() + "\t" + student.getMarks() + "\t" + grade);
        }
    }

    private static Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    private static String getGrade(double marks) {
        if (marks >= 9) {
            return "Excellent";
        } else if (marks >= 7.5) {
            return "Verry good";
        } else if (marks >= 6.5) {
            return "Good";
        } else if (marks >= 5) {
            return "Medium";
        } else {
            return "Fail";
        }
    }
}
   