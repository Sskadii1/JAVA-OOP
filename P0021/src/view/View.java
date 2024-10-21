/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.Manager;
import controller.Validation;
import java.util.ArrayList;
import model.Report;
import model.Student;

/**
 *
 * @author Admin
 */
public class View {

    String[] menu = {
        "Create",
        "Find and Sort",
        "Update/Delete",
        "Report",
        "Exit"
    };

    public void display() {
        for (int i = 0; i < menu.length; i++) {
            System.out.println((i + 1) + ". " + menu[i]);
        }
    }
    
    public Student inputStudent(Manager m) {
        // Loop until user input not duplicate
        while (true) {
            String id = Validation.getString("Enter id: ");
            ArrayList<Student> findById = m.studentsListFindById(id);
            String studentName;
            if (!findById.isEmpty()) {
                studentName = findById.get(0).getStudentName();
            } else { 
                studentName = Validation.getString("Enter student name: ");
            }
            if (!Validation.checkIdExist(m, id, studentName)) {
                System.err.println("Id has exist. Please input again");
                continue;
            }

            String semester = Validation.getString("Enter semester: ");
            String courseName = Validation.getInputCourse("Enter course name: ");
            
            // Check student exist or not
            if (m.checkStudentExist(m, id, studentName, semester, courseName)) {
                return new Student(id, studentName, semester, courseName);                
            }
            System.err.println("Duplicate");
        }
    }
    
    public void displayStudentsList(ArrayList<Student> students) {
        if (students.isEmpty()) {
            System.err.println("Empty list");
            return;
        }
        System.out.printf("%-10s| %-15s| %-15s| %-12s\n", "Number", "Student name", "Semester", "Course name");
        for (int i = 0; i < students.size(); i++) {
            System.out.printf("%-10d| %s\n", i+1, students.get(i));
        }
    }
    
    public void displayReportsList(ArrayList<Report> reports) {
        if (reports.isEmpty()) {
            System.err.println("Empty list");
            return;
        }
        System.out.printf("%-15s| %-12s| %-15s\n", "Student name", "Course name", "Total course");
        for (Report r : reports) {
            System.out.println(r);
        }
    }

}
